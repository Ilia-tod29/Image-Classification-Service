package com.talentboost.ics.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(catalog = "final_task", schema = "ics", name = "image_tag")
public class ImageTag {
    @EmbeddedId
    @JsonIgnore
    private ImageTagId id = new ImageTagId();

    @ManyToOne
    @MapsId("imageId")
    @JsonIgnore
    private Image image;

    @ManyToOne
    @MapsId("tagId")
    private Tag tag;

    private Double confidence;

    public ImageTagId getId() {
        return id;
    }

    public void setId(ImageTagId id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }



    @Embeddable
    public static class ImageTagId implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long imageId;
        private Long tagId;

        public ImageTagId() {}

        public ImageTagId(Long imageId, Long tagId) {
            super();
            this.imageId = imageId;
            this.tagId = tagId;
        }

        public Long getImageId() {
            return this.imageId;
        }

        public void setImageId(Long imageId) {
            this.imageId = imageId;
        }

        public Long getTagId() {
            return this.tagId;
        }

        public void setTagId(Long tagId) {
            this.tagId = tagId;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result
                    + ((this.imageId == null) ? 0 : this.imageId.hashCode());
            result = prime * result
                    + ((this.tagId == null) ? 0 : this.tagId.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ImageTagId other = (ImageTagId) obj;
            return Objects.equals(getImageId(), other.getImageId()) && Objects.equals(getTagId(), other.getTagId());
        }
    }

}

