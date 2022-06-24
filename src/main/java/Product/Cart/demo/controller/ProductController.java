package Product.Cart.demo.controller;

import Product.Cart.demo.exception.ProductRequestValidationException;
import Product.Cart.demo.model.Product;
import Product.Cart.demo.service.ProductRequestValidationService;
import Product.Cart.demo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductRequestValidationService productRequestValidationService;

    public ProductController(ProductService productService, ProductRequestValidationService productRequestValidationService) {
        this.productService = productService;
        this.productRequestValidationService = productRequestValidationService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAll();
        productService.convertToJson(products);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
       return productService.getProductById(id);
    }

    @PostMapping
    public ResponseEntity<List<Product>> addProduct(@RequestBody Product product) throws ProductRequestValidationException {
        productRequestValidationService.validateRequest(product);
        return productService.save(product);
    }
}
