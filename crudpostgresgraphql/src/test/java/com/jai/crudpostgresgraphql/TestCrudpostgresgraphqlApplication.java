package com.jai.crudpostgresgraphql;

import org.springframework.boot.SpringApplication;

public class TestCrudpostgresgraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.from(CrudpostgresgraphqlApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
