package com.foodorderapp.services.interfaces;

import com.foodorderapp.models.service.ImageServiceModel;

public interface ImageService {
    ImageServiceModel upload(ImageServiceModel imageServiceModel);
    ImageServiceModel edit(ImageServiceModel imageServiceModel);
    void delete(String name);
    ImageServiceModel findByName(String name);
}
