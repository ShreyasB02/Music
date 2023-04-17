package com.music;


//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContext;

import com.music.services.StorageService;

@SpringBootApplication
public class SpotifyApplication {
	
	public static void main(String[] args) {
		ApplicationContext context =   SpringApplication.run(SpotifyApplication.class, args);
		StorageService storageService=context.getBean(StorageService.class);
		 storageService.getSongFileNames();
		//SpringApplication.run(SpotifyApplication.class, args);
		//storageService.getSongFileNames();
	}

}
