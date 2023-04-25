package com.music;

//import org.springframework.beans.factory.annotation.Autowired;
import com.music.Repository.songRepo.SongRepository;
import com.music.Repository.userRepo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContext;

import com.music.services.StorageService;


@Slf4j
@SpringBootApplication
public class SpotifyApplication{

	@Autowired
	private UserRepository model1Repository;

	@Autowired
	private SongRepository model2Repository;

	public static void main(String[] args) {
		ApplicationContext context =   SpringApplication.run(SpotifyApplication.class, args);
		StorageService storageService=context.getBean(StorageService.class);
		System.out.println(storageService.getSongFileNames());
		//SpringApplication.run(SpotifyApplication.class, args);
		//storageService.getSongFileNames();
	}
}