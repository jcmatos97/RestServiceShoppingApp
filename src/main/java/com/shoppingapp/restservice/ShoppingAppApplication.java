package com.shoppingapp.restservice;

import com.shoppingapp.restservice.models.Category;
import com.shoppingapp.restservice.models.Group;
import com.shoppingapp.restservice.models.User;
import com.shoppingapp.restservice.models.repositories.ICategoryRepository;
import com.shoppingapp.restservice.models.repositories.IGroupRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ShoppingAppApplication {

	@Autowired
	private IGroupRepository groupRepository;
	@Autowired
	private ICategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(ShoppingAppApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("*");
			}
		};
	}

	@Bean
	InitializingBean dataSeed() {
 		if((groupRepository.count() == 0)&&(categoryRepository.count() == 0)){
			return () -> {
			Group g1 = new Group();
			g1.setName("Products");
			g1.setStatus(true);

			Group g2 = new Group();
			g2.setName("Transactions");
			g2.setStatus(true);

			Group g3 = new Group();
			g3.setName("Users");
			g3.setStatus(true);

			Group savedG1 = groupRepository.save(g1);
			Group savedG2 = groupRepository.save(g2);
			Group savedG3 = groupRepository.save(g3);

			List<Category> categoryList = new ArrayList<>();

			categoryList.add(new Category());
			categoryList.get(0).setGroup(savedG2);
			categoryList.get(0).setName("Shopping Cart");
			categoryList.get(0).setDescription("Category used to store a user's wish list");
			categoryList.get(0).setStatus(true);

			categoryList.add(new Category());
			categoryList.get(1).setGroup(savedG2);
			categoryList.get(1).setName("Sell Transaction");
			categoryList.get(1).setDescription("Category used to tag a Sell transaction (when some customer buy a product)");
			categoryList.get(1).setStatus(true);

			categoryList.add(new Category());
			categoryList.get(2).setGroup(savedG2);
			categoryList.get(2).setName("Purchase Transaction");
			categoryList.get(2).setDescription("Category used to tag a Purchase transaction (when application purchase products to provider)");
			categoryList.get(2).setStatus(true);

			categoryList.add(new Category());
			categoryList.get(3).setGroup(savedG3);
			categoryList.get(3).setName("Administrator");
			categoryList.get(3).setDescription("Manages the inventory of the application");
			categoryList.get(3).setStatus(true);

			categoryList.add(new Category());
			categoryList.get(4).setGroup(savedG3);
			categoryList.get(4).setName("Customer");
			categoryList.get(4).setDescription("Buys the products of the application");
			categoryList.get(4).setStatus(true);

			categoryList.add(new Category());
			categoryList.get(5).setGroup(savedG3);
			categoryList.get(5).setName("Provider");
			categoryList.get(5).setDescription("Category used to group the users who provide products to inventory, these users never enter the applicaction, only to appear in purchase transactions giving sense to transaction records");
			categoryList.get(5).setStatus(true);

			categoryList.forEach(category -> {
				categoryRepository.save(category);
			});

			};
		}
		return null;
	}
}

