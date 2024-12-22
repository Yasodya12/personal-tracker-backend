package com.yasodya12.personal_tracker;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class PersonalTrackerApplicationTests {

	@Test
	void contextLoads() {
	}

}
