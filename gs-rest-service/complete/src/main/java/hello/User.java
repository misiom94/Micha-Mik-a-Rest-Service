package hello;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private long id;
	private String login;
	private String password;
	private static List<User> userList = new ArrayList<>();
	private static long counter = 0;
	
	
	public User(){
		
	}
	
	public User(String login, String password) {

		this.login = login;
		this.password = password;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getId() {
		return id;
	}
	public static List<User> getUserList() {
		return userList;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public static void addUser(User user){
		user.setId(counter++);
		userList.add(user);
	}
	
	public static void fillUserList(){
		
		addUser(new User("user","qwerty"));
		addUser(new User("user1","qwerty"));
		addUser(new User("user2","qwerty"));
		
	}
	
	public static User getUserById(int id){
		for(User user:userList){
			if(user.getId()==id){
				return user;
			}
		}
		return null;
	}
	
	public static void deleteUserById(int id){
		Integer idToRemove = null;
		for(User user:userList){
			if(user.getId()==id){
				idToRemove = id;
			}
		}
		if(idToRemove != null){
			userList.remove(User.getUserById(idToRemove));
		}
	}
	
	public static String printUserList(){
		String list="";
		if(!userList.isEmpty()){
			for(User user: userList){
				list+=user.toString() + "\n";
		}
			return list;
		
	}
		return "NO USERS IN DATABASE";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + "]";
	}
	
	
	
	

}
