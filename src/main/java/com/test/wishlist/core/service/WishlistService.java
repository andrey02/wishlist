package com.test.wishlist.core.service;

import com.test.wishlist.core.dto.WishlistDTO;
import com.test.wishlist.core.entity.Wishlist;

import java.util.Optional;

public interface WishlistService {
    Wishlist addProductToWishlist(WishlistDTO wishlistDTO);
    Wishlist  removeProductFromWishlist(WishlistDTO wishlistDTO);
    Optional<Wishlist> getWishlistByClientId(String clientId);
    boolean isProductInWishlist(WishlistDTO wishlistDTO);
}
