# language: pt
Funcionalidade: Serviço de Wishlist

  Cenário: Adicionar produto à wishlist
    Dado que o cliente com id "cliente1" existe
    Quando o cliente adicionar um produto com id "produto1" à wishlist
    Então o produto deve estar presente na wishlist

  Cenário: Remover produto da wishlist
    Dado que o cliente com id "cliente1" tem um produto "produto1" na wishlist
    Quando o cliente remover o produto com id "produto1" da wishlist
    Então o produto não deve estar presente na wishlist

  Cenário: Exceder limite da wishlist
    Dado que o cliente com id "cliente1" tem 20 produtos na wishlist
    Quando o cliente tentar adicionar um produto com id "produto21" à wishlist
    Então o sistema deve lançar uma WishlistLimitException