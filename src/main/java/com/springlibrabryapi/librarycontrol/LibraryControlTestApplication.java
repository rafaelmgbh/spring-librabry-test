package com.springlibrabryapi.librarycontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
public class LibraryControlTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryControlTestApplication.class, args);
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
		passwordEncoder.setDefaultPasswordEncoderForMatches(new Pbkdf2PasswordEncoder());

		String result = passwordEncoder.encode("123456");
		System.out.println("My hash " + result);

	}

}
