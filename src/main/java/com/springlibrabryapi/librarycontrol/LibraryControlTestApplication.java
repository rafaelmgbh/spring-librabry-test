package com.springlibrabryapi.librarycontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LibraryControlTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryControlTestApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("123456"));
	}

}
