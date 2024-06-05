package com.test.wishlist.controller;

import com.test.wishlist.core.entity.Wishlist;
import com.test.wishlist.core.service.WishlistService;
import com.test.wishlist.core.dto.WishlistDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/add")
    public ResponseEntity<Wishlist> addProductToWishlist(@RequestBody WishlistDTO wishlistDTO) {
        try {
            Wishlist wishlist = wishlistService.addProductToWishlist(wishlistDTO);
            return ResponseEntity.ok(wishlist);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Wishlist> removeProductFromWishlist(@RequestBody WishlistDTO wishlistDTO) {
        Wishlist wishlist = wishlistService.removeProductFromWishlist(wishlistDTO);
        return wishlist != null ? ResponseEntity.ok(wishlist) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Wishlist> getWishlistByClientId(@PathVariable String clientId) {
        Optional<Wishlist> wishlist = wishlistService.getWishlistByClientId(clientId);
        return wishlist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/contains")
    public ResponseEntity<Boolean> isProductInWishlist(@RequestBody WishlistDTO wishlistDTO) {
        boolean isInWishlist = wishlistService.isProductInWishlist(wishlistDTO);
        return ResponseEntity.ok(isInWishlist);
    }

}