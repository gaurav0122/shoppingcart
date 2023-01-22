package com.profile.app.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.profile.app.model.UserProfile;
import com.profile.app.repository.ProfileRepository;


@Service
public class SpringUserService implements UserDetailsService{

	@Autowired
	private ProfileRepository profileRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		/*
		 * First go to db and check wheter the username is present or not
		 * If present fetch the deatils
		 * If not present then throw  UsernameNotFoundException
		 */
		
		UserProfile userProfile = profileRepository.findByEmail(username).get(0);
		
		if(userProfile == null)
			throw new UsernameNotFoundException("user with email "+ username+ " not found");
		
		/*
		 * convert role to authority as spring user dosent accept string values it needs 
		 * list of authority as a parameter
		 */
		List<GrantedAuthority> list = new ArrayList<>();
		String role = userProfile.getRole();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
		list.add(authority);
		
		User springUser = new User(username, userProfile.getEncodedPassword(), list);
		
		return springUser;
	}

}
