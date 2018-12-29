package com.annuaire.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.annuaire.stage.model.Role;
import com.annuaire.stage.model.RoleName;
import com.annuaire.stage.repository.RoleRepository;


@SpringBootApplication
/*@ComponentScan({    
    "com.annuaire.stage.controller.UserController",
    "com.annuaire.stage.controller.AuthController",
    "com.annuaire.stage.controller.AnnuaireController"
})*/
public class StageApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(StageApplication.class, args);
	}
	
	@Autowired
    private RoleRepository roleRepository;
	
	@Override
	 public void run(String... args) throws Exception {
		
		Role role = new Role();
		role.setName(RoleName.ROLE_ADMIN);
		role = roleRepository.save(role);
		role = new Role();
	    role.setName(RoleName.ROLE_USER);
	    role = roleRepository.save(role);
		
	}
}
