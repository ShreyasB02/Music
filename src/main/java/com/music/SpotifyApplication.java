package com.music;

//import org.springframework.beans.factory.annotation.Autowired;
import com.music.Repository.songRepo.SongRepository;
import com.music.Repository.userRepo.UserRepository;
import com.music.models.Song;
import com.music.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContext;

import com.music.services.StorageService;

import java.util.List;

@Slf4j
@SpringBootApplication
public class SpotifyApplication implements CommandLineRunner {

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

	@Override
	public void run(String... args) throws Exception {
		log.info("************************************************************");
		log.info("Start creating and printing mongo objects");
		log.info("************************************************************");

//		this.model1Repository.save(new User(null,"Model 1 obj"));
//		this.model2Repository.save(new Song(null,"Model 2 Obj"));
//
//		List<User> model1s = this.model1Repository.findAll();
//		for (Model1 model1obj : model1s) {
//			log.info(model1obj.toString());
//		}
//
//
//		List<Model2> model2s = this.model2Repository.findAll();
//		for (Model2 model2obj : model2s) {
//			log.info(model2obj.toString());
//		}

		log.info("************************************************************");
		log.info("Ended printing mongo objects");
		log.info("************************************************************");

	}
}