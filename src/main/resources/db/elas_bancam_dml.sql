/* insert contas CONTACORRENTEPJ, CONTACORRENTEPF e CONTAPOUPANCA */
INSERT INTO `elas_bancam`.`conta`
(
`id`,
`agencia`,
`numero_conta`,
`operacao`,
`saldo`,
`status`)
VALUES
(
"875e4a2e-c44c-420c-8368-99d21842706a",
123,
25040,
"CONTACORRENTEPJ",
10999,
1);

INSERT INTO `elas_bancam`.`conta`
(
`id`,
`agencia`,
`numero_conta`,
`operacao`,
`saldo`,
`status`)
VALUES
(
"f0c4999f-a2d4-48c0-a78f-efb3a1ded4a5",
123,
25041,
"CONTACORRENTEPF",
5000,
1);
INSERT INTO `elas_bancam`.`conta`
(
`id`,
`agencia`,
`numero_conta`,
`operacao`,
`saldo`,
`status`)
VALUES
(
"d4130b7b-42a1-4608-83fe-d80a9846f86f",
123,
25043,
"CONTAPOUPANCA",
2000,
1);
/* insert endereços */
INSERT INTO `elas_bancam`.`endereco`
(`id`,
`bairro`,
`cep`,
`cidade`,
`complemento`,
`numero`,
`regiao`,
`rua`,
`uf`)
VALUES
(99,
"Cidade Satélite",
"12941490",
"Atibaia",
"lj01",
239,
"SE",
"Rua Itaú",
"SP");

INSERT INTO `elas_bancam`.`endereco`
(`id`,
`bairro`,
`cep`,
`cidade`,
`complemento`,
`numero`,
`regiao`,
`rua`,
`uf`)
VALUES
(100,
"Iririu",
"89224150",
"Joinville",
"apt303",
323,
"S",
"Rua Cegonhas",
"SC");

INSERT INTO `elas_bancam`.`endereco`
(`id`,
`bairro`,
`cep`,
`cidade`,
`complemento`,
`numero`,
`regiao`,
`rua`,
`uf`)
VALUES
(101,
"Jardim das Orquídeas",
"13453732",
"Santa Bárbara D'Oeste",
"",
549,
"SE",
"Rua Cesarino Scagnolato",
"SP");


INSERT INTO `elas_bancam`.`pessoa_juridica`
(
`id`,
`alterado_em`,
`celular`,
`criado_em`,
`email`,
`nome`,
`status`,
`telefone`,
`cnpj`,
`inscricao_estadual`,
`nome_contato`,
`nome_fantasia`,
`conta_id`,
`endereco_id`)
VALUES
(
99,
now(),
"6525283206",
now(),
"almoxarifado@nelsoncontabilltda.com.br",
"Nelson e Cláudio Contábil Ltda",
1,
"1126675385",
"34213050000179",
"708272103721",
"Claudio",
"Nelson e Cláudio Contábil Ltda",
"875e4a2e-c44c-420c-8368-99d21842706a",
99
);

INSERT INTO `elas_bancam`.`pessoa_fisica`
(`id`,
`alterado_em`,
`celular`,
`criado_em`,
`email`,
`nome`,
`status`,
`telefone`,
`cpf`,
`dt_nascimento`,
`genero`,
`nome_mae`,
`rg`,
`conta_id`,
`endereco_id`)
VALUES
(100,
now(),
"87985390961",
now(),
"tatiane-caldeira88@mourafiorito.com",
"Tatiane Sandra Agatha Caldeira",
1,
"8737827184",
"87985390961",
"1981-09-15",
"FEMININO",
"Alana Rita",
"56320060",
"f0c4999f-a2d4-48c0-a78f-efb3a1ded4a5",
100);


INSERT INTO `elas_bancam`.`pessoa_fisica`
(`id`,
`alterado_em`,
`celular`,
`criado_em`,
`email`,
`nome`,
`status`,
`telefone`,
`cpf`,
`dt_nascimento`,
`genero`,
`nome_mae`,
`rg`,
`conta_id`,
`endereco_id`)
VALUES
(101,
now(),
"63989426684",
now(),
"bryan_almeida@hellokitty.com",
"Bryan Arthur Almeida",
1,
"6326574436",
"24176339225",
"1980-10-15",
"MASCULINO",
"Allana Simone",
"482212329",
"d4130b7b-42a1-4608-83fe-d80a9846f86f",
101);

INSERT INTO `elas_bancam`.`transacao`
(`id`,
`data`,
`descricao`,
`tipo_transacao`,
`valor`,
`conta_destino_id`,
`conta_origem_id`)
VALUES
("ff137a77-e363-471d-a03a-225e1f9252e7",
now(),
"pagamento de compra",
"PIX",
59.50,
"d4130b7b-42a1-4608-83fe-d80a9846f86f",
"f0c4999f-a2d4-48c0-a78f-efb3a1ded4a5");

INSERT INTO `elas_bancam`.`transacao`
(`id`,
`data`,
`descricao`,
`tipo_transacao`,
`valor`,
`conta_destino_id`,
`conta_origem_id`)
VALUES
("cc17c138-33f3-481a-adb6-22436dbb2473",
now(),
"pagamento de pedido",
"PIX",
1999.50,
"f0c4999f-a2d4-48c0-a78f-efb3a1ded4a5",
"875e4a2e-c44c-420c-8368-99d21842706a");




