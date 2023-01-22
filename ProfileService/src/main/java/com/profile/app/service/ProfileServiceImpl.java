package com.profile.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.profile.app.model.UserProfile;
import com.profile.app.repository.ProfileRepository;


@Service
public class ProfileServiceImpl implements ProfileService{

	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// Add new profile 
	public UserProfile addNewCustomerProfile(UserProfile userProfile) {

		String encodedPassword = passwordEncoder.encode(userProfile.getPassword());
		userProfile.setEncodedPassword(encodedPassword);
		return profileRepository.save(userProfile);
		
	}
	
	//Get all the profiles
	public List<UserProfile> getAllProfiles() {
		return profileRepository.findAll();
	}

	public UserProfile getByProfileId(String userId) {
		Optional<UserProfile> userOptional = profileRepository.findById(userId);
		return userOptional.get();
	}

	public UserProfile updateProfile(UserProfile userProfile,String userId) {
		
		UserProfile userProfileDb = profileRepository.findById(userId).get();
		
		if(userProfile.getFullName() != null) {
			userProfileDb.setFullName(userProfile.getFullName());
		}
		if(userProfile.getEmail() != null) {
			userProfileDb.setEmail(userProfile.getEmail());
		}
		
		if(userProfile.getMobileNo() != null) {
			userProfileDb.setMobileNo(userProfile.getMobileNo());
		}
		
		if(userProfile.getDateOfBirth() != null) {
			userProfileDb.setDateOfBirth(userProfile.getDateOfBirth());
		}
		
		if(userProfile.getGender() != null) {
			userProfileDb.setGender(userProfile.getGender());
		}
		
		if(userProfile.getPassword() != null) {
			userProfileDb.setPassword(userProfile.getPassword());
		}
		
		if(userProfile.getAddress().getHouseNumber() != 0) {
			userProfileDb.getAddress().setHouseNumber(userProfile.getAddress().getHouseNumber());
		}
		
		if(userProfile.getAddress().getStreetName() != null) {
			userProfileDb.getAddress().setStreetName(userProfile.getAddress().getStreetName());
		}
		
		if(userProfile.getAddress().getColonyName() != null) {
			userProfileDb.getAddress().setColonyName(userProfile.getAddress().getColonyName());
		}
		
		if(userProfile.getAddress().getCity() != null) {
			userProfileDb.getAddress().setCity(userProfile.getAddress().getCity());
		}
		
		if(userProfile.getAddress().getState() != null) {
			userProfileDb.getAddress().setState(userProfile.getAddress().getState());
		}
		
		if(userProfile.getAddress().getPinCode() != null) {
			userProfileDb.getAddress().setPinCode(userProfile.getAddress().getPinCode());
		}
		
		return profileRepository.save(userProfileDb);
	}
	
	public String deleteProfile(String userId) {
		profileRepository.deleteById(userId);
		return "Deleted Succesfully";
	}

	public List<UserProfile> findByMobileNo(Long mobileNo) {
		
		return profileRepository.findByMobileNo(mobileNo);
	}

	public List<UserProfile> findByEmail(String email) {
		
		return profileRepository.findByEmail(email);
	}

	
	
	
}
