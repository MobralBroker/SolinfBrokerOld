-- Parâmetros:
-- p_cpf: Parâmetro opcional que permite filtrar os resultados por CPF da pessoa física associada ao cliente.
-- p_cnpj: Parâmetro opcional que permite filtrar os resultados por CNPJ da pessoa jurídica associada ao cliente.
-- p_id_cliente: Parâmetro opcional que permite filtrar os resultados pelo ID único do cliente.

-- Estrutura dos Resultados:
-- id_cliente: Identificador único do cliente.
-- tipo: Tipo de cliente, sendo 'PF' para pessoa física e 'PJ' para pessoa jurídica.
-- usuario: Nome de usuário do cliente.
-- senha: Senha do cliente.
-- email: Endereço de e-mail do cliente.
-- saldo: Saldo associado ao cliente.
-- cpf: CPF do cliente (apenas para tipo 'PF').
-- nome_pf: Nome da pessoa física (apenas para tipo 'PF').
-- nascimento: Data de nascimento da pessoa física (apenas para tipo 'PF').
-- cnpj: CNPJ do cliente (apenas para tipo 'PJ').
-- razao_social: Razão social da pessoa jurídica (apenas para tipo 'PJ').
-- nome_fantasia: Nome fantasia da pessoa jurídica (apenas para tipo 'PJ').

-- Lógica da Procedure:
-- Utiliza duas consultas com LEFT JOIN nas tabelas pessoafisica e pessoajuridica para obter informações tanto de pessoas físicas quanto de pessoas jurídicas.
-- Aplica os filtros com base nos parâmetros fornecidos, permitindo a filtragem por CPF, CNPJ e ID do cliente.
-- Retorna os resultados consolidados de acordo com os filtros aplicados.
CREATE OR REPLACE PROCEDURE sp_cliente_completo(
    IN p_cpf VARCHAR(11) DEFAULT NULL,
    IN p_cnpj VARCHAR(14) DEFAULT NULL,
    IN p_id_cliente INT DEFAULT NULL
)
BEGIN
		SELECT
			c.id_cliente,
			c.tipo,
			c.usuario,
			c.senha,
			c.email,
			c.saldo,
			pf.cpf,
			pf.nome AS nome_pf,
			pf.nascimento,
			NULL AS cnpj,
			NULL AS razao_social,
			NULL AS nome_fantasia
		FROM
			cliente c
		LEFT JOIN
			pessoafisica pf ON c.id_cliente = pf.id_cliente
		WHERE
			(p_cpf IS NULL OR pf.cpf = p_cpf) AND
			(p_cnpj IS NULL) AND
			(p_id_cliente IS NULL OR c.id_cliente = p_id_cliente)

    UNION

		SELECT
			c.id_cliente,
			c.tipo,
			c.usuario,
			c.senha,
			c.email,
			c.saldo,
			NULL AS cpf,
			NULL AS nome_pf,
			NULL AS nascimento,
			pj.cnpj,
			pj.razao_social,
			pj.nome_fantasia
		FROM
			cliente c
		LEFT JOIN
			pessoajuridica pj ON c.id_cliente = pj.id_cliente
		WHERE
			(p_cnpj IS NULL OR pj.cnpj = p_cnpj) AND
			(p_cpf IS NULL) AND
			(p_id_cliente IS NULL OR c.id_cliente = p_id_cliente);
END;


-- Utilização:
-- A procedure sp_cliente_completo pode ser chamada com diferentes combinações de parâmetros para obter informações específicas do cliente.
-- Facilita a obtenção de dados consolidados, permitindo a flexibilidade na seleção de critérios de pesquisa.
CALL sp_cliente_completo('12345678909', NULL, NULL);