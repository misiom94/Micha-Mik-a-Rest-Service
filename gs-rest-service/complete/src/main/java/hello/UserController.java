package hello;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private static final String template = "Welcome on homepage!";
	private static final String wrongUUIDMsg = "Wrong UUID or Session lost !";
	private static final int sessionDuration = 30;
	private final AtomicLong counter = new AtomicLong();
	private static Map<UUID, LocalDateTime> uuidTime = new HashMap<>();
	private static Map<UUID, User> uuidUser = new HashMap<>();

	@RequestMapping("/home")
	public String homepage() {
		return template;
	}

	@RequestMapping("/content")
	public String content(@RequestParam(value = "uuid", defaultValue = "") String uuid) {
		if (!checkUuidTime(UUID.fromString(uuid))) {
			return "SESSION LOST!";
		}

		return "This is your secret webpage!";

	}

	@PostMapping("/login")
	public String login(@RequestBody User user) {
		if (!checkUser(user)) {
			return "Bad login or password!";
		}
		UUID uuid = UUID.randomUUID();
		uuidTime.put(uuid, LocalDateTime.now());
		uuidUser.put(uuid, user); 
		return uuid.toString();
	}

	public boolean checkUser(User user) {
		if (User.getUserList().contains(user)) {
			return true;
		}
		return false;
	}

	@RequestMapping("/getUserById")
	public String getUserById(@RequestParam(value = "uuid", defaultValue = "") String uuid,
			@RequestParam(value = "id", defaultValue = "") String id) {
		if (!checkUuidTime(UUID.fromString(uuid))) {
			return wrongUUIDMsg;
		}
		User user = User.getUserById(Integer.parseInt(id));
		if (user == null) {
			return "Wrong ID, user doesnt EXISTS!";
		}
		return user.toString();

	}

	@RequestMapping("/getAllUsers")
	public String getAllUsers(@RequestParam(value = "uuid", defaultValue = "") String uuid) {
		if (!checkUuidTime(UUID.fromString(uuid))) {
			return wrongUUIDMsg;
		}
		return User.printUserList();

	}

	@DeleteMapping(value = "/delete")
	public String deleteUserById(@RequestParam(value = "uuid", defaultValue = "") String uuid,
			@RequestParam(value = "id", defaultValue = "") String id) {
		String result = checkDeleteUser(uuid, id);
		if(result != "OK"){
			return result;
		}
		User.deleteUserById(Integer.parseInt(id));
		return "USER DELETED!";
	}
	
	private String checkDeleteUser(String stringUUID, String id){
		UUID uuid = UUID.fromString(stringUUID);
		int idToCheck = Integer.parseInt(id);
		if (!checkUuidTime(uuid)) {
			return wrongUUIDMsg;
		}
		if (User.getUserById(idToCheck) == null) {
			return "Wrong ID, user doesnt EXISTS!";
		}
		if (uuidUser.get(uuid).getId() == idToCheck){
			return "Cant delete user logged now";
		}
		return "OK";
	}

	public boolean checkUuidTime(UUID uuid) {
		LocalDateTime loginTime;
		LocalDateTime sessionTime;
		try {
			loginTime = uuidTime.get(uuid);
			sessionTime = loginTime.plusMinutes(sessionDuration);
		} catch (Exception e) {
			return false; 
		}
		if (LocalDateTime.now().isAfter(sessionTime)) {
			return false;
		}
		return true;

	}
}