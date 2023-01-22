package com.wallet.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wallet.api.model.Statement;

@Repository
public interface StatementsRepository extends MongoRepository<Statement, String> {

	List<Statement> findByEwalletId(String walletId);

}
