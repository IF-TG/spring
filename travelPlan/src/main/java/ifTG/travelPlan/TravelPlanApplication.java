package ifTG.travelPlan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TravelPlanApplication {
	public static void main(String[] args) {
		SpringApplication.run(TravelPlanApplication.class, args);
	}

}
