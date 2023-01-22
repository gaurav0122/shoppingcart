package com.profile.app.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.profile.app.jwtutil.JwtUtil;
import com.profile.app.model.AuthenticationRequest;
import com.profile.app.model.AuthenticationResponse;
import com.profile.app.model.UserProfile;
import com.profile.app.security.service.SpringUserService;
import com.profile.app.service.ProfileService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
//@CrossOrigin("http://localhost:4200/")	 
public class ProfileServiceController {

	@Autowired
	private ProfileService profileServiceImpl;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private SpringUserService springUserService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	Logger logger = LoggerFactory.getLogger(ProfileServiceController.class);
	
	
	@GetMapping("/user/login")
	public String loginUser() {
		return "User Logged";
	}
	
	@PostMapping("/autnenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try {
				authenticationManager.
					authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		}
		catch(BadCredentialsException e) {
			throw new Exception("Incorrect username or password");
		}
		
		final UserDetails userDetails = springUserService.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
		
	}
	
	@PostMapping("/user")
	@ApiOperation("This method is used to add profile in database of user/merchant as per role")
	public UserProfile addNewCustomerProfile(@RequestBody UserProfile userProfile)  {
		
		logger.trace("Add user profile method accesd");
		String check = null;

			List<UserProfile> mobileNo = profileServiceImpl.findByMobileNo(userProfile.getMobileNo());
			check = "mobile no is already present";
			if(mobileNo.isEmpty()) {
				check="Email is already present";
				List<UserProfile> email = profileServiceImpl.findByEmail(userProfile.getEmail());
				if(email.isEmpty()) {
					return profileServiceImpl.addNewCustomerProfile(userProfile);
				}
				logger.error(check);
			}
			logger.error(check);
			throw new RuntimeException(check);
		

	}

	
	@GetMapping("/user")
	@ApiOperation("This method is used to get all profiles")
	public List<UserProfile> getAllProfiles() {
		logger.trace("Get all user method accesed");
		List<UserProfile> profileList = profileServiceImpl.getAllProfiles();
		return profileList;
	}

	
	@GetMapping("/user/{userId}")
	@ApiOperation("This method is used to get profile as per the userId ")
	public UserProfile getByProfileId(@PathVariable("userId") String Id) {
		logger.trace("Get profile by ID method accessed");
		
		UserProfile userProfile = profileServiceImpl.getByProfileId(Id);
		return userProfile;
	}
	
	@GetMapping("/user1/{userId}")
	@ApiOperation("This method is used to get profile as per the userId ")
	public UserProfile getByProfileId1(@PathVariable("userId") String Id) {
		logger.trace("Get profile by ID method accessed");
		
		UserProfile userProfile = profileServiceImpl.getByProfileId(Id);
		return userProfile;
	}
	

	
	@PutMapping("/user/{userId}")
	@ApiOperation("This method is used to update user profile")
	public UserProfile updateProfile(@RequestBody UserProfile userProfile,
										@PathVariable("userId") String userId) {
		logger.trace("Update user method accessed");
		String check = null;
		
		List<UserProfile> mobileNo = profileServiceImpl.findByMobileNo(userProfile.getMobileNo());
		check = "This MobileNo is present with another account";
		if(mobileNo.isEmpty()) {
			check="This email is present with another account";
			List<UserProfile> email = profileServiceImpl.findByEmail(userProfile.getEmail());
			if(email.isEmpty()) {
				return profileServiceImpl.updateProfile(userProfile,userId);
			}
			logger.error(check);
		}
		logger.error(check);
		throw new RuntimeException(check);
	

		
	}

	
	@DeleteMapping("/user/{userId}")
	@ApiOperation("This method is used to delete user account by userId ")
	public String deleteProfile(@PathVariable("userId") String userId) {
		
		logger.trace("delete method accesed");
		try {
			UserProfile profile = profileServiceImpl.getByProfileId(userId);
		}
		catch(Exception exception) {
			logger.error("Profile with the given id is not present");
			return "user not found";
		}
		return profileServiceImpl.deleteProfile(userId);
	}

	
	@GetMapping("/user/mobileno/{mobileNo}")
	@ApiOperation("This method is used to get profile as per the mobileNo ")
	public UserProfile findByMobileNo(@PathVariable("mobileNo") Long mobileNo) {
		logger.trace("Get user by mobile number method accesed");
		try {
			UserProfile profile =  profileServiceImpl.findByMobileNo(mobileNo).get(0);
			return profile;

		} catch (Exception e) {
			
		}
		return null;
	}

	
	@GetMapping("/user/email/{email}")
	@ApiOperation("This method is used to get profile as per the Email id ")
	public UserProfile findByEmail(@PathVariable("email") String email) {
		logger.trace("Get user by email id method accesed");
		
		try {
			UserProfile profile = profileServiceImpl.findByEmail(email).get(0);
			return profile;
		} catch (Exception e) {
			 
		}
		return null;
	}
	
	@GetMapping("/user/email1/{email}")
	@ApiOperation("This method is used to get profile as per the Email id ")
	public UserProfile findByEmail1(@PathVariable("email") String email) {
		logger.trace("Get user by email id method accesed");
		
		try {
			UserProfile profile = profileServiceImpl.findByEmail(email).get(0);
			return profile;
		} catch (Exception e) {
			 
		}
		return null;
	}

	

}
