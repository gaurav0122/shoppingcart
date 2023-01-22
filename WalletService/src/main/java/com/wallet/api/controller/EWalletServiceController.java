package com.wallet.api.controller;

import java.util.List;

import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.api.model.EWallet;
import com.wallet.api.model.Statement;
import com.wallet.api.service.EWalletService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
//@CrossOrigin("http://localhost:4200/")
public class EWalletServiceController  {
	
	
	Logger logger = LoggerFactory.getLogger(EWalletServiceController.class);
	
	@Autowired
	private EWalletService walletService;
	

	@PostMapping("/wallet/{userId}")
	@ApiOperation("This method used for create wallet")
	public EWallet addWallet(@RequestBody EWallet ewallet, @PathVariable("userId") String userId) {
		
		logger.trace("add wallet method accessed");
		
		return walletService.addWallet(ewallet,userId);
	}

	@GetMapping("/wallets")
	@ApiOperation("This method is used for get all methods")
	public List<EWallet> getWallets() {
		logger.trace("get wallets method accessed");
		return walletService.getWallets();
	}
	
	
	@GetMapping("/wallets/{walletId}")
	@ApiOperation("Get wallet by wallet ID")
	public EWallet getById(@PathVariable("walletId") String walletId) {
		
		logger.trace("get wallet by walletId method accessed");
		return walletService.getById(walletId);
	}



	@GetMapping("/wallet/statements")
	@ApiOperation("get all statements")
	public List<Statement> getStatements() {
		
		logger.trace("get statements method accessed");
		return walletService.getStatements();
	}

	
	@DeleteMapping("/wallet/{walletId}")
	@ApiOperation("Delete wallet by wallet Id")
	public String deleteById( @PathVariable("walletId") String walletId) {
		logger.trace("delete wallet by id method accessed");
		 return walletService.deleteById(walletId);
		
	}

	
	
	@PostMapping("/wallet/{walletId}/{amount}")
	@ApiOperation("Add money in the wallet")
	public EWallet addMoney(@PathVariable("walletId") String walletId,
								@PathVariable("amount") Double amount) {
		
		logger.trace("add money to wallet method accessed");
		 return walletService.addMoney(walletId, amount);
	}

	
	@GetMapping("/wallet/statements/{walletId}")
	@ApiOperation("Get Statements by wallet Id")
	public List<Statement> getStatementsByWalletId(@PathVariable("walletId") String walletId){
		return walletService.getStatementByWalletId(walletId);
	}
	
	@ApiOperation("Get wallet by user ID")
	@GetMapping("/wallets/byuser/{userId}")
	public EWallet getWalletByUserId(@PathVariable("userId") String userId) {
		
		logger.trace("get wallet by walletId method accessed");
		return walletService.getWalletByUserId(userId);
	}
	
	
	@ApiOperation("pay money to another account")
	@PostMapping("/wallet/pay/{amount}/{senderId}/{reciverId}")
	public EWallet payByWallet(@PathVariable("amount") double amount,
								@PathVariable("senderId") String senderId,
								@PathVariable("reciverId") String reciverId) throws Exception {
		return walletService.payMoney(senderId, amount, reciverId);
	}
	
	@ApiOperation("pay money By User")
	@PostMapping("/wallet/pay/{amount}/{senderId}")
	public EWallet payByWalletUser(@PathVariable("amount") double amount,
								@PathVariable("senderId") String senderId) throws Exception {
		return walletService.payMoney(senderId, amount);
	}
	
}
