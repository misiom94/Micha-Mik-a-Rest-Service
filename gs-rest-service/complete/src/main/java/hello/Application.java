package hello;

import static org.assertj.core.api.Assertions.useRepresentation;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {
	private static final Logger LOGGER = Logger.getRootLogger();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        localhost:8080/swagger-ui.html
        String id = "2";
        User.fillUserList();
        UserClient userClient = new UserClient();
        LOGGER.info(userClient.home().readEntity(String.class));
        String uuid = userClient.userLogin("user", "qwerty").readEntity(String.class);
        String wrongUuid = "291ed8fb-aaaa-aaaa-aaaa-8714e48c3f7b";
        LOGGER.info(uuid);
        LOGGER.info(userClient.content(uuid).readEntity(String.class));
        LOGGER.info(userClient.content(wrongUuid).readEntity(String.class));
        LOGGER.info("\n" + userClient.getAllUsers(uuid).readEntity(String.class));
        LOGGER.info(userClient.getAllUsers(wrongUuid).readEntity(String.class));
        LOGGER.info(userClient.getUserById(uuid, id).readEntity(String.class));
        LOGGER.info(userClient.deleteUserById(uuid, id).readEntity(String.class));
        LOGGER.info(userClient.deleteUserById(uuid, "0").readEntity(String.class));
        LOGGER.info(userClient.getUserById(uuid, id).readEntity(String.class));
        LOGGER.info("\n" + userClient.getAllUsers(uuid).readEntity(String.class));
        
        
        
        
        
        
        
    }
}
