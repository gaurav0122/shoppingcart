package com.wallet.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wallet.api.model.EWallet;

@Repository
public interface EWalletRepository extends MongoRepository<EWallet,String> {

	EWallet findByUserId(String userId);

}
