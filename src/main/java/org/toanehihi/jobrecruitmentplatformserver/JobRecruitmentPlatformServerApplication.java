package org.toanehihi.jobrecruitmentplatformserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.TimeZone;

@SpringBootApplication
public class JobRecruitmentPlatformServerApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure()
				.directory("./")
				.ignoreIfMissing()
				.load();
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

		String tz = System.getProperty("APP_TIMEZONE", "Asia/Ho_Chi_Minh");
		System.setProperty("user.timezone", tz);
		TimeZone.setDefault(TimeZone.getTimeZone(tz));

		SpringApplication.run(JobRecruitmentPlatformServerApplication.class, args);
	}
}
