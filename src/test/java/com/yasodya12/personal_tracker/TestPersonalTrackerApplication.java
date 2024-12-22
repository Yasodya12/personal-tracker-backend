package com.yasodya12.personal_tracker;

import org.springframework.boot.SpringApplication;

public class TestPersonalTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.from(PersonalTrackerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
