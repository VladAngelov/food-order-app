package com.foodorderapp.services.impl;

import com.foodorderapp.models.entity.Image;
import com.foodorderapp.models.service.ImageServiceModel;
import com.foodorderapp.repositories.ImageRepository;
import com.foodorderapp.services.interfaces.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final ModelMapper modelMapper;

    public ImageServiceImpl(
            ImageRepository imageRepository,
            ModelMapper modelMapper) {
        this.imageRepository = imageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ImageServiceModel upload(ImageServiceModel imageServiceModel) {
        return this.modelMapper
                .map(
                    this.imageRepository
                            .saveAndFlush(this.modelMapper
                                    .map(imageServiceModel, Image.class)),
                    ImageServiceModel.class);
    }

    @Override
    public ImageServiceModel edit(ImageServiceModel imageServiceModel) {
        var imgDB = this.modelMapper.map(this.imageRepository
                .findByName(imageServiceModel.getName()), Image.class);

        imgDB.setName(imageServiceModel.getName());
        imgDB.setType(imageServiceModel.getType());
        imgDB.setPicByte(imageServiceModel.getPicByte());

        return this.modelMapper
                .map(this.imageRepository.saveAndFlush(imgDB),
                    ImageServiceModel.class);
    }

    @Override
    public void delete(String name) {
        this.imageRepository
            .delete(this.modelMapper
                    .map(this.imageRepository.findByName(name),
                            Image.class));
    }

    @Override
    public ImageServiceModel findByName(String name) {
        return this.modelMapper
                .map(this.imageRepository.findByName(name),
                        ImageServiceModel.class);
    }
}
