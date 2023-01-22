package com.wallet.api.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wallet.api.model.EWallet;
import com.wallet.api.model.Statement;
import com.wallet.api.model.UserProfile;
import com.wallet.api.repository.EWalletRepository;
import com.wallet.api.repository.StatementsRepository;


@Service
public class WalletServiceImpl implements EWalletService{
	
	@Autowired
	private EWalletRepository walletRepo;
	
	@Autowired
	private StatementsRepository statementRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	// add new Wallet
	public EWallet addWallet(EWallet ewallet,String userId) {
		
		try {
			UserProfile profile = restTemplate.getForObject("http://profile-service/user/user1/" + userId,
					UserProfile.class);

				if(getWalletByUserId(userId) == null ) {
					ewallet.setUserId(userId);
					ewallet.setUserName(profile.getFullName());
					ewallet.setCurrentBalance(0.0);
					return walletRepo.save(ewallet);
				}
		} 
		catch (Exception e) {
			
		}
		
		return null;
		
	}
	
	//get list of all wallets
	public List<EWallet> getWallets(){
	
		return walletRepo.findAll();
	}
	
	
	//get wallet by id
	public EWallet getById(String walletId) {
		Optional<EWallet> ewallets=walletRepo.findById(walletId);
		return ewallets.get();
	}
	
	//add money
	@Override
	public EWallet addMoney(String walletId,Double amount) {
	     EWallet wallet=walletRepo.findById(walletId).get();
	     Double Amountadded=wallet.getCurrentBalance()+amount;
	     wallet.setCurrentBalance(Amountadded);
	      
	      
	     Statement statement=new Statement();
	     statement.setAmount(amount);
	     statement.setDate(LocalDate.now());
	     statement.setOrderId(0);
	     statement.setTransactionType("DEPOSIT");
	     statement.setEwalletId(walletId);
	     statement.setTransactionRemarks(walletId+" added "+amount+" at date and time  "+statement.getDate()+" By transaction type"
	     		+ " "+statement.getTransactionType()+"  Now, Current balance in wallet is "+wallet.getCurrentBalance());
	     
	     statementRepo.save(statement);
	     
	     walletRepo.save(wallet);
	     return wallet;
	     
	}
	
	//pay money
	public EWallet payMoney(String walletIdSender,Double amount,String walletIdRecipent) throws Exception {
		
		EWallet walletSender =walletRepo.findById(walletIdSender).get();
		
		if(amount > walletSender.getCurrentBalance()) {
			throw new Exception();
		}
		Double amountDebited=walletSender.getCurrentBalance()-amount;
		walletSender.setCurrentBalance(amountDebited);
		
		walletRepo.save(walletSender);
		
		EWallet walletReciever=walletRepo.findById(walletIdRecipent).get();
		Double amountCredited=walletReciever.getCurrentBalance()+amount;
		walletReciever.setCurrentBalance(amountCredited);
		
		walletRepo.save(walletReciever);
		
		Statement statementS=new Statement();
	     statementS.setAmount(amount);
	     statementS.setDate(LocalDate.now());
	    
	     statementS.setOrderId(0);
	     statementS.setTransactionType("WITHDRAW");
	     statementS.setTransactionRemarks(walletIdSender+" payed "+amount+" at "+statementS.getDate()+
	    		 					" Now, Current balance in wallet is "+walletSender.getCurrentBalance());
	     statementS.setEwalletId(walletIdSender);
	     statementRepo.save(statementS);
	     
	  
	     Statement statementR=new Statement();
	     statementR.setAmount(amount);
	     statementR.setDate(LocalDate.now());
	    
	     statementR.setOrderId(0);
	     statementR.setTransactionType("DEPOSIT");
	     statementR.setTransactionRemarks(walletIdRecipent+" received "+amount+" at "+statementR.getDate()+
	    		 				" Now, Current balance in wallet is "+walletReciever.getCurrentBalance());
	     statementR.setEwalletId(walletIdRecipent);
	     statementRepo.save(statementR);

		return walletSender;
	}
	
	
	//get all statements
	public List<Statement> getStatements(){
		return statementRepo.findAll();
	}
	
	//delete wallet by id
	public String deleteById(String walletId) {
		 walletRepo.deleteById(walletId);
		return "deleted successfully"; 
			
	}

	
	//get statements by wallet Id
	@Override
	public List<Statement> getStatementByWalletId(String walletId) {
		 List<Statement> list = statementRepo.findByEwalletId(walletId);
		 Collections.reverse(list);
		return list;
	}

	// get wallet By user Id
	@Override
	public EWallet getWalletByUserId(String userId) {
		return walletRepo.findByUserId(userId);
	}

	//pay The money
	@Override
	public EWallet payMoney(String senderId, double amount) throws Exception {
		EWallet walletSender =walletRepo.findById(senderId).get();
		
		if(amount > walletSender.getCurrentBalance()) {
			throw new Exception("wallet amount is less");
		}
		
		Double amountDebited=walletSender.getCurrentBalance()-amount;
		walletSender.setCurrentBalance(amountDebited);
		
		walletRepo.save(walletSender);
		
		
		
		Statement statementS=new Statement();
	     statementS.setAmount(amount);
	     statementS.setDate(LocalDate.now());
	    
	     statementS.setOrderId(0);
	     statementS.setTransactionType("WITHDRAW");
	     statementS.setTransactionRemarks(senderId+" payed "+amount+" at "+statementS.getDate()+
	    		 					" Now, Current balance in wallet is "+walletSender.getCurrentBalance());
	     statementS.setEwalletId(senderId);
	     statementRepo.save(statementS);

		return walletSender;
	}

	
}
