package com.fico.kriti.explorer;

import com.fico.kriti.explorer.repositories.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.fico.kriti.explorer")
public class ExplorerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ExplorerApplication.class, args);
    }


    private static final Logger log = LoggerFactory.getLogger(ExplorerApplication.class);

    @Bean
    public CommandLineRunner demo(FileRepository repository) {
        return (args) -> {
            //Save files in db
            //repositories.save(new FileDetail());

            //fetch all files
			/*for (FileDetail file : repositories.findAll()) {
				log.info("Fruit is: " + file.getFileName());
			}*/

            //fetch a fruit By Id
			/*FileDetail file = repositories.findByFileId((long) 1);
			log.info(file.getFileName());*/
        };
    }

}