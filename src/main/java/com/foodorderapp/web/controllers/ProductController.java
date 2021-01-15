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

            List<ProductServiceModel> productServiceAll = this.productService.findAll();
                for (var product : productServiceAll) {
                    ProductViewModel productViewModel =
                            this.modelMapper
                            .map(product, ProductViewModel.class);

                    products.add(productViewModel);
                }

            if (products.isEmpty()) {
                return new ResponseEntity<>("No products...",HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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

    @GetMapping(path = Links.PRODUCT_GET_BY_ID)
    public ResponseEntity<ProductViewModel> getProduct(@PathVariable("id") String id) {
        try {

            var product = this.modelMapper.map(
                    this.productService.findById(id), ProductViewModel.class);

            return  new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping(path = Links.PRODUCT_EDIT)
    public ResponseEntity<ProductViewModel> editProduct(
            @RequestBody ProductEditBindingModel productEditBindingModel) {
        try {
            ProductEditBindingModel productData = this.modelMapper
                            .map(this.productService.findById(productEditBindingModel.getId()),
                                    ProductEditBindingModel.class);

            if (productData != null) {
                productData.setName(productEditBindingModel.getName());
                productData.setContent(productEditBindingModel.getContent());
                productData.setType(productEditBindingModel.getType());
                productData.setVolume(productEditBindingModel.getVolume());
                productData.setPrice(productEditBindingModel.getPrice());
               // product.setPicBytes(compressBytes(file.getBytes()));

                ProductServiceModel productServiceModel = this.modelMapper
                        .map(productData, ProductServiceModel.class);

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
}
