package com.bookhive.ufcg.bookhive.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookhive.ufcg.bookhive.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {


}

//	private Map<String, User> users;
//
//	public UserRepository() {
//		this.users = new HashMap<String, User>();
//	}
//
//	public Collection<User> getAll() {
//		return users.values();
//	}
//
//	public void addUser(User user) {
//		this.users.put(user.getUsername(), user);
//	}
//
//	public User getUser(String username) {
//		return this.users.get(username);
//	}
//
//	public void removeUser(String username) {
//		this.users.remove(username);
//	}
//
//	public void updateUser(String username, String firstName, String lastName) {
//		User user = this.getUser(username);
//		user.setFirstName(firstName);
//		user.setLastName(lastName);
//	}
//
//	public boolean hasUser(String username) {
//		return this.users.containsKey(username);
//	}
//
//	public List<String> listUsers() {
//		Collection<User> users = this.users.values();
//		List<String> user_list = new ArrayList<String>();
//		for (User user : users) {
//			String info = ("username: " + user.getUsername() + " Nome: " + user.getFullName());
//			user_list.add(info);
//		}
//
//		return user_list;
//	}
