package com.ecommerce.library.service;

import com.ecommerce.library.dto.ProductDto;
import com.ecommerce.library.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    /*Admin*/
    List<ProductDto> findAll();
    Product save(MultipartFile imageProduct, ProductDto productDto);
    Product update(MultipartFile imageProduct, ProductDto productDto);
    void deleteById(Long id);
    void enableById(Long id);
    ProductDto getById(Long id);

    Page<ProductDto> pageProducts(int pageNo);

    Page<ProductDto> searchProducts(int pageNo, String keyword);


    /*Customer*/
    List<Product> getAllProducts();

    List<Product> listViewProducts();

    Product getProductById(Long id);

    List<Product> getRelatedProducts(Long categoryId);

    List<Product> getProductsInCategory(Long categoryId);

    List<Product> filterHighPrice();

    List<Product> filterLowPrice();
}
