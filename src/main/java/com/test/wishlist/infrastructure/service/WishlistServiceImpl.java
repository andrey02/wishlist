package com.test.wishlist.infrastructure.service;

import com.test.wishlist.core.dto.WishlistDTO;
import com.test.wishlist.core.entity.Wishlist;
import com.test.wishlist.core.exception.WishlistLimitException;
import com.test.wishlist.core.service.WishlistService;
import com.test.wishlist.infrastructure.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Override
    public Wishlist addProductToWishlist(WishlistDTO wishlistDTO) {
        Optional<Wishlist> optionalWishlist = wishlistRepository.findByClientId(wishlistDTO.getClientId());
        Wishlist wishlist;

        if (optionalWishlist.isPresent()) {
            wishlist = optionalWishlist.get();
            if (wishlist.getProductIds().contains(wishlistDTO.getProductId())) {
                throw new IllegalArgumentException("Produto já está na Wishlist");
            }
            if (wishlist.getProductIds().size() >= 20) {
                throw new WishlistLimitException();
            }
            wishlist.getProductIds().add(wishlistDTO.getProductId());
        } else {
            wishlist = new Wishlist();
            wishlist.setClientId(wishlistDTO.getClientId());
            wishlist.setProductIds(new ArrayList<>());
            wishlist.getProductIds().add(wishlistDTO.getProductId());
        }

        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist removeProductFromWishlist(WishlistDTO wishlistDTO) {
        Optional<Wishlist> optionalWishlist = wishlistRepository.findByClientId(wishlistDTO.getClientId());

        if (optionalWishlist.isPresent()) {
            Wishlist wishlist = optionalWishlist.get();
            if (!wishlist.getProductIds().remove(wishlistDTO.getProductId())) {
                throw new IllegalArgumentException("Produto não encontrado na Wishlist");
            }
            return wishlistRepository.save(wishlist);
        } else {
            throw new IllegalArgumentException("Cliente não possui uma Wishlist");
        }
    }

    @Override
    public Optional<Wishlist> getWishlistByClientId(String clientId) {
        return wishlistRepository.findByClientId(clientId);
    }

    @Override
    public boolean isProductInWishlist(WishlistDTO wishlistDTO) {
        Optional<Wishlist> optionalWishlist = wishlistRepository.findByClientId(wishlistDTO.getClientId());
        return optionalWishlist.isPresent() && optionalWishlist.get().getProductIds().contains(wishlistDTO.getProductId());
    }
}
