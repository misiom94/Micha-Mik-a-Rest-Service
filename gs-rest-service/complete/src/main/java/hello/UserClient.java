package hello;

import org.apache.log4j.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



public class UserClient {
	private static final int UUID_SIZE = 10;
	private static final Logger LOGGER = Logger.getRootLogger();
	private static final String HOST = "http://localhost:8080/";

	public Response userLogin(String login, String password) {
		Client client = ClientBuilder.newClient();
		User user = new User(login, password);
		Response response = client.target(HOST + "login").request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(user, MediaType.APPLICATION_JSON), Response.class);
		return response;
	}
	
	public Response userLogout(String uuid){
		Client client = ClientBuilder.newClient();
		Response response = client.target(HOST + "logout?uuid="+uuid).request(MediaType.APPLICATION_JSON)
				.delete();
		return response;
	}

	public Response home() {
		Client client = ClientBuilder.newClient();
		Response response = client.target(HOST + "home").request(MediaType.APPLICATION_JSON).get(Response.class);
		return response;

	}
	public Response content(String uuid) {
		Client client = ClientBuilder.newClient();
		Response response = client.target(HOST+"content?uuid="+uuid).request(MediaType.APPLICATION_JSON).get(Response.class);
		return response;

	}
	
	public Response getAllUsers(String uuid){
		Client client = ClientBuilder.newClient();
		Response response = client.target(HOST + "getAllUsers?uuid="+uuid).request(MediaType.APPLICATION_JSON).get(Response.class);
		return response;
	}
	
	public Response getUserById(String uuid, String id){
		Client client = ClientBuilder.newClient();
		Response response = client.target(HOST + "getUserById?uuid="+uuid+"&id="+id).request(MediaType.APPLICATION_JSON).get(Response.class);
		return response;
	}
	
	public Response deleteUserById(String uuid, String id){
		Client client = ClientBuilder.newClient();
		Response response = client.target(HOST + "delete?uuid="+uuid+"&id="+id).request(MediaType.APPLICATION_JSON).delete();
		return response;
	}
	
//	dopisac reszte meto z controllera

}
