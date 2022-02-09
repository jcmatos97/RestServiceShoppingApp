package com.shoppingapp.restservice;

import com.shoppingapp.restservice.models.Group;
import com.shoppingapp.restservice.models.User;
import com.shoppingapp.restservice.models.repositories.IGroupRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ShoppingAppApplication {

	@Autowired
	private IGroupRepository groupRepository;

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
 		if(groupRepository.count() == 0){
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


			};
		}
		return null;
	}
}

