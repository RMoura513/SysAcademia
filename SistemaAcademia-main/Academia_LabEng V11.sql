CREATE DATABASE Academia
GO

USE Academia
GO

CREATE TABLE Admnistrador (
Id            INT,     
Formacao      VARCHAR(100),
TipoFormacao  VARCHAR(100),
Nome          VARCHAR(100),
DataNasc      DATE,
CEP           VARCHAR(15),
Logradouro    VARCHAR(100),
Numero        INT,
Usuario       VARCHAR(100),
Senha         VARCHAR(10)
PRIMARY KEY(Id)
)
GO

CREATE TABLE Atendente (
Id            INT IDENTITY(1000, 1),     
Nome          VARCHAR(100),
DataNasc      DATE,
CEP           VARCHAR(15),
Logradouro    VARCHAR(100),
Numero        INT,
Usuario       VARCHAR(100),
Senha         VARCHAR(10)

PRIMARY KEY(Id)
)
GO

CREATE TABLE Personal (
Id            INT IDENTITY(1000, 1),     
Formacao      VARCHAR(100),
TipoFormacao  VARCHAR(100),
Nome          VARCHAR(100),
DataNasc      DATE,
CEP           VARCHAR(15),
Logradouro    VARCHAR(100),
Numero        INT,
Usuario       VARCHAR(100),
Senha         VARCHAR(10)
PRIMARY KEY(Id)
)
GO

CREATE TABLE Aluno (
Id            INT IDENTITY(1000, 1),
Usuario       VARCHAR(100),
Senha         VARCHAR(10),
CPF           VARCHAR(15),
Nome          VARCHAR(100),
DataNasc      DATE,
CEP           VARCHAR(10),
Logradouro    VARCHAR(100),
Numero_end    INT,
PRIMARY KEY(Id)
)
GO

CREATE TABLE Mensalidade (
Id_Mensalidade INT IDENTITY(1000, 1),
Id_Aluno       INT,
Valor          DECIMAL(7,2),
DataVenc       DATE,
Descricao      VARCHAR(100)
PRIMARY KEY(Id_Mensalidade, Id_Aluno, Descricao)
FOREIGN KEY(Id_Aluno) REFERENCES Aluno(Id)
)
GO

CREATE TABLE Ficha (
Id_Ficha      INT IDENTITY(1000, 1),
Id_Personal   INT,
Id_Aluno      INT,
Descricao     VARCHAR(100)
PRIMARY KEY(Id_Ficha, Id_Personal, Id_Aluno, Descricao)
FOREIGN KEY(Id_Personal) REFERENCES Personal(Id),
FOREIGN KEY(Id_Aluno) REFERENCES Aluno(Id)
)
GO

CREATE TABLE Aula (
Id_Aula       INT IDENTITY(1000, 1),
Id_Personal   INT,
Id_Aluno      INT,
Nome          VARCHAR(100),
Duracao       TIME
PRIMARY KEY(Id_Aula, Id_Personal, Id_Aluno, Nome)
FOREIGN KEY(Id_Personal) REFERENCES Personal(Id),
FOREIGN KEY(Id_Aluno) REFERENCES Aluno(Id)
)
GO




INSERT INTO Admnistrador (id, Formacao, TipoFormacao, Nome, DataNasc, CEP, Logradouro, Numero, Usuario, Senha)
VALUES                   (1111, 'Administrador', 'Superior', 'Djonga', '11/09/1990', '08330401', 'Avenida Aguia de Haia', 120, 'ADM', 'admin')

INSERT INTO Atendente(Nome, DataNasc, CEP, Logradouro, Numero, Usuario, Senha)
VALUES               ('Anderson Moura', '20/08/1999', 22222222, 'eueueueueuueueueu', '0022222', 'AnderMoura', 'and1234')

INSERT INTO Personal(Formacao, TipoFormacao, Nome, DataNasc, CEP, Logradouro, Numero, Usuario, Senha)
VALUES              ('Educação Fisica', 'Mestrado', 'Gabriel Elias da Silva', '05/06/2002', '0300033', 'ererewrerewrerewsaa', '0322222', 'GabrielElias' ,'biel1234')

INSERT INTO Aluno (Usuario, Senha, CPF, Nome, DataNasc, CEP, Logradouro, Numero_end)
VALUES            ('LucasBCoelho', '123456', '11111111', 'Lucas Bezerra Coelho', '29/08/2001', '0111111', 'Vila Silvia Rua Belem Santos', 250)

INSERT INTO Aluno (Usuario, Senha, CPF, Nome, DataNasc, CEP, Logradouro, Numero_end)
VALUES            ('K', '654321', '11111111', 'Knull', '29/08/2021', '0111111', 'Vila Silvia Rua Belem Santos', 250)


DROP PROCEDURE sp_realizar_login

-- Procedure que ajuda a realizar o login especifico

CREATE PROCEDURE sp_realizar_login (@usuario VARCHAR(30),
                                    @senha VARCHAR(10),
									@acesso VARCHAR(10) OUTPUT)
AS
BEGIN

		IF(@usuario IS NULL AND @senha IS NULL)
		BEGIN
			 RAISERROR('Usuario/Senha invalidos', 16, 1)
		END

		ELSE IF((SELECT Usuario FROM Admnistrador WHERE Usuario = @usuario) = @usuario AND (SELECT Senha FROM Admnistrador WHERE Senha = @senha) = @senha)
		BEGIN
			 SET @acesso = 'ADM'
		END

		ELSE IF((SELECT Usuario FROM Aluno WHERE Usuario = @usuario) = @usuario AND (SELECT Senha FROM Aluno WHERE Senha = @senha) = @senha)
		BEGIN
			 SET @acesso = 'ALU'
		END

		ELSE IF((SELECT Usuario FROM Atendente WHERE Usuario = @usuario) = @usuario AND (SELECT Senha FROM Atendente WHERE Senha = @senha) = @senha)
		BEGIN
			 SET @acesso = 'ATEN'
		END

		ELSE IF((SELECT Usuario FROM Personal WHERE Usuario = @usuario) = @usuario AND (SELECT Senha FROM Personal WHERE Senha = @senha) = @senha)
		BEGIN
			 SET @acesso = 'PERSO'
		END
		ELSE
		BEGIN
			 RAISERROR('Usuario/senha invalidos', 16, 1)
		END

END

DECLARE @saida VARCHAR(10)
EXEC sp_realizar_login 'AnderMoura', 'and1234', @saida OUT

PRINT(@saida)

-- Manter Ficha
-- Procedure que Cria a ficha de treino
DROP PROCEDURE sp_criar_ficha

CREATE PROCEDURE sp_criar_ficha (@nome_personal VARCHAR(30),
                                 @nome_aluno VARCHAR(30),
								 @descricao VARCHAR(30),
								 @saida VARCHAR(30) OUTPUT)
AS
BEGIN
     DECLARE @id_personal INT,
	         @id_aluno INT

	     SET @id_personal = (SELECT Id FROM Personal WHERE Nome = @nome_personal)
		 SET @id_aluno = (SELECT Id FROM Aluno WHERE Nome = @nome_aluno)

		 IF((@Id_personal IS NULL OR @Id_aluno IS NULL OR @Descricao IS NULL) OR (@Id_personal = 0 AND @Id_aluno = 0 AND @Descricao = ''))
		 BEGIN
		      RAISERROR('Informações de cadastro Invalidas', 16, 1)
		 END

		 ELSE IF EXISTS ((SELECT Id_Personal, Id_Aluno, Descricao FROM Ficha WHERE Id_Personal = @id_personal AND Id_Aluno = @id_aluno AND @descricao = Descricao))
		 BEGIN
			  RAISERROR('Ficha de treino ja existente', 16, 1)
		 END

		 ELSE IF((@Id_personal IS NOT NULL AND @Id_aluno IS NOT NULL AND @Descricao IS NOT NULL) OR (@Id_personal != 0 AND @Id_aluno != 0 AND @Descricao != ''))
		 BEGIN
		      INSERT INTO Ficha (Id_Personal, Id_Aluno, Descricao)
			  VALUES            (@id_personal, @id_aluno, @descricao)

			  SET @saida = 'Ficha cadastrada com sucesso'
		 END

		 ELSE
		 BEGIN
		      RAISERROR('Informações fornecidas invalidas', 16, 1)
		 END

END 

DECLARE @saida VARCHAR(30)
EXEC sp_criar_ficha 'Gabriel Elias da Silva', 'Lucas Bezerra Coelho', 'TESTE1...', @saida OUT

PRINT(@saida)
GO

DECLARE @saida VARCHAR(30)
EXEC sp_criar_ficha 'Gabriel Elias da Silva', 'Knull', 'TESTE1...', @saida OUT

PRINT(@saida)

SELECT * FROM Ficha

DELETE Ficha

-- Select para Listar todas as fichas daquele personal

SELECT f.Id_Ficha,
       p.Nome AS nome_personal,
       a.Nome AS nome_aluno,
	   f.Descricao 
FROM Ficha f, Personal p, Aluno a
WHERE f.id_Aluno = a.Id
  AND f.Id_Personal = p.Id


-- Select para Buscar a ficha especifica daquele personal 

SELECT f.Id_Ficha,
       p.Nome AS nome_personal,
       a.Nome AS nome_aluno,
	   f.Descricao 
FROM Ficha f, Personal p, Aluno a
WHERE f.id_Aluno = a.Id
  AND f.Id_Personal = p.Id
  AND f.Id_Ficha = 1000 


-- Procedure que Exclui a ficha
DROP PROCEDURE sp_excluir_ficha

CREATE PROCEDURE sp_excluir_ficha (@id_ficha INT,
								   @saida VARCHAR(30) OUTPUT)
AS
BEGIN


	IF((@id_ficha IS NULL) OR (@id_ficha = 0))
	BEGIN
	     RAISERROR('Codigo deve ser preenchido para exclusão', 16, 1)
	END

	ELSE IF NOT EXISTS (SELECT Id_Ficha FROM Ficha WHERE Id_Ficha = @id_ficha)
	     BEGIN
	          RAISERROR('Ficha não existente para exclusão', 16, 1)
         END

		 ELSE
		 BEGIN
		      DELETE Ficha
			  WHERE Id_Ficha = @id_ficha

			  SET @saida = 'Ficha excluida com sucesso'
		 END
END

DECLARE @saida VARCHAR(30) 
EXEC sp_excluir_ficha 1003, @saida OUT

PRINT(@saida)


-- Procedure que Atualiza a Ficha
DROP PROCEDURE sp_atualizar_ficha

CREATE PROCEDURE sp_atualizar_ficha (@id_ficha INT,
									 @descricao VARCHAR(200),
									 @saida VARCHAR(50) OUTPUT)
AS
BEGIN
			PRINT(@id_ficha) 

		 IF((@id_ficha IS NULL OR @Descricao IS NULL) OR (@id_ficha = 0 OR @Descricao = ''))
		 BEGIN
		      RAISERROR('Informações para atualizaçao precisam ser preenchidas', 16, 1)
		 END

		 ELSE IF NOT EXISTS ((SELECT Id_Ficha FROM Ficha WHERE Id_Ficha = @id_ficha))
		 BEGIN
              RAISERROR('Ficha nao encotrada para atualização', 16, 1)
		 END

		 ELSE IF EXISTS ((SELECT Id_Ficha, Descricao FROM Ficha WHERE Id_Ficha = @id_ficha))
		 BEGIN
			  UPDATE Ficha
			  SET Descricao = @descricao
			  WHERE Id_Ficha = @id_ficha

			  SET @saida = 'Ficha atualizada com sucesso'
		 END

		 ELSE
		 BEGIN
		      RAISERROR('Informações para atualização invalidas', 16, 1)
		 END
END

DECLARE @saida VARCHAR(100)
EXEC sp_atualizar_ficha 1000, 'correr', @saida OUT

PRINT(@saida)
GO
DECLARE @saida VARCHAR(100)
EXEC sp_atualizar_ficha 100, 'pular', @saida OUT

PRINT(@saida)
GO
DECLARE @saida VARCHAR(100)
EXEC sp_atualizar_ficha 1000, 'flexao', @saida OUT

PRINT(@saida)

SELECT * FROM Ficha

-- Manter Aula
-- Procedure que Cria a aula
DROP PROCEDURE sp_criar_aula 

CREATE PROCEDURE sp_criar_aula (@nome_personal VARCHAR(30),
                                @nome_aluno VARCHAR(30),
								@nome VARCHAR(30),
								@duracao TIME,
								@saida VARCHAR(50) OUTPUT)
AS
BEGIN
     DECLARE @id_personal INT,
	         @id_aluno INT

	     SET @id_personal = (SELECT Id FROM Personal WHERE Nome = @nome_personal)
		 SET @id_aluno = (SELECT Id FROM Aluno WHERE Nome = @nome_aluno)

		 PRINT(@Id_personal)
		 PRINT(@Id_aluno)
		 PRINT(@nome)
		 PRINT(@duracao)

		 IF((@Id_personal IS NULL OR @Id_aluno IS NULL OR @nome_personal IS NULL OR @nome_aluno IS NULL OR @nome IS NULL OR @duracao IS NULL) OR (@Id_personal = 0 AND @Id_aluno = 0 OR @nome_personal = '' OR @nome_aluno = '' OR @nome = '' OR @duracao = '')) 
		 BEGIN
		      RAISERROR('Campos devem ser preenchidos para o cadastro', 16, 1)
		 END

		 ELSE IF EXISTS ((SELECT Id_Personal, Id_Aluno, Nome FROM Aula WHERE Id_Personal = @id_personal AND Id_Aluno = @id_aluno AND Nome = @nome))
		 BEGIN
			  RAISERROR('Aula ja existente', 16, 1)
		 END

		 ELSE IF((@Id_personal IS NOT NULL AND @Id_aluno IS NOT NULL) OR (@Id_personal != 0 AND @Id_aluno != 0))
		 BEGIN
		      INSERT INTO Aula (Id_Personal, Id_Aluno, Nome, Duracao)
			  VALUES            (@id_personal, @id_aluno, @nome, @duracao)

			  SET @saida = 'Aula cadastrada com sucesso'
		 END

		 ELSE
		 BEGIN
		      RAISERROR('Informações fornecidas invalidas', 16, 1)
		 END

END

DECLARE @saida VARCHAR(30)
EXEC sp_criar_aula 'Gabriel Elias da Silva', 'Lucas Bezerra Coelho', 'Natação', '4:00', @saida OUT

PRINT(@saida)
GO
DECLARE @saida VARCHAR(30)
EXEC sp_criar_aula 'Gabriel Elias da Silva', 'Lucas Bezerra Coelho', 'mai tai', '2:00', @saida OUT

PRINT(@saida)
GO
DECLARE @saida VARCHAR(30)
EXEC sp_criar_aula 'Gabriel Elias da Silva', 'Lucas Bezerra Coelho', 'yoga', '2:00', @saida OUT

PRINT(@saida)

SELECT * FROM Aula

-- Select para Listar aulas do personal

SELECT au.Id_Aula,
       al.Nome AS nome_aluno,
	   p.Nome AS nome_personal,
       au.Nome AS nome_aula,
       au.Duracao
FROM Aula au, aluno al, Personal p
WHERE au.Id_Aluno = al.Id
  AND au.Id_Personal = p.Id

-- Select para Buscar a aula especifica do personal

SELECT au.Id_Aula,
       al.Nome AS nome_aluno,
	   p.Nome AS nome_personal,
       au.Nome AS nome_aula,
       au.Duracao
FROM Aula au, aluno al, Personal p
WHERE au.Id_Aluno = al.Id
  AND au.Id_Personal = p.Id
  AND au.Id_Aula = 1000


-- Procedure para Excluir aulas
DROP PROCEDURE sp_excluir_aula

CREATE PROCEDURE sp_excluir_aula (@id_aula INT,
                                  @saida VARCHAR(100) OUTPUT)
AS
BEGIN
     IF(@id_aula IS NULL OR @id_aula = 0 OR @id_aula = 0)
	 BEGIN
	      RAISERROR('Codigo necessario para exclusão', 16, 1)
	 END

	 ELSE IF NOT EXISTS (SELECT Id_Aula FROM Aula WHERE Id_Aula = @id_aula)
	 BEGIN
	      RAISERROR('Aula não existente', 16, 1) 
	 END

	 ELSE IF EXISTS (SELECT Id_Aula FROM Aula WHERE Id_Aula = @id_aula)
	 BEGIN
	      DELETE Aula
		  WHERE Id_Aula = @id_aula 

		  SET @saida = 'Aula excluida com sucesso'
	 END
END

DECLARE @saida VARCHAR(100)
EXEC sp_excluir_aula 1002, @saida OUT

PRINT(@saida)

SELECT * FROM Aula

-- Procedure para Atualizar aula
DROP PROCEDURE sp_atualizar_aula 

CREATE PROCEDURE sp_atualizar_aula (@id_aula INT,
									@nome_aula VARCHAR(100),
									@duracao TIME,
									@saida VARCHAR(100) OUTPUT)
AS
BEGIN
		 PRINT(@id_aula)
		 PRINT(@nome_aula)
		 PRINT(@duracao) 
		 
		 IF((@id_aula IS NULL OR @nome_aula IS NULL OR @duracao IS NULL) OR (@id_aula = 0 OR @nome_aula = '' OR @duracao = ''))
		 BEGIN
		      RAISERROR('Campos nao podem ficar em branco para a atualização', 16, 1)
		 END    

		 ELSE IF NOT EXISTS (SELECT Id_Aula FROM Aula WHERE Id_Aula = @id_aula)
		 BEGIN
		      RAISERROR('Aula não existente', 16, 1)
		 END

		 ELSE IF EXISTS (SELECT Id_Aula FROM Aula WHERE Id_Aula = @id_aula)
		 BEGIN
		      UPDATE Aula
			  SET Nome = @nome_aula,
			      Duracao = @duracao
			  WHERE Id_Aula = @id_aula


			  SET @saida = 'Aula atualizada com sucesso'
		 END
END 

DECLARE @saida VARCHAR(100)
EXEC sp_atualizar_aula 1000, 'Natação 2', '2:00', @saida OUT

PRINT(@saida)


DECLARE @saida VARCHAR(100)
EXEC sp_atualizar_aula 1001, 'Mai Tai', '1:00', @saida OUT

PRINT(@saida)


DECLARE @saida VARCHAR(100)
EXEC sp_atualizar_aula 1002, 'Yoga', '5:00', @saida OUT

PRINT(@saida)


SELECT * FROM Aula


-- Manter Mensalidade
-- Procedure que Cria as mensalidades
DROP PROCEDURE sp_cria_mensalidade

CREATE PROCEDURE sp_cria_mensalidade (@nome_aluno VARCHAR(30),
								      @valor DECIMAL(7, 2),
								      @dataVenc DATE,
									  @descricao VARCHAR(30),
								      @saida VARCHAR(50) OUTPUT)
AS
BEGIN
     DECLARE @id_aluno INT

	     
		 SET @id_aluno = (SELECT Id FROM Aluno WHERE Nome = @nome_aluno)

		
		 PRINT(@Id_aluno)
		 PRINT(@valor)
		 PRINT(@dataVenc)
		 PRINT(@descricao)

		 IF((@Id_aluno IS NULL OR @valor IS NULL OR @dataVenc IS NULL OR @descricao IS NULL) OR (@Id_aluno = 0 OR @valor = 0 OR @dataVenc = '' OR @descricao = ''))
		 BEGIN
		      RAISERROR('Informações de cadastro não podem estar em branco', 16, 1)
		 END

		 ELSE IF EXISTS ((SELECT Id_Aluno, Valor, DataVenc, Descricao FROM Mensalidade WHERE Id_Aluno = @id_aluno AND Valor = @valor AND DataVenc = @dataVenc AND Descricao = @descricao))
		 BEGIN

			  RAISERROR('Mensalidade ja existente', 16, 1)

		 END

		 ELSE IF((@Id_aluno IS NOT NULL AND @valor IS NOT NULL AND @dataVenc IS NOT NULL AND @descricao IS NOT NULL) OR (@Id_aluno != 0 AND @valor != 0.0 AND @dataVenc != '' AND @descricao != ''))
		 BEGIN
		      INSERT INTO Mensalidade (Id_Aluno, Valor, DataVenc, Descricao)
			  VALUES                  (@id_aluno, @valor, @dataVenc, @descricao)

			  SET @saida = 'Mensalidade cadastrada com sucesso'
		 END

		 ELSE
		 BEGIN
		      RAISERROR('Informações fornecidas invalidas', 16, 1)
		 END
END

DECLARE @saida VARCHAR(100)
EXEC sp_cria_mensalidade 'Lucas Bezerra Coelho', 400.50, '20/03/2023', 'Natação', @saida OUT

PRINT(@saida)
GO
DECLARE @saida VARCHAR(100)
EXEC sp_cria_mensalidade 'Lucas Bezerra Coelho', 35.50, '20/03/2020', 'Yoga', @saida OUT

PRINT(@saida)

SELECT * FROM Mensalidade




-- Select que Lista as Mensalidades para o atendente

SELECT m.Id_Mensalidade,
       a.Nome AS nome_aluno,
       m.Descricao,
	   m.Valor,
	   m.DataVenc
FROM Mensalidade m, Aluno a, Atendente at
WHERE m.Id_Aluno = a.Id
  AND m.Id_Atendente = at.Id

-- Select que Buscar uma Mensalidades para o atendente

SELECT m.Id_Mensalidade,
       a.Nome AS nome_aluno,
       m.Descricao,
	   m.Valor,
	   m.DataVenc
FROM Mensalidade m, Aluno a, Atendente at
WHERE m.Id_Aluno = a.Id
  AND m.Id_Atendente = at.Id
  AND m.Id_Mensalidade = 1006

-- Procedure para Excluir Mensalidade

DROP PROCEDURE sp_excluir_mensalidade

CREATE PROCEDURE sp_excluir_mensalidade (@id_mensalidade INT,
                                         @saida VARCHAR(100) OUTPUT)
AS
BEGIN
     IF(@id_mensalidade IS NULL OR @id_mensalidade = 0)
	 BEGIN
	      RAISERROR('Codigo necessario para exclusão', 16, 1)
	 END

	 ELSE IF NOT EXISTS (SELECT Id_Mensalidade FROM Mensalidade WHERE Id_Mensalidade = @id_mensalidade)
	 BEGIN
	      RAISERROR('Mensalidade não encontrada', 16, 1)
	 END

	 ELSE IF EXISTS (SELECT Id_Mensalidade FROM Mensalidade WHERE Id_Mensalidade = @id_mensalidade)
	 BEGIN
	      DELETE Mensalidade
		  WHERE Id_Mensalidade = @id_mensalidade

		  SET @saida = 'Mensalidade excluida com sucesso'
	 END

	 ELSE
	 BEGIN
	      RAISERROR('Erro ao tentar excluir', 16, 1)
	 END
END

DECLARE @saida VARCHAR(100)
EXEC sp_excluir_mensalidade 1001, @saida OUT

PRINT(@saida)

SELECT * FROM Mensalidade

-- Procedure para Atualizar a mensalidade
DROP PROCEDURE sp_atualizar_mensalidade

CREATE PROCEDURE sp_atualizar_mensalidade (@id_mensalidade INT,
                                           @valor DECIMAL(7, 2),
                                           @dataVenc DATE,
										   @descricao VARCHAR(100),
										   @saida VARCHAR(100) OUTPUT)
AS
BEGIN
     IF((@id_mensalidade IS NULL OR @valor IS NULL OR @dataVenc IS NULL OR @descricao IS NULL) OR (@id_mensalidade = 0 OR @valor = 0.0 OR @dataVenc = '' OR @descricao = ''))
	 BEGIN
	      RAISERROR('Campos devem ser preenchidos para a atualização', 16, 1)
	 END

	 ELSE IF NOT EXISTS (SELECT Id_Mensalidade FROM Mensalidade WHERE Id_Mensalidade = @id_mensalidade)
	 BEGIN
	      RAISERROR('Mensalidade não encontrada', 16, 1)
	 END

	 ELSE IF EXISTS (SELECT Id_Mensalidade FROM Mensalidade WHERE Id_Mensalidade = @id_mensalidade)
	 BEGIN
	      UPDATE Mensalidade
		  SET Valor = @valor,
		      DataVenc = @datavenc,
			  Descricao = @descricao 
		  WHERE Id_Mensalidade = @id_mensalidade

		  SET @saida = 'Mensalidade atualizada com sucesso'
	 END

	 ELSE
	 BEGIN
	      RAISERROR('Erro ao tentar atualizar', 16, 1)
	 END
END

DECLARE @saida VARCHAR(100)
EXEC sp_atualizar_mensalidade null, 35.50, '20/08/2000', 'Spine',  @saida OUT

PRINT(@saida)

SELECT * FROM Mensalidade

DELETE Mensalidade

SELECT * FROM Aula
SELECT * FROM Ficha
SELECT * FROM Admnistrador
SELECT * FROM Personal
SELECT * FROM Aluno
SELECT * FROM Atendente
SELECT * FROM Mensalidade

SELECT f.Id_Ficha,
       p.Nome AS nome_personal,
       a.Nome AS nome_aluno,
	   f.Descricao
FROM Ficha f, Aluno a, Personal p
WHERE f.Id_Aluno = a.Id
  AND f.Id_Personal = p.Id
  AND a.Id = 1000

SELECT m.Valor,
       m.Descricao,
	   m.DataVenc
FROM Mensalidade m, Aluno a
WHERE m.Id_Aluno = a.Id
  AND a.Id = 1000


-- Procedure para cadastrar o atendente
DECLARE @saida VARCHAR(100)
EXEC sp_cadastrar_atendente 'pato', '20/08/2009', '343433', 'eeeeeeeee', 123, 'Pato', '12345', @saida OUT
PRINT(@saida)

DROP PROCEDURE sp_cadastrar_atendente

CREATE PROCEDURE sp_cadastrar_atendente (@nome VARCHAR(30),
                                         @dataNasc DATE,
										 @cep VARCHAR(10),
										 @logradouro VARCHAR(100),
										 @numero INT,
										 @usuario VARCHAR(15),
										 @senha VARCHAR(10),
										 @saida VARCHAR(100) OUTPUT)
AS
BEGIN
     IF (@nome IS NULL OR @dataNasc IS NULL OR @cep IS NULL OR @logradouro IS NULL OR @numero IS NULL OR @usuario IS NULL OR @senha IS NULL) OR (@nome = '' OR @dataNasc = '' OR @cep = '' OR @logradouro = '' OR @numero = 0 OR @usuario = ''  OR @senha = '' )
	 BEGIN
	      RAISERROR('Todos os Campos Obrigatorios para Cadastro', 16, 1)
	 END

	 ELSE IF EXISTS (SELECT Nome, DataNasc, CEP, Logradouro, Numero, Usuario, Senha FROM Atendente WHERE Nome = @nome AND DataNasc = @dataNasc AND CEP = @cep AND Logradouro = @logradouro AND Numero = @numero AND Usuario = @usuario AND Senha = @senha)
	 BEGIN
	      RAISERROR('Este Atendente já está Cadastrado', 16, 1)
	 END

     ELSE IF NOT EXISTS (SELECT Nome, DataNasc, CEP, Logradouro, Numero, Usuario, Senha FROM Atendente WHERE Nome = @nome AND DataNasc = @dataNasc AND CEP = @cep AND Logradouro = @logradouro AND Numero = @numero AND Usuario = @usuario AND Senha = @senha)
	 BEGIN
	      INSERT INTO Atendente (Nome, DataNasc, CEP, Logradouro, Numero, Usuario, Senha)
		  VALUES                (@nome, @dataNasc, @cep, @logradouro, @numero, @usuario, @senha)

		  SET @saida = 'Atendente Cadastrado com Sucesso'
	 END

	 ELSE 
	 BEGIN
	      RAISERROR('Erro Interno', 16, 1)
	 END
END




SELECT * FROM Atendente


--Procedure que atualiza o atendente
DROP PROCEDURE sp_atualiza_atendente

CREATE PROCEDURE sp_atualiza_atendente (@id INT,
                                        @nome_atendente VARCHAR(100),
										@dataNasc DATE,
										@cep VARCHAR(15),
										@logradouro VARCHAR(50),
										@numero INT,
										@usuario VARCHAR(10),
										@senha VARCHAR(10),
										@saida VARCHAR(100) OUTPUT)
AS
BEGIN
     IF ((@id IS NULL OR @nome_atendente IS NULL OR @cep IS NULL OR @logradouro IS NULL OR @numero IS NULL OR @usuario IS NULL OR @senha IS NULL) OR (@id = 0 OR @nome_atendente = '' OR @cep = '' OR @logradouro = '' OR @numero = 0 OR @usuario = '' OR @senha = '' ))
	 BEGIN
	      RAISERROR('Espaços em branco necessarios para atualização', 16, 1)
	 END

     ELSE IF NOT EXISTS (SELECT Id FROM Atendente WHERE Id = @id)
	 BEGIN
	      RAISERROR('Atendente não existente para atualização', 16, 1)
	 END

     ELSE IF EXISTS (SELECT Id FROM Atendente WHERE Id = @id)
	 BEGIN
	      UPDATE Atendente
		  SET Nome = @nome_atendente,
		      DataNasc = @dataNasc,
			  CEP = @cep,
			  Logradouro = @logradouro,
			  Numero = @numero,
			  Usuario = @usuario,
			  Senha = @senha
		  WHERE Id = @id

		  SET @saida = 'Atendente atualizado com sucesso'    
	 END

	 ELSE
	 BEGIN
	      RAISERROR('Erro Interno', 16, 1)
	 END
END

DECLARE @saida VARCHAR(100)
EXEC sp_atualiza_atendente 1001, '', '20/01/2000', '111111', 'rtrtrtrtrtr', 250, 'Patao', 'pato1', @saida OUT
PRINT(@saida)

SELECT * FROM Atendente

-- Procedure que excluir atendentes 
DROP PROCEDURE sp_excluir_atendente

CREATE PROCEDURE sp_excluir_atendente (@id INT,
                                       @saida VARCHAR(100) OUTPUT)
AS
BEGIN
     IF (@id IS NULL OR @id = 0)
	 BEGIN
	      RAISERROR('Codigo em branco necessario para exclusão', 16, 1)
	 END

     ELSE IF NOT EXISTS (SELECT Id FROM Atendente WHERE Id = @id)
	 BEGIN
	      RAISERROR('Atendente não encontrado para exclusão', 16, 1)
	 END

     ELSE IF EXISTS (SELECT Id FROM Atendente WHERE Id = @id)
	 BEGIN
	      DELETE Atendente
		  WHERE Id = @id

		  SET @saida = 'Atendente excluido com sucesso'
	 END

	 ELSE
	 BEGIN
	      RAISERROR('Erro Interno', 16, 1)
	 END
END



SELECT * FROM Atendente
-- Procedure para cadastrar o personal

DECLARE @saida VARCHAR(100)
EXEC sp_cadastrar_personal 'Pablo', 'mestre', 'Mestrado', '20/08/1998', '434326', 'gfgfgfgdgfdgfd', 33343, 'p', 'pablo1234', @saida OUT
PRINT(@saida)

DROP PROCEDURE sp_cadastrar_personal

CREATE PROCEDURE sp_cadastrar_personal (@nome VARCHAR(30),
                                        @formacao VARCHAR(50),
										@tipoFormacao VARCHAR(50),
                                        @dataNasc DATE,
									    @cep VARCHAR(10),
									    @logradouro VARCHAR(100),
									    @numero INT,
									    @usuario VARCHAR(15),
									    @senha VARCHAR(10),
									    @saida VARCHAR(100) OUTPUT)
AS
BEGIN
     IF (@formacao IS NULL OR @tipoFormacao IS NULL OR @nome IS NULL OR @dataNasc IS NULL OR @cep IS NULL OR @logradouro IS NULL OR @numero IS NULL OR @usuario IS NULL OR @senha IS NULL) OR (@formacao = '' OR @tipoFormacao = '' OR @nome = '' OR @dataNasc = '' OR @cep = '' OR @logradouro = '' OR @numero = 0 OR @usuario = ''  OR @senha = '' )
	 BEGIN
	      RAISERROR('Todos os Campos Obrigatorios para Cadastro', 16, 1)
	 END

	 ELSE IF EXISTS (SELECT Formacao, TipoFormacao, Nome, DataNasc, CEP, Logradouro, Numero, Usuario, Senha FROM Personal WHERE Formacao = @formacao AND TipoFormacao = @tipoFormacao AND Nome = @nome AND DataNasc = @dataNasc AND CEP = @cep AND Logradouro = @logradouro AND Numero = @numero AND Usuario = @usuario AND Senha = @senha)
	 BEGIN
	      RAISERROR('Este Personal já está Cadastrado', 16, 1)
	 END

     ELSE IF NOT EXISTS (SELECT Formacao, TipoFormacao, Nome, DataNasc, CEP, Logradouro, Numero, Usuario, Senha FROM Personal WHERE Formacao = @formacao AND TipoFormacao = @tipoFormacao AND Nome = @nome AND DataNasc = @dataNasc AND CEP = @cep AND Logradouro = @logradouro AND Numero = @numero AND Usuario = @usuario AND Senha = @senha)
	 BEGIN
	      INSERT INTO Personal (Formacao, TipoFormacao, Nome, DataNasc, CEP, Logradouro, Numero, Usuario, Senha)
		  VALUES                (@formacao, @tipoFormacao, @nome, @dataNasc, @cep, @logradouro, @numero, @usuario, @senha)

		  SET @saida = 'Personal Cadastrado com Sucesso'
	 END

	 ELSE 
	 BEGIN
	      RAISERROR('Erro Interno', 16, 1)
	 END
END

SELECT * FROM Personal

--Procedure que atualiza o personal
DROP PROCEDURE sp_atualiza_personal

CREATE PROCEDURE sp_atualiza_personal  (@id INT,
                                        @formacao VARCHAR(100),
										@tipoFormacao VARCHAR(100),
                                        @nome_personal VARCHAR(100),
										@dataNasc DATE,
										@cep VARCHAR(15),
										@logradouro VARCHAR(50),
										@numero INT,
										@usuario VARCHAR(10),
										@senha VARCHAR(10),
										@saida VARCHAR(100) OUTPUT)
AS
BEGIN
     IF ((@id IS NULL OR @formacao IS NULL OR @tipoFormacao IS NULL OR @nome_personal IS NULL OR @cep IS NULL OR @logradouro IS NULL OR @numero IS NULL OR @usuario IS NULL OR @senha IS NULL) OR (@id = 0 OR @formacao = '' OR @tipoFormacao = '' OR @nome_personal = '' OR @cep = '' OR @logradouro = '' OR @numero = 0 OR @usuario = '' OR @senha = '' ))
	 BEGIN
	      RAISERROR('Espaços em branco necessarios para atualização', 16, 1)
	 END

     ELSE IF NOT EXISTS (SELECT Id FROM Personal WHERE Id = @id)
	 BEGIN
	      RAISERROR('Personal não existente para atualização', 16, 1)
	 END

     ELSE IF EXISTS (SELECT Id FROM Personal WHERE Id = @id)
	 BEGIN
	      UPDATE Personal
		  SET Formacao = @formacao,
		      TipoFormacao = @tipoFormacao,
		      Nome = @nome_personal,
		      DataNasc = @dataNasc,
			  CEP = @cep,
			  Logradouro = @logradouro,
			  Numero = @numero,
			  Usuario = @usuario,
			  Senha = @senha
		  WHERE Id = @id

		  SET @saida = 'Personal atualizado com sucesso'    
	 END

	 ELSE
	 BEGIN
	      RAISERROR('Erro Interno', 16, 1)
	 END
END

DECLARE @saida VARCHAR(100)
EXEC sp_atualiza_personal 1000, 'Educação Fisica', 'Mestrado Superior', 'Gabriel Elias da Silva', '2002-06-05' , '3333333', 'RUA', 350, 'GabrielElias', 'biel1234', @saida OUT

PRINT(@saida)

SELECT * FROM Personal
-- Procedure que excluir atendentes 
DROP PROCEDURE sp_excluir_personal

CREATE PROCEDURE sp_excluir_personal (@id INT,
                                      @saida VARCHAR(100) OUTPUT)
AS
BEGIN
     IF (@id IS NULL OR @id = 0)
	 BEGIN
	      RAISERROR('Codigo em branco necessario para exclusão', 16, 1)
	 END

     ELSE IF NOT EXISTS (SELECT Id FROM Personal WHERE Id = @id)
	 BEGIN
	      RAISERROR('Personal não encontrado para exclusão', 16, 1)
	 END

     ELSE IF EXISTS (SELECT Id FROM Personal WHERE Id = @id)
	 BEGIN
	      DELETE Personal
		  WHERE Id = @id

		  SET @saida = 'Personal excluido com sucesso'
	 END

	 ELSE
	 BEGIN
	      RAISERROR('Erro Interno', 16, 1)
	 END
END

SELECT * FROM Personal




SELECT * FROM Aula
SELECT * FROM Ficha
SELECT * FROM Admnistrador
SELECT * FROM Personal
SELECT * FROM Aluno
SELECT * FROM Atendente
SELECT * FROM Mensalidade