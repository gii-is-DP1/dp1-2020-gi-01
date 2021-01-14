package com.project.TabernasSevilla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.project.TabernasSevilla.configuration.ApplicationConfig;

//@Import({ApplicationConfig.class })
@SpringBootApplication
public class TabernasSevillaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TabernasSevillaApplication.class, args);
	}

}
