
package io.github.lvbo.learn.spring.boot.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


/**
 * @author lvbo
 */
@SpringBootApplication
@PropertySource(value = {"classpath:swagger.properties"}, encoding="utf-8")
public class SwaggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwaggerApplication.class, args);
	}

}
