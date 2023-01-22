package com.wallet.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wallet.api.model.EWallet;
import com.wallet.api.model.Statement;

@Service
public interface EWalletService {
	
	public List<EWallet> getWallets();
	
	public EWallet addWallet(EWallet ewallet, String userId);
	
	public EWallet addMoney(String walletId,Double amount);
	
	public EWallet getById(String walletId);
	
	public List<Statement> getStatements();
	
	public List<Statement> getStatementByWalletId(String walletId);
	
	public String deleteById(String walletId);
	
	public EWallet getWalletByUserId(String userId);
	
	public EWallet payMoney(String walletIdSender,Double amount,String walletIdRecipent) throws Exception;

	public EWallet payMoney(String senderId, double amount) throws Exception;

}
