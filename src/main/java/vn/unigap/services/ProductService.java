package vn.unigap.services;

import vn.unigap.entities.Product;

import java.util.List;
import java.util.Set;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Long productId);

    void addProduct(Product product);

    void updateProduct(Long productId, Product updatedProduct);

    void deleteProduct(Long productId);

    List<Product> searchProductsByTitle(String searchStr);

    List<Product> getProductsOfACategory(String category);

    Set<String> getAllCategories();

    List<Product> getProductsByPage(int limit, int pageNumber);
}