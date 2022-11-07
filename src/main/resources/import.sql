insert into aluguel(id_aluguel, id_Locacao, data_Vencimento, valor_Pago, data_Pagamento, obs) values (11, 11, CURRENT_TIMESTAMP(), 1200, CURRENT_TIMESTAMP(), 'atividade04');
insert into aluguel(id_aluguel, id_Locacao, data_Vencimento, valor_Pago, data_Pagamento, obs) values (22, 11, CURRENT_TIMESTAMP(), 1100, CURRENT_TIMESTAMP(), 'atividade04');
insert into aluguel(id_aluguel, id_Locacao, data_Vencimento, valor_Pago, data_Pagamento, obs) values (33, 11, CURRENT_TIMESTAMP(), 1000, CURRENT_TIMESTAMP(), 'atividade04');

insert into cliente(id_Cliente, nome_Cliente, cpf, telefone1, telefone2, email, data_Nascimento) values (11, 'Francisco Remo', '12312312312', '12341234', '12345678', 'francisco.remo@email', CURRENT_TIMESTAMP());
insert into cliente(id_Cliente, nome_Cliente, cpf, telefone1, telefone2, email, data_Nascimento) values (22, 'João Carlos', '12345612345', '12341234', '12345678', 'joao.carlos@email', CURRENT_TIMESTAMP());
insert into cliente(id_Cliente, nome_Cliente, cpf, telefone1, telefone2, email, data_Nascimento) values (33, 'Mel Silva', '45671234122', '12341234', '12345678', 'mel.silva@email', CURRENT_TIMESTAMP());

insert into imovel(id_Imovel, tipo_Imovel, endereco, bairro, cep, metragem, dormitorios, banheiros, suites, vagas_Garagem, valor_Aluguel_Sugerido, obs) values (11, 'Apartamento', 'rua 10', 'bairro Renascença', '1000000', 1231, 2, 1, 1, 2, 1100, 'Mobiliada');
insert into imovel(id_Imovel, tipo_Imovel, endereco, bairro, cep, metragem, dormitorios, banheiros, suites, vagas_Garagem, valor_Aluguel_Sugerido, obs) values (22, 'Apartamento', 'rua 54', 'bairro Bequimão', '1000000', 1231, 3, 1, 2, 4, 2100, 'Mobiliada');
insert into imovel(id_Imovel, tipo_Imovel, endereco, bairro, cep, metragem, dormitorios, banheiros, suites, vagas_Garagem, valor_Aluguel_Sugerido, obs) values (33, 'Cobertura', 'rua 72', 'bairro Cohama', '1000000', 1231, 4, 2, 3, 10, 14100, 'Mobiliada');

insert into locacao(id_Locacao, id_Imovel, id_Cliente, valor_Aluguel, percentual_Multa, dia_vencimento, data_Inicio, data_Fim, ativo, obs) values (11, 11, 11, 1200, 0.33, 30, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()+30, 1, 'atividade04');
insert into locacao(id_Locacao, id_Imovel, id_Cliente, valor_Aluguel, percentual_Multa, dia_vencimento, data_Inicio, data_Fim, ativo, obs) values (12, 11, 11, 1300, 0.33, 10, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()+30, 1, 'atividade04');
insert into locacao(id_Locacao, id_Imovel, id_Cliente, valor_Aluguel, percentual_Multa, dia_vencimento, data_Inicio, data_Fim, ativo, obs) values (13, 11, 11, 1400, 0.33, 5, CURRENT_TIMESTAMP()-65, CURRENT_TIMESTAMP()-30, 1, 'atividade04')