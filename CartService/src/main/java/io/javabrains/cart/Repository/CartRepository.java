package io.javabrains.cart.Repository;

import io.javabrains.cart.model.Cart;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CartRepository extends MongoRepository<Cart,String> {
    List<Cart> findByCartId(String cartId);

	Cart findByUserId(String userId);

	

}
