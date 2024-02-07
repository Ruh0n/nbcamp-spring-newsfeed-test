package com.ptjcoding.nbcampspringnewsfeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing()
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@PropertySource(value = {"classpath:db.properties"})
public class NbcampSpringNewsfeedApplication {

  public static void main(String[] args) {
    SpringApplication.run(NbcampSpringNewsfeedApplication.class, args);
  }

}
