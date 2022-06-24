package Product.Cart.demo.controller;

import Product.Cart.demo.model.Cart;
import Product.Cart.demo.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getAllCart() {
        List<Cart> carts = cartService.findAll();
        cartService.convertToJson(carts);
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<Cart>> addCart(@RequestBody Cart cart) {
        return cartService.save(cart);
    }

}
