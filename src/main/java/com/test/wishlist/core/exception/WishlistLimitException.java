package com.test.wishlist.core.exception;

public class WishlistLimitException extends RuntimeException{
    public WishlistLimitException() {
        super("O limite máximo da lista de desejos é de 20 produtos");
    }
}
