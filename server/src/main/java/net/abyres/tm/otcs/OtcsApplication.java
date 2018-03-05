package net.abyres.tm.otcs;

import com.canoo.platform.server.spring.DolphinPlatformApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@DolphinPlatformApplication
public class OtcsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtcsApplication.class, args);
	}
}
