package Product.Cart.demo.service;

import Product.Cart.demo.model.Cart;
import Product.Cart.demo.repository.CartRepository;
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
public class CartService {

    private static Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public void convertToJson(final List<Cart> carts) {
        LOGGER.info("Generating cart.json ....");
        try {
            objectMapper.writeValue(new File("Cart.json"), carts);
            LOGGER.info("File was generated successfully!");
        } catch (IOException e) {
            LOGGER.error("Could not generate json file");
            e.printStackTrace();
        }
    }

    public ResponseEntity<List<Cart>> save(final Cart cart) {
        LOGGER.info("Saving cart to database...");
        cartRepository.save(cart);
        List<Cart> carts = cartRepository.findAll();
        return new ResponseEntity<>(carts, HttpStatus.CREATED);
    }

}
