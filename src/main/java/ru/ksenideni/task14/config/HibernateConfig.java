package ru.ksenideni.task14.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "ru.ksenideni.task14")
public class HibernateConfig {

}
