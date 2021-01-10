package com.foodorderapp.web.controllers;

import com.foodorderapp.constants.Links;
import com.foodorderapp.models.binding.image.ImageBindingModel;
import com.foodorderapp.models.service.ImageServiceModel;
import com.foodorderapp.models.view.ImageViewModel;
import com.foodorderapp.services.impl.ImageServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@RequestMapping(path = Links.API)
public class ImageController {

    private final ImageServiceImpl imageService;
    private final ModelMapper modelMapper;

    public ImageController(
            ImageServiceImpl imageService,
            ModelMapper modelMapper) {
        this.imageService = imageService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(path = Links.IMAGE_ADD)
    public BodyBuilder uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {

        System.out.println("Original Image Byte Size - " + file.getBytes().length);

        ImageBindingModel img = new ImageBindingModel(
                file.getOriginalFilename(),
                file.getContentType(),
                compressBytes(file.getBytes()));

        this.imageService.upload(this.modelMapper.map(img, ImageServiceModel.class));

        return ResponseEntity.status(HttpStatus.OK);
    }

    @GetMapping(path = Links.IMAGE_GET)
    public ImageViewModel getImage(@PathVariable("name") String name) throws IOException {

        var image = this.imageService.findByName(name);

        ImageViewModel retrievedImage = (this.modelMapper
                .map(image, ImageViewModel.class));

        ImageViewModel img = new ImageViewModel(
                retrievedImage.getName(),
                retrievedImage.getType(),
                decompressBytes(retrievedImage.getPicByte()));

        return img;
    }

    @PutMapping(path = Links.IMAGE_EDIT)
    public BodyBuilder editImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        ImageBindingModel img = new ImageBindingModel(
                file.getOriginalFilename(),
                file.getContentType(),
                compressBytes(file.getBytes()));

        this.imageService.edit(this.modelMapper.map(img, ImageServiceModel.class));
        return ResponseEntity.status(HttpStatus.OK);
    }

    @PostMapping(path = Links.IMAGE_DELETE)
    public BodyBuilder deleteImage(@PathVariable("name") String name) throws IOException {

        this.imageService.delete(name);

        return ResponseEntity.status(HttpStatus.OK);
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];

        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }

        try {
            outputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];

        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (DataFormatException e) {
            System.out.println(e.getMessage());
        }
        return outputStream.toByteArray();
    }
}
