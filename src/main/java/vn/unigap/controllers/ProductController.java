package vn.unigap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.unigap.entities.Product;
import vn.unigap.services.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts(
            @RequestParam("limit") Optional<Integer> limit,
            @RequestParam("page") Optional<Integer> pages
    ) {
        List<Product> result;
        if (limit.isEmpty() || pages.isEmpty()) {
            result = productService.getAllProducts();
        } else {
            result = productService.getProductsByPage(limit.get(), pages.get());
        }
        return result;
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/search")
    public List<Product> searchProductsByTitle(@RequestParam(name="q") String searchStr) {
        return productService.searchProductsByTitle(searchStr);
    }

    @GetMapping("/categories")
    public Set<String> getAllCategories() {
        return productService.getAllCategories();
    }

    @GetMapping("/category/{categoryName}")
    public List<Product> getProductsOfACategory(@PathVariable String categoryName) {
        return productService.getProductsOfACategory(categoryName);
    }

//    @GetMapping()
//    public List<Product> getProductsByPage(
//            @RequestParam(name = "limit") int limit,
//            @RequestParam(name = "page") int page) {
//        return productService.getProductsByPage(limit, page);
//    }

    @PostMapping("/add")
    public void addProduct(@RequestBody Product product) {
//        System.out.println(product.getId() + " " + product.getTitle());
        productService.addProduct(product);
    }

    @PutMapping("/{productId}")
    public void updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
        productService.updateProduct(productId, updatedProduct);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }
}
