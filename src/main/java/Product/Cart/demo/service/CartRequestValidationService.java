package Product.Cart.demo.service;

import Product.Cart.demo.model.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartRequestValidationService {

    private static Logger LOGGER = LoggerFactory.getLogger(CartRequestValidationService.class);

    public void validateRequest(Cart cart) {
        LOGGER.info("Validating request.");

        List<String> missingFields = new ArrayList<>();

    }
}
