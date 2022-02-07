package com.shoppingapp.restservice;

import com.shoppingapp.restservice.models.Group;
import com.shoppingapp.restservice.models.repositories.IGroupRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShoppingAppApplication {

	@Autowired
	private IGroupRepository groupRepository;

	public static void main(String[] args) {
		SpringApplication.run(ShoppingAppApplication.class, args);
	}

	@Bean
	InitializingBean groupSeed() {
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

			groupRepository.save(g1);
			groupRepository.save(g2);
			groupRepository.save(g3);
			};
		}
		return null;
	}
}

