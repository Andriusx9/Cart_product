package Product.Cart.demo.service;

import Product.Cart.demo.exception.ProductRequestValidationException;
import Product.Cart.demo.model.Product;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductRequestValidationService {

    public static final String NAME = "Name";
    public static final String DESCRIPTION = "Description";
    private static Logger LOGGER = LoggerFactory.getLogger(ProductRequestValidationService.class);

    public void validateRequest(Product product) throws ProductRequestValidationException {
        LOGGER.info("Validating request.");

        List<String> missingFields = new ArrayList<>();

        if(StringUtils.isEmpty(product.getName())) {
            missingFields.add(NAME);
        }
        if(StringUtils.isEmpty(product.getDescription())) {
            missingFields.add(DESCRIPTION);
        }

        if(!missingFields.isEmpty()) {
            throw new ProductRequestValidationException("Request can not be submitted dua to missing fields " + missingFields);
        }

        LOGGER.info("Library request validation was good");
    }
}
