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

  Cenário: Verificar se o produto está na wishlist
    Dado que o cliente com id "cliente1" tem um produto "produto1" na wishlist
    Quando eu verificar se o produto com id "produto1" está na wishlist
    Então o resultado deve ser verdadeiro

  Cenário: Obter wishlist por id do cliente
    Dado que o cliente com id "cliente1" tem uma wishlist
    Quando eu buscar a wishlist pelo id do cliente "cliente1"
    Então a wishlist retornada deve pertencer ao cliente com id "cliente1"