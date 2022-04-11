# e-commerce
Projeto de estudo de sistemas, E-commerce

### Projeto do modulo: 
#### Construir um e-commerce onde seja possível gerenciar usuários e produtos, e criar um carrinho de compras.  

- Produtos: Cadastrar, alterar, listar, consultar e remover produtos.
- Usuários: Criar, alterar, listar, consultar e remover usuários. Autenticar usuário.
- Carrinho de compras: criar um carrinho de compras (adicionar e remover produtos de um carrinho).

#### Requisitos:
- Testes;
- Conectar em uma base de dados postgres (docker ou cloud);
- A aplicação deve salvar os dados na base de dados, podendo ser consultados após um restart da aplicacao.
- Construção do projeto, estruturação.

#### Campos minimos:
(os demais seguem como em projeto de aula, ex produto)

- Usuario:
id,
nome,
data nascimento,
data criacao,
data alteracao

- Carrinho:
id carrinho,
data criacao,
data alteracao,
Itens
