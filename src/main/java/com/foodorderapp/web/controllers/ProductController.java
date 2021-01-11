package com.foodorderapp.web.controllers;

import com.foodorderapp.constants.Links;
import com.foodorderapp.models.binding.product.ProductAddBindingModel;
import com.foodorderapp.models.binding.product.ProductEditBindingModel;
import com.foodorderapp.models.entity.Product;
import com.foodorderapp.models.service.ProductServiceModel;
import com.foodorderapp.models.view.ProductViewModel;
import com.foodorderapp.repositories.ProductRepository;
import com.foodorderapp.services.interfaces.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@RequestMapping(Links.API)
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(
            ProductService productService,
            ModelMapper modelMapper
    ) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(path = Links.PRODUCTS_ALL)
    public ResponseEntity<?> getAllProducts() {
        try {
            List<ProductViewModel> products = new ArrayList<ProductViewModel>();

                var ps = this.productService.findAll();
                for (var product : ps) {
                    ProductViewModel productViewModel =
                            this.modelMapper
                            .map(product, ProductViewModel.class);
                    products.add(productViewModel);
                }

            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = Links.PRODUCT_BY_ID)
    public ResponseEntity<ProductViewModel> getProductById(
            @PathVariable("id") String id) {
        ProductServiceModel productServiceModel = this.modelMapper
                .map(this.productService.findById(id), ProductServiceModel.class);

        ProductViewModel product = this.modelMapper
                .map(productServiceModel, ProductViewModel.class);

        if (product.getName() != null) {
            Optional<ProductViewModel> productOp = Optional.of(product);
            return new ResponseEntity<>(productOp.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = Links.PRODUCT_ADD)
    public ResponseEntity<?> addProduct(
            @RequestBody ProductAddBindingModel productAddBindingModel ) {
        try {
           var productServiceModel =   this.modelMapper
                       .map(productAddBindingModel, ProductServiceModel.class);

           ProductServiceModel product = this.productService
            .addProduct(productServiceModel);

           ProductViewModel productViewModel = this.modelMapper
                       .map(product, ProductViewModel.class);

            return new ResponseEntity<>(productViewModel, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = Links.PRODUCT_EDIT_BY_ID)
    public ResponseEntity<ProductViewModel> editProduct(
            @PathVariable("id") String id,
            @RequestBody ProductEditBindingModel productEditBindingModel) {
        try {
            ProductEditBindingModel productData = this.modelMapper
                            .map(this.productService.findById(id),
                                    ProductEditBindingModel.class);

            Optional<ProductEditBindingModel> productOptional =
                    Optional.ofNullable(productData);

            if (productOptional.isPresent()) {
                ProductEditBindingModel product = productOptional.get();
                product.setName(productEditBindingModel.getName());
                product.setContent(productEditBindingModel.getContent());
                product.setType(productEditBindingModel.getType());
                product.setVolume(productEditBindingModel.getVolume());
                product.setPrice(productEditBindingModel.getPrice());
               // product.setPicBytes(compressBytes(file.getBytes()));

                ProductServiceModel productServiceModel = this.modelMapper
                        .map(product, ProductServiceModel.class);

                return new ResponseEntity<>(
                        this.modelMapper.map(
                                this.productService.addProduct(productServiceModel),
                                ProductViewModel.class),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @DeleteMapping(path = Links.PRODUCT_DELETE_BY_ID)
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") String id) {
        try {
            this.productService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = Links.PRODUCT_BY_TYPE)
    public ResponseEntity<List<ProductViewModel>> findByType(
            @PathVariable("type") String type) {
        try {
            List<ProductServiceModel> productsServiceModel =
                    this.productService.findByType(type)
                            .stream()
                            .map(product -> this.modelMapper.map(product, ProductServiceModel.class))
                            .collect(Collectors.toList());

            List<ProductViewModel> productsViewModel = productsServiceModel
                    .stream()
                    .map(product -> this.modelMapper.map(product, ProductViewModel.class))
                    .collect(Collectors.toList());

            if (!productsViewModel.isEmpty()) {
                return new ResponseEntity<>(productsViewModel, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // compress the image bytes before storing it in the database
    private static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream =
                new ByteArrayOutputStream(data.length);

        byte[] buffer = new byte[1024];

        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " +
                outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    private static byte[] decompressBytes(byte[] data) {
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
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
