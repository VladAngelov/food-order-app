package com.foodorderapp.services.impl;

import com.foodorderapp.models.entity.Product;
import com.foodorderapp.models.service.ProductServiceModel;
import com.foodorderapp.repositories.ProductRepository;
import com.foodorderapp.services.interfaces.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(
            ProductRepository productRepository,
            ModelMapper modelMapper
    ) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductServiceModel findByName(String name) {

       return this.modelMapper
               .map(this.productRepository.findByName(name),
                    ProductServiceModel.class);
    }

    @Override
    public List<ProductServiceModel> findByType(String type) {
        return this.productRepository.findAll()
                .stream()
                .filter(p -> p.getType().equals(type))
                .map(product -> this.modelMapper
                    .map(product, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductServiceModel> findAll() {
        return this.productRepository
                .findAll()
                .stream()
                .map(product -> this.modelMapper
                    .map(product, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductServiceModel findById(String id) {
        Optional<Product> productDB = this.productRepository.findById(id);
        ProductServiceModel product = this.modelMapper.map(productDB.get(), ProductServiceModel.class);

        return this.modelMapper.map(product, ProductServiceModel.class);
    }

    @Override
    public ProductServiceModel addProduct(ProductServiceModel productServiceModel) {
        try {
            Product product = this.modelMapper.map(productServiceModel, Product.class);
            Product existProdcut = this.productRepository.findByName(productServiceModel.getName());

            if (existProdcut != null) {
                existProdcut.setAvailable(true);
                this.productRepository.save(existProdcut);
                return this.modelMapper.map(existProdcut, ProductServiceModel.class);
            }
            product.setAvailable(true);
            this.productRepository.save(product);

            return this.modelMapper.map(product, ProductServiceModel.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ProductServiceModel editProduct(ProductServiceModel productServiceModel) {
        try {
            this.productRepository.save(
                    this.modelMapper
                            .map(productServiceModel, Product.class));

            return productServiceModel;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void deleteById(String id) {
        var product = this.productRepository.findById(id);
        Product p = this.modelMapper.map(product.get(), Product.class);
        p.setAvailable(false);
        this.productRepository.save(p);
    }
}
