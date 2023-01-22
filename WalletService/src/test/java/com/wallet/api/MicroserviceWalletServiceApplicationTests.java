package com.wallet.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.wallet.api.controller.EWalletServiceController;
import com.wallet.api.model.EWallet;
import com.wallet.api.model.Statement;
import com.wallet.api.service.EWalletService;

import io.swagger.annotations.ApiOperation;

@SpringBootTest
class MicroserviceWalletServiceApplicationTests {
	
	@InjectMocks
	EWalletServiceController ewalletController;
	
	@Mock
	EWalletService walletService;
	
	EWallet ewallet;
	
	Statement statement1;
	Statement statement2;
	
	List<EWallet> walletList;
	
	List<Statement> statementList;
	

	private final String walletId = "idw1";
	
	private final Double amountToBeAdded=500D;
	
	private final String userId="uid1";
	
	private final String userId1="uid2";

	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		
		statement1=new Statement();
		statement1.setStatementId("sid1");
		statement1.setAmount(2000D);
		statement1.setDate(LocalDate.of(2022, 06, 25));
		statement1.setOrderId(1);
		statement1.setTransactionRemarks("transaction remarks");
		statement1.setTransactionType("DEPOSIT");
		statement1.setEwalletId(walletId);
		
		statement2=new Statement();
		statement2.setStatementId("sid2");
		statement2.setAmount(1000D);
		statement2.setDate(LocalDate.of(2022, 06, 24));
		statement2.setOrderId(1);
		statement2.setTransactionRemarks("transaction remarks");
		statement2.setTransactionType("DEPOSIT");
		statement2.setEwalletId(walletId);
		
		ewallet=new EWallet();
		ewallet.setWalletId("idw1");
		ewallet.setCurrentBalance(1000D);
		ewallet.setUserId(userId);
		ewallet.setUserName("user");
		
		
		walletList = new ArrayList<EWallet>();
		statementList=new ArrayList<Statement>();
		
		statementList.add(statement1);
		statementList.add(statement2);
		
		walletList.add(ewallet);
		
	}
	
	@Test
	void addWalletChecked() {
		assertNotNull(ewallet,"ewallet is null");
	}
	
	@Test
	void addWalletCheck() {
		
		when(walletService.addWallet(ewallet,userId)).thenReturn(ewallet);
	
		EWallet walletRest = ewalletController.addWallet(ewallet,userId);
		
		assertNotNull(walletRest,"ewallet is null");
		
	}
	
	@Test
	void addWalletCurrentBalanceCheck() {
		
		when(walletService.addWallet(ewallet,userId)).thenReturn(ewallet);
	
		EWallet walletRest = ewalletController.addWallet(ewallet,userId);
		
		assertNotNull(walletRest.getCurrentBalance(),"currentBalance is null");
		
		assertEquals(1000D, walletRest.getCurrentBalance());
		
	}
	
	@Test
	void addWalletUserIDCheck() {
		
		when(walletService.addWallet(ewallet,userId)).thenReturn(ewallet);
	
		EWallet walletRest = ewalletController.addWallet(ewallet,userId);
		
		assertNotNull(walletRest.getUserId(),"currentBalance is null");
		
		assertEquals(userId, walletRest.getUserId());
	}
	
	@Test
	void addWalletUserNameCheck() {
		
		when(walletService.addWallet(ewallet,userId)).thenReturn(ewallet);
	
		EWallet walletRest = ewalletController.addWallet(ewallet,userId);
		
		assertNotNull(walletRest.getUserName(),"currentBalance is null");
		
		assertEquals(ewallet.getUserName(), walletRest.getUserName());
	}
	

	@Test
	 void getAllWallet() {
		
		when(walletService.getWallets()).thenReturn(walletList);//mocking
		
		List<EWallet> listOfWallets = ewalletController.getWallets();
		
		assertEquals(1,listOfWallets.size()); 
	
	}

	

	@Test
	void getWalletById() {
		when(walletService.getById(walletId)).thenReturn(ewallet);
		
		EWallet walletRest = ewalletController.getById(walletId);
		
		assertNotNull(walletRest,"wallet not available in DB");
		
		assertEquals(ewallet.getWalletId(), walletRest.getWalletId());
		
	}
	
	@Test
	void getStatements() {
		when(walletService.getStatements()).thenReturn(statementList);
		
		assertEquals(2, ewalletController.getStatements().size());
	}
	
	@Test
	void deleteById() {
        when(walletService.deleteById(walletId)).thenReturn("Deleted Succesfully");
		
		when(walletService.getById(walletId)).thenReturn(ewallet);

		String delete = ewalletController.deleteById(walletId);
		
		assertEquals("Deleted Succesfully", delete,"user not available in db");
		
	}
	
	@Test
	void addMoneyInWallet() {
		
		ewallet.setCurrentBalance(amountToBeAdded);
		
		when(walletService.addMoney(walletId,amountToBeAdded)).thenReturn(ewallet);
		when(walletService.getById(walletId)).thenReturn(ewallet);
		
		ewallet.setCurrentBalance(ewallet.getCurrentBalance()-amountToBeAdded); 
		
		EWallet walletRest = ewalletController.addMoney(walletId, amountToBeAdded);
		
		//assertNotNull(walletRest);
		
		assertEquals(walletRest.getCurrentBalance()+amountToBeAdded, ewallet.getCurrentBalance()+amountToBeAdded);
	}
	
	@Test
	void getStatementsByWalletId() {
		
		when(walletService.getStatementByWalletId(walletId)).thenReturn(statementList); 
		
		List<Statement> listStatement = ewalletController.getStatementsByWalletId(walletId);
		
		assertEquals(listStatement.size(),statementList.size());
	}
	
	@Test
	void GetwalletbyuserId() {
		
		when(walletService.getWalletByUserId(userId)).thenReturn(ewallet); 
		
		EWallet walletRest = ewalletController.getWalletByUserId(userId);
		
		assertNotNull(walletRest);
		
		assertEquals(ewallet.getUserId(),walletRest.getUserId());
	}
	
	@Test
	void payMoneyToAnotherAccount() throws Exception {
		
		when(walletService.payMoney(userId,1000d,userId1)).thenReturn(ewallet); 
		ewallet.setCurrentBalance(ewallet.getCurrentBalance()-1000d);
		EWallet walletRest = ewalletController.payByWallet(1000d,userId,userId1);
		
		assertNotNull(walletRest);
		
		assertEquals(ewallet.getCurrentBalance(),walletRest.getCurrentBalance());
	}
	
	
	@Test
	void payByWalletUser() throws Exception {
		
		when(walletService.payMoney(userId,1000d)).thenReturn(ewallet); 
		ewallet.setCurrentBalance(ewallet.getCurrentBalance()-1000d);
		
		EWallet walletRest = ewalletController.payByWalletUser(1000d,userId);
		
		assertNotNull(walletRest);
		
		assertEquals(ewallet.getCurrentBalance(),walletRest.getCurrentBalance());
	}
	
	
}
