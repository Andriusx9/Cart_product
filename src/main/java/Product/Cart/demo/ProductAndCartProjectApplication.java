package Product.Cart.demo;

import Product.Cart.demo.model.Cart;
import Product.Cart.demo.model.Product;
import Product.Cart.demo.repository.CartRepository;
import Product.Cart.demo.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
public class ProductAndCartProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductAndCartProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner constructCommandLineRunner(final CartRepository cartRepository,
														final ProductRepository productRepository) {
		return args -> {

			Cart firstCart = Cart.builder()
					.created(new Timestamp(new Date().getTime()))
					.build();

			cartRepository.save(firstCart);

			Product lenovoPc = Product.builder()
					.name("Lenovo Legion Y7000P")
					.description("Gaming laptop 2022")
					.price(1250.99)
					.cart(firstCart)
					.build();

			productRepository.save(lenovoPc);
		};
	}

}
