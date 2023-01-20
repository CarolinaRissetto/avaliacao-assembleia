package assembleia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class AssembleiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssembleiaApplication.class, args);
    }

}
