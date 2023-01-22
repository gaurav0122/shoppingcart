package com.profile.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.profile.app.controller.ProfileServiceController;
import com.profile.app.model.Address;
import com.profile.app.model.UserProfile;
import com.profile.app.service.ProfileService;


@SpringBootTest
class ProfileServiceApplicationTests {

	@InjectMocks
	ProfileServiceController profileServiceController;
	
	@Mock
	ProfileService profileService;
	
	UserProfile userProfile;
	
	Address address;
	
	List<UserProfile> listUser;
	
	private final String userId = "1234";
	
	private final Long MobileNo = 1231231235L;
	
	private final String emailId = "gaurav@gamil.com";
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		address = new Address();
		address.setHouseNumber(12);
		address.setColonyName("ABC colony");
		address.setCity("Pune");
		address.setPinCode(413133L);
		address.setState("Maharashtra");
		address.setStreetName("xyz street");
		
		
		userProfile = new UserProfile();
		userProfile.setId(userId);
		userProfile.setFullName("gaurav");
		userProfile.setEmail("gaurav@gamil.com");
		userProfile.setDateOfBirth(LocalDate.of(2011, 11, 21));
		userProfile.setGender("Male");
		userProfile.setMobileNo(1231231235L);
		userProfile.setRole("Customer");
		userProfile.setAddress(address);
		userProfile.setPassword("gaurav123");
		
		
		listUser = new ArrayList<UserProfile>();
		
		listUser.add(userProfile);
		
	}
	
	
	
	@Test
	@Order(1)
	void addUserProfileNotNull() {
		assertNotNull(userProfile,"user is null");
	}
	
	@Test
	@Order(2)
	void addUserFullNameNotNull() {
		assertNotNull(userProfile.getFullName(),"Full name required");
	}
	
	@Test
	@Order(3)
	void addUserEmailNotNull() {
		assertNotNull(userProfile.getEmail(),"Email is required");
	}
	
	@Test
	@Order(4)
	void addUserMobileNoNotNull() {
		assertNotNull(userProfile.getMobileNo(),"Mobile Number is required");
	}
	
	@Order(5)
	@Test
	void addUserAddressNotNull() {
		assertNotNull(userProfile.getAddress(),"Address is required");
	}
	
	@Order(6)
	@Test
	void addUserPasswordNotNull() {
		assertNotNull(userProfile.getPassword(),"Password is required");
	}
	
	@Order(7)
	@Test
	void addUserRoleNotNull() {
		assertNotNull(userProfile.getRole(),"Role is required");
	}
	
	@Order(8)
	@Test
	void addUserMobileNoLenght() {
			assertEquals(10, userProfile.getMobileNo().toString().length(),"Mobile no lenght is not 10");
	}

	@Order(9)
	@Test
	void addUserCheck() {
		
		// get the userProfile by when then method
		when(profileService.addNewCustomerProfile(any(UserProfile.class))).thenReturn(userProfile);
		
		UserProfile userRest = profileServiceController.addNewCustomerProfile(userProfile);
		assertNotNull(userRest);

	}

	@Order(10)
	@Test
	void addUserFullNameCheck() {
		
		// get the userProfile by when then method
		when(profileService.addNewCustomerProfile(any(UserProfile.class))).thenReturn(userProfile);
		
		UserProfile userRest = profileServiceController.addNewCustomerProfile(userProfile);
		
		assertEquals(userProfile.getEmail(), userRest.getEmail());
	}

	@Order(11)
	@Test
	void addUserMobileNoCheck()  {
		
		// get the userProfile by when then method
		when(profileService.addNewCustomerProfile(any(UserProfile.class))).thenReturn(userProfile);
		
		UserProfile userRest = profileServiceController.addNewCustomerProfile(userProfile);
		
		assertEquals(userProfile.getMobileNo(), userRest.getMobileNo());
	
	}
	
	@Order(12)
	@Test
	void addUserAddressCheck()  {
		
		// get the userProfile by when then method
		when(profileService.addNewCustomerProfile(any(UserProfile.class))).thenReturn(userProfile);
		
		UserProfile userRest = profileServiceController.addNewCustomerProfile(userProfile);
		
		assertEquals(userProfile.getAddress(), userRest.getAddress());
	}
	
	@Order(13)
	@Test
	void addUserRoleCheck()  {
		
		// get the userProfile by when then method
		when(profileService.addNewCustomerProfile(any(UserProfile.class))).thenReturn(userProfile);
		
		UserProfile userRest = profileServiceController.addNewCustomerProfile(userProfile);
	
		assertEquals(userProfile.getRole(), userRest.getRole());
	}
	
	@Order(14)
	@Test
	void addUserEmailCheck() {
		
		// get the userProfile by when then method
		when(profileService.addNewCustomerProfile(any(UserProfile.class))).thenReturn(userProfile);
		
		UserProfile userRest = profileServiceController.addNewCustomerProfile(userProfile);
		
		assertEquals(userProfile.getFullName(), userRest.getFullName());
	}

	@Order(15)
	@Test
	void getUserById() {
		when(profileService.getByProfileId(userId)).thenReturn(userProfile);
		
		UserProfile userRest = profileServiceController.getByProfileId(userId);
		
		assertNotNull(userRest,"User not available in DB");
		
		assertEquals(userProfile.getId(), userRest.getId());
		
	}
	
	@Order(16)
	@Test
	void updateUser() {
		
		when(profileService.updateProfile(any(UserProfile.class),anyString())).thenReturn(userProfile);
		
		UserProfile userRest = profileServiceController.updateProfile(userProfile, userId);
		
		assertNotNull(userRest,"User not available in DB id incorrect");
		
		assertEquals(userProfile.getId(), userRest.getId());
		
	}
	
// need to validate in before deleting that user is present or not in db
	@Order(17)
	@Test
	void deleteUser() throws Exception{
		
		when(profileService.deleteProfile(userId)).thenReturn("Deleted Succesfully");
		
		when(profileService.getByProfileId(userId)).thenReturn(userProfile);

		String delete = profileServiceController.deleteProfile(userId);
		
		assertEquals("Deleted Succesfully", delete,"user not available in db");
		
		
		
	}
	
	@Order(18)
	@Test
	void findByMobileNo() {
		
		when(profileService.findByMobileNo(MobileNo)).thenReturn(listUser);
		
		UserProfile profile = profileServiceController.findByMobileNo(MobileNo);
	
		assertNotNull(profile);
		
		assertEquals(profile.getMobileNo(), 1231231235L);
		
	}
	
	@Order(19)
	@Test
	void findByEmailId() {
		
		when(profileService.findByEmail(emailId)).thenReturn(listUser);
		
		UserProfile profile = profileServiceController.findByEmail(emailId);

		assertNotNull(profile);
		
		assertEquals(profile.getEmail(), "gaurav@gamil.com");
		
	}
	
	
	
	
	
	
}
