package Product.Cart.demo.service;

import Product.Cart.demo.model.Cart;
import Product.Cart.demo.model.Product;
import Product.Cart.demo.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    private static Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void convertToJson(final List<Product> products) {
        LOGGER.info("Generating products.json ....");
        try {
            objectMapper.writeValue(new File("Products.json"), products);
            LOGGER.info("File was generated successfully!");
        } catch (IOException e) {
            LOGGER.error("Could not generate json file");
            e.printStackTrace();
        }
    }

    public ResponseEntity<List<Product>> save(final Product product) {
        LOGGER.info("Saving product to database...");
        productRepository.save(product);
        List<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Product>> findByName(final String name) {
        List<Product> products = productRepository.findByName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    public ResponseEntity<Product> getProductById(final Integer id) {
        Product product = productRepository.findAll().get(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
