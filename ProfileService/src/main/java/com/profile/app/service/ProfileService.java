package com.profile.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;


import com.profile.app.model.UserProfile;


public interface ProfileService {

	public UserProfile addNewCustomerProfile(UserProfile userProfile);
	
	public List<UserProfile> getAllProfiles();
	
	public UserProfile getByProfileId(String Id);
	
	public UserProfile updateProfile(UserProfile userProfile,String userId);
	
	public String deleteProfile(String userId);
	
	public List<UserProfile> findByMobileNo(Long mobileNo);
	
	public List<UserProfile> findByEmail(String email);
	
	
}

