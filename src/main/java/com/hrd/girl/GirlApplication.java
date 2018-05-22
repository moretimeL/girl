package com.hrd.girl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaAuditing   //自动生成日期

public class GirlApplication {

	@Bean
	public StartupRunner schedulerRunner() {
		return new StartupRunner();
	}
	public static void main(String[] args) {
		SpringApplication.run(GirlApplication.class, args);
	}
}