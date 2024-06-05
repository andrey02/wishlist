package com.test.wishlist.infrastructure.repository;

import com.test.wishlist.core.entity.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WishlistRepository extends MongoRepository<Wishlist, String> {

    Optional<Wishlist> findByClientId(String clientId);
}
