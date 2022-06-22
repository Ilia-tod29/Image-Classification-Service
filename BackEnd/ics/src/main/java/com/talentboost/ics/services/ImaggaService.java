package com.talentboost.ics.services;

import com.talentboost.ics.data.Image;

import java.io.IOException;

public interface ImaggaService {
    Image getImageData(final String imageUrl) throws IOException;
}
