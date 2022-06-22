package com.talentboost.ics.services.impl;

import com.google.gson.Gson;
import com.talentboost.ics.data.Image;
import com.talentboost.ics.data.ImageTag;
import com.talentboost.ics.data.Tag;
import com.talentboost.ics.dto.datafromapi.ResultDto;
import com.talentboost.ics.dto.datafromapi.Tags;
import com.talentboost.ics.services.ChecksumService;
import com.talentboost.ics.services.ImaggaService;
import com.talentboost.ics.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ImaggaServiceImpl implements ImaggaService {
    private static final String SERVICE = "Imagga";

    private final TagService tagService;
    private final ChecksumService checksumService;

    @Autowired
    public ImaggaServiceImpl(TagService tagService, ChecksumService checksumService) {
        this.tagService = tagService;
        this.checksumService = checksumService;
    }

    private List<Integer> getImageSize(final String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            final BufferedImage bufferedImage = ImageIO.read(url);

            int imageWidth = bufferedImage.getWidth();
            int imageHeight = bufferedImage.getHeight();

            List<Integer> toReturn = new ArrayList<>();
            toReturn.add(imageWidth);
            toReturn.add(imageHeight);

            return toReturn;
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    private Image createImageFromUrl(String imageUrl, String jsonResponse) {
        //Creating new image
        Image imageToSend = new Image();
        imageToSend.setUrl(imageUrl);

        Date date = new Date();
        imageToSend.setAdded_on(date);

        imageToSend.setService(this.SERVICE);

        //Getting the width and height
        List<Integer> widthAndHeight = this.getImageSize(imageUrl);
        imageToSend.setWidth(widthAndHeight.get(0));
        imageToSend.setHeight(widthAndHeight.get(1));

        //Getting the checksum of the image
        Long checksum = this.checksumService.checksumURL(imageUrl);
        imageToSend.setChecksum(checksum);

        //Converting the result string to json to Dto
        Gson gson = new Gson();
        ResultDto resultDto = gson.fromJson(jsonResponse, ResultDto.class);

        //Creating a set of tags
        List<Tag> tags = resultDto
                .getResult()
                .getTags()
                .stream()
                .filter(tag -> tag.getConfidence() >= 30)
                .map(tag -> {
                    Tag newTag = new Tag();
                    newTag.setName(tag.getTag().getEn());
                    return newTag;
                })
                .collect(Collectors.toList());

        //Creating list of confidences
        List<Double> confidences = resultDto
                .getResult()
                .getTags()
                .stream()
                .map(Tags::getConfidence)
                .filter(confidence -> confidence >= 30)
                .collect(Collectors.toList());

        //Adding the non-existing tgs to the db and setting the image-tag connections to the toSendDto
        Tag toAddToTheImage;
        for (int i = 0; i < tags.size(); i++) {
            if (!this.tagService.existsTagByName(tags.get(i).getName())) {
                this.tagService.add(tags.get(i));
            }
            toAddToTheImage = this.tagService.getTagByName(tags.get(i).getName());

            ImageTag connectionToSend = new ImageTag();
            Tag tag = new Tag();
            tag.setId(toAddToTheImage.getId());
            connectionToSend.setTag(tag);
            connectionToSend.setConfidence(confidences.get(i));

            imageToSend.addImageTag(connectionToSend);
        }
        return imageToSend;
    }

    @Override
    public Image getImageData(final String imageUrl) throws IOException {
        String credentialsToEncode = "acc_1df70508939e24c" + ":" + "45f35232011db6cd0b4c3d75787cc452";
        String basicAuth = Base64.getEncoder().encodeToString(credentialsToEncode.getBytes(StandardCharsets.UTF_8));

        String endpoint_url = "https://api.imagga.com/v2/tags";

        String url = endpoint_url + "?image_url=" + imageUrl;
        URL urlObject = new URL(url);

        //Connection to the api
        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

        connection.setRequestProperty("Authorization", "Basic " + basicAuth);

        BufferedReader connectionInput = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String jsonResponse = connectionInput.readLine();

        connectionInput.close();

        //Returning the image
        return createImageFromUrl(imageUrl, jsonResponse);
    }
}
