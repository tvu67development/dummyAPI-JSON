package vn.unigap.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.unigap.entities.Product;
import vn.unigap.services.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    // In-memory product list (for demonstration purposes)
    private List<Product> productList = new ArrayList<>();

    @Override
    public List<Product> getAllProducts() {
        String dummyJSON = "[\n" +
                "{\n" +
                "    \"id\": 1,\n" +
                "    \"title\": \"iPhone 9\",\n" +
                "    \"description\": \"An apple mobile which is nothing like apple\",\n" +
                "    \"price\": 549,\n" +
                "    \"discountPercentage\": 12.96,\n" +
                "    \"rating\": 4.69,\n" +
                "    \"stock\": 94,\n" +
                "    \"brand\": \"Apple\",\n" +
                "    \"category\": \"smartphones\",\n" +
                "    \"thumbnail\": \"https://i.dummyjson.com/data/products/1/thumbnail.jpg\",\n" +
                "    \"images\": [\n" +
                "        \"https://i.dummyjson.com/data/products/1/1.jpg\",\n" +
                "        \"https://i.dummyjson.com/data/products/1/2.jpg\",\n" +
                "        \"https://i.dummyjson.com/data/products/1/3.jpg\",\n" +
                "        \"https://i.dummyjson.com/data/products/1/4.jpg\",\n" +
                "        \"https://i.dummyjson.com/data/products/1/thumbnail.jpg\"\n" +
                "    ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"title\": \"iPhone X\",\n" +
                "        \"description\": \"SIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with ...\",\n" +
                "        \"price\": 899,\n" +
                "        \"discountPercentage\": 17.94,\n" +
                "        \"rating\": 4.44,\n" +
                "        \"stock\": 34,\n" +
                "        \"brand\": \"Apple\",\n" +
                "        \"category\": \"smartphones\",\n" +
                "        \"thumbnail\": \"https://i.dummyjson.com/data/products/2/thumbnail.jpg\",\n" +
                "        \"images\": [\n" +
                "            \"https://i.dummyjson.com/data/products/2/1.jpg\",\n" +
                "            \"https://i.dummyjson.com/data/products/2/2.jpg\",\n" +
                "            \"https://i.dummyjson.com/data/products/2/3.jpg\",\n" +
                "            \"https://i.dummyjson.com/data/products/2/thumbnail.jpg\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 3,\n" +
                "        \"title\": \"Samsung Universe 9\",\n" +
                "        \"description\": \"Samsung's new variant which goes beyond Galaxy to the Universe\",\n" +
                "        \"price\": 1249,\n" +
                "        \"discountPercentage\": 15.46,\n" +
                "        \"rating\": 4.09,\n" +
                "        \"stock\": 36,\n" +
                "        \"brand\": \"Samsung\",\n" +
                "        \"category\": \"smartphones\",\n" +
                "        \"thumbnail\": \"https://i.dummyjson.com/data/products/3/thumbnail.jpg\",\n" +
                "        \"images\": [\n" +
                "            \"https://i.dummyjson.com/data/products/3/1.jpg\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 4,\n" +
                "        \"title\": \"OPPOF19\",\n" +
                "        \"description\": \"OPPO F19 is officially announced on April 2021.\",\n" +
                "        \"price\": 280,\n" +
                "        \"discountPercentage\": 17.91,\n" +
                "        \"rating\": 4.3,\n" +
                "        \"stock\": 123,\n" +
                "        \"brand\": \"OPPO\",\n" +
                "        \"category\": \"smartphones\",\n" +
                "        \"thumbnail\": \"https://i.dummyjson.com/data/products/4/thumbnail.jpg\",\n" +
                "        \"images\": [\n" +
                "            \"https://i.dummyjson.com/data/products/4/1.jpg\",\n" +
                "            \"https://i.dummyjson.com/data/products/4/2.jpg\",\n" +
                "            \"https://i.dummyjson.com/data/products/4/3.jpg\",\n" +
                "            \"https://i.dummyjson.com/data/products/4/4.jpg\",\n" +
                "            \"https://i.dummyjson.com/data/products/4/thumbnail.jpg\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 5,\n" +
                "        \"title\": \"Huawei P30\",\n" +
                "        \"description\": \"Huawei’s re-badged P30 Pro New Edition was officially unveiled yesterday in Germany and now the device has made its way to the UK.\",\n" +
                "        \"price\": 499,\n" +
                "        \"discountPercentage\": 10.58,\n" +
                "        \"rating\": 4.09,\n" +
                "        \"stock\": 32,\n" +
                "        \"brand\": \"Huawei\",\n" +
                "        \"category\": \"smartphones\",\n" +
                "        \"thumbnail\": \"https://i.dummyjson.com/data/products/5/thumbnail.jpg\",\n" +
                "        \"images\": [\n" +
                "            \"https://i.dummyjson.com/data/products/5/1.jpg\",\n" +
                "            \"https://i.dummyjson.com/data/products/5/2.jpg\",\n" +
                "            \"https://i.dummyjson.com/data/products/5/3.jpg\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 6,\n" +
                "        \"title\": \"MacBook Pro\",\n" +
                "        \"description\": \"MacBook Pro 2021 with mini-LED display may launch between September, November\",\n" +
                "        \"price\": 1749,\n" +
                "        \"discountPercentage\": 11.02,\n" +
                "        \"rating\": 4.57,\n" +
                "        \"stock\": 83,\n" +
                "        \"brand\": \"Apple\",\n" +
                "        \"category\": \"laptops\",\n" +
                "        \"thumbnail\": \"https://i.dummyjson.com/data/products/6/thumbnail.png\",\n" +
                "        \"images\": [\n" +
                "            \"https://i.dummyjson.com/data/products/6/1.png\",\n" +
                "            \"https://i.dummyjson.com/data/products/6/2.jpg\",\n" +
                "            \"https://i.dummyjson.com/data/products/6/3.png\",\n" +
                "            \"https://i.dummyjson.com/data/products/6/4.jpg\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 7,\n" +
                "        \"title\": \"Samsung Galaxy Book\",\n" +
                "        \"description\": \"Samsung Galaxy Book S (2020) Laptop With Intel Lakefield Chip, 8GB of RAM Launched\",\n" +
                "        \"price\": 1499,\n" +
                "        \"discountPercentage\": 4.15,\n" +
                "        \"rating\": 4.25,\n" +
                "        \"stock\": 50,\n" +
                "        \"brand\": \"Samsung\",\n" +
                "        \"category\": \"laptops\",\n" +
                "        \"thumbnail\": \"https://i.dummyjson.com/data/products/7/thumbnail.jpg\",\n" +
                "        \"images\": [\n" +
                "            \"https://i.dummyjson.com/data/products/7/1.jpg\",\n" +
                "            \"https://i.dummyjson.com/data/products/7/2.jpg\",\n" +
                "            \"https://i.dummyjson.com/data/products/7/3.jpg\",\n" +
                "            \"https://i.dummyjson.com/data/products/7/thumbnail.jpg\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 8,\n" +
                "        \"title\": \"Microsoft Surface Laptop 4\",\n" +
                "        \"description\": \"Style and speed. Stand out on HD video calls backed by Studio Mics. Capture ideas on the vibrant touchscreen.\",\n" +
                "        \"price\": 1499,\n" +
                "        \"discountPercentage\": 10.23,\n" +
                "        \"rating\": 4.43,\n" +
                "        \"stock\": 68,\n" +
                "        \"brand\": \"Microsoft Surface\",\n" +
                "        \"category\": \"laptops\",\n" +
                "        \"thumbnail\": \"https://i.dummyjson.com/data/products/8/thumbnail.jpg\",\n" +
                "        \"images\": [\n" +
                "            \"https://i.dummyjson.com/data/products/8/1.jpg\",\n" +
                "            \"https://i.dummyjson.com/data/products/8/2.jpg\",\n" +
                "            \"https://i.dummyjson.com/data/products/8/3.jpg\",\n" +
                "            \"https://i.dummyjson.com/data/products/8/4.jpg\",\n" +
                "            \"https://i.dummyjson.com/data/products/8/thumbnail.jpg\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 9,\n" +
                "        \"title\": \"Infinix INBOOK\",\n" +
                "        \"description\": \"Infinix Inbook X1 Ci3 10th 8GB 256GB 14 Win10 Grey – 1 Year Warranty\",\n" +
                "        \"price\": 1099,\n" +
                "        \"discountPercentage\": 11.83,\n" +
                "        \"rating\": 4.54,\n" +
                "        \"stock\": 96,\n" +
                "        \"brand\": \"Infinix\",\n" +
                "        \"category\": \"laptops\",\n" +
                "        \"thumbnail\": \"https://i.dummyjson.com/data/products/9/thumbnail.jpg\",\n" +
                "        \"images\": [\n" +
                "            \"https://i.dummyjson.com/data/products/9/1.jpg\",\n" +
                "            \"https://i.dummyjson.com/data/products/9/2.png\",\n" +
                "            \"https://i.dummyjson.com/data/products/9/3.png\",\n" +
                "            \"https://i.dummyjson.com/data/products/9/4.jpg\",\n" +
                "            \"https://i.dummyjson.com/data/products/9/thumbnail.jpg\"\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 10,\n" +
                "        \"title\": \"HP Pavilion 15-DK1056WM\",\n" +
                "        \"description\": \"HP Pavilion 15-DK1056WM Gaming Laptop 10th Gen Core i5, 8GB, 256GB SSD, GTX 1650 4GB, Windows 10\",\n" +
                "        \"price\": 1099,\n" +
                "        \"discountPercentage\": 6.18,\n" +
                "        \"rating\": 4.43,\n" +
                "        \"stock\": 89,\n" +
                "        \"brand\": \"HP Pavilion\",\n" +
                "        \"category\": \"laptops\",\n" +
                "        \"thumbnail\": \"https://i.dummyjson.com/data/products/10/thumbnail.jpeg\",\n" +
                "        \"images\": [\n" +
                "            \"https://i.dummyjson.com/data/products/10/1.jpg\",\n" +
                "            \"https://i.dummyjson.com/data/products/10/2.jpg\",\n" +
                "            \"https://i.dummyjson.com/data/products/10/3.jpg\",\n" +
                "            \"https://i.dummyjson.com/data/products/10/thumbnail.jpeg\"\n" +
                "        ]\n" +
                "    }\n" +
                "]";
        if (productList.isEmpty()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(dummyJSON);

                // Assuming your JSON data is an array of products
                productList = objectMapper.readValue(
                        jsonNode.toString(),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Product.class)
                );

                // Now 'productList' contains a list of Product objects
//                for (Product product : productList) {
//                    System.out.println(product.getTitle());
//                }
            } catch (IOException e) {
                System.out.println("Error on read JSON data");
                e.printStackTrace();
            }
        }
        return productList;
    }

    @Override
    public Product getProductById(Long productId) {
        return productList.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addProduct(Product product) {
        // Implementation to add a new product
        // You may want to generate a unique ID or handle other validations
//        System.out.println(product.getId() + " " + product.getTitle());\
        product.setId((long) (productList.size() + 1));
        productList.add(product);
//        System.out.println(productList.size());
    }

    @Override
    public void updateProduct(Long productId, Product updatedProduct) {
        // Implementation to update an existing product
        // You may want to validate the existence of the product
        productList.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .ifPresent(product -> {
                    // Update product fields based on updatedProduct
                    // Handle other update logic as needed
                    if (!updatedProduct.getTitle().trim().isEmpty()) {
                        product.setTitle(updatedProduct.getTitle());
                    }
                    if (!updatedProduct.getDescription().trim().isEmpty()) {
                        product.setTitle(updatedProduct.getDescription());
                    }
                    if (!updatedProduct.getBrand().trim().isEmpty()) {
                        product.setTitle(updatedProduct.getBrand());
                    }
                    if (!updatedProduct.getImages().isEmpty()) {
                        product.setImages(updatedProduct.getImages());
                    }

                    // Update other fields...
                });
    }

    @Override
    public void deleteProduct(Long productId) {
        // Implementation to delete a product by ID
        // You may want to validate the existence of the product
//        System.out.println(productId);
        productList.removeIf(product -> product.getId().equals(productId));
    }

    public List<Product> searchProductsByTitle(String searchStr) {
        return productList.stream().filter(product -> product.getTitle().toLowerCase().contains(searchStr.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Product> getProductsOfACategory(String category) {
        // Implement logic to retrieve products based on the specified category
        // You may use this parameter in your database query or any other data access logic
        // Return the list of products
//        System.out.println(category);
        return productList.stream()
                .filter(product -> product.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    @Override
    public Set<String> getAllCategories() {
        return productList.stream().map(Product::getCategory).collect(Collectors.toSet());
    }

    @Override
    public List<Product> getProductsByPage(int limit, int pageNumber) {
        int SKIP_PER_PAGE = 2;
        int startIndex = pageNumber*SKIP_PER_PAGE;
        int endIndex = Math.min(startIndex+limit, productList.size());
        if (startIndex < 0 || startIndex >= productList.size() || endIndex <= startIndex) {
            // Handle invalid pagination parameters
            return new ArrayList<>();
        }
        return productList.subList(startIndex, endIndex);
    }

}
