insert into cozinha (id, nome) values(1, 'Tailandesa');
insert into cozinha (id, nome) values(2, 'Chinesa');
insert into cozinha (id, nome) values(3, 'Brasileira');

insert into restaurante (nome, tx_frete, cozinha_id) values ('Tai Shan', 10.75, 2);
insert into restaurante (nome, tx_frete, cozinha_id) values ('Companhia do Churrasco', 7.50, 3);
  
insert into formapagamento (id, descricao) values (1, 'Cartão de crédito');
insert into formapagamento (id, descricao) values (2, 'Cartão de débito');
insert into formapagamento (id, descricao) values (3, 'Dinheiro');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');
    
insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);