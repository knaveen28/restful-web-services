package com.in28minutes.rest.webservices.restfulwebservcies.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	
	private static int usersCount=0;
	
	static {
		users.add(new User(++usersCount, "naveen", LocalDate.now().minusYears(25)));
		users.add(new User(++usersCount, "venkat", LocalDate.now().minusYears(20)));
		users.add(new User(++usersCount, "phani", LocalDate.now().minusYears(35)));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User findOne(int id){
		Predicate<? super User> Predicate = user -> user.getId().equals(id);
		return users.stream().filter(Predicate).findFirst().orElse(null);
		
	}
	
	public User save(User user) {
		
		user.setId(++usersCount);
		users.add(user);
		return user;
		
	}
	
	

}
