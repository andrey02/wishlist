package com.test.wishlist;

import com.test.wishlist.core.dto.WishlistDTO;
import com.test.wishlist.core.entity.Wishlist;
import com.test.wishlist.core.exception.WishlistLimitException;
import com.test.wishlist.core.service.WishlistService;
import com.test.wishlist.infrastructure.repository.WishlistRepository;
import com.test.wishlist.infrastructure.service.WishlistServiceImpl;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import io.cucumber.spring.CucumberContextConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@CucumberContextConfiguration
@SpringBootTest
public class WishlistServiceSteps {

    @Mock
    private WishlistRepository wishlistRepository;

    @InjectMocks
    private WishlistService wishlistService = new WishlistServiceImpl();

    private Wishlist wishlist;
    private WishlistDTO wishlistDTO;
    private Exception exception;

    public WishlistServiceSteps() {
        openMocks(this); // Inicializa mocks do Mockito
    }

    @Dado("que o cliente com id {string} existe")
    public void cliente_existe(String clientId) {
        wishlist = new Wishlist();
        wishlist.setClientId(clientId);
        wishlist.setProductIds(new ArrayList<>());
        when(wishlistRepository.findByClientId(clientId)).thenReturn(Optional.of(wishlist));
    }

    @Quando("o cliente adicionar um produto com id {string} à wishlist")
    public void adicionar_produto_a_wishlist(String productId) {
        try {
            wishlistDTO = new WishlistDTO();
            wishlistDTO.setProductId(productId);
            wishlistDTO.setClientId(wishlist.getClientId());
            wishlistService.addProductToWishlist(wishlistDTO);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Entao("o produto deve estar presente na wishlist")
    public void produto_presente_na_wishlist() {
        assertTrue(wishlist.getProductIds().contains(wishlistDTO.getProductId()));
    }

    @Dado("que o cliente com id {string} tem um produto {string} na wishlist")
    public void cliente_com_produto(String clientId, String productId) {
        wishlistDTO = new WishlistDTO();
        wishlistDTO.setClientId(clientId);
        wishlistDTO.setProductId(productId);

        wishlist = new Wishlist();
        wishlist.setClientId(clientId);
        wishlist.setProductIds(new ArrayList<>());
        wishlist.getProductIds().add(productId);
        when(wishlistRepository.findByClientId(clientId)).thenReturn(Optional.of(wishlist));
    }

    @Quando("o cliente remover o produto com id {string} da wishlist")
    public void remover_produto_da_wishlist(String productId) {
        wishlistDTO.setProductId(productId);
        wishlistService.removeProductFromWishlist(wishlistDTO);
    }

    @Entao("o produto não deve estar presente na wishlist")
    public void produto_nao_presente_na_wishlist() {
        assertFalse(wishlist.getProductIds().contains(wishlistDTO.getProductId()));
    }

    @Dado("que o cliente com id {string} tem 20 produtos na wishlist")
    public void cliente_com_20_produtos_na_wishlist(String clientId) {
        wishlist = new Wishlist();
        wishlist.setClientId(clientId);
        wishlist.setProductIds(new ArrayList<>());

        for (int i = 1; i <= 20; i++) {
            wishlist.getProductIds().add("produto" + i);
        }

        when(wishlistRepository.findByClientId(clientId)).thenReturn(Optional.of(wishlist));
        wishlistDTO = new WishlistDTO();
        wishlistDTO.setClientId(clientId);
    }

    @Quando("o cliente tentar adicionar um produto com id {string} à wishlist")
    public void tentar_adicionar_produto_a_wishlist(String productId) {
        try {
            wishlistDTO.setProductId(productId);
            wishlistService.addProductToWishlist(wishlistDTO);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Entao("o sistema deve lançar uma WishlistLimitException")
    public void sistema_lancar_wishlist_limit_exception() {
        assertNotNull(exception);
        assertTrue(exception instanceof WishlistLimitException);
    }
}