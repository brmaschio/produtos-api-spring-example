CREATE TABLE MERCADO (
  ID BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  CNPJ VARCHAR(20) NOT NULL,
  NOME VARCHAR(100) NOT NULL,
  NOMEFANTASIA VARCHAR(100),
  STATUS VARCHAR(15) NOT NULL,
  IMAGEM VARCHAR(100),
  ENDERECO VARCHAR(100),
  NUMERO VARCHAR(10),
  COMPLEMENTO VARCHAR(10),
  BAIRRO VARCHAR(50),
  CEP VARCHAR(15),
  CIDADE VARCHAR(30),
  ESTADO VARCHAR(3),
  TELEFONES VARCHAR(254)
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE CATEGORIA (
  ID BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  NOME VARCHAR(45) NOT NULL,
  IMAGEM VARCHAR(100)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE PRODUTO (
  ID BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  NOME VARCHAR(200) NOT NULL,	
  IMAGEM VARCHAR(100),
  STATUS VARCHAR(15) NOT NULL,
  CODIGOBARRAS VARCHAR(200) NOT NULL,
  CATEGORIA BIGINT(20) NOT NULL,
  FOREIGN KEY (CATEGORIA) REFERENCES CATEGORIA(ID)
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE MERCADORIA (
  ID BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  CODIGOMERCADO BIGINT(20) NOT NULL,
  CODIGOPRODUTO BIGINT(20) NOT NULL,
  VALOR DECIMAL(10,2) NOT NULL,
  PROMOCAO CHAR NULL,
  HABILITADO TINYINT(1) NOT NULL,
  DATAATT datetime,
  DATAATTCOMP DATE NOT NULL,
  FOREIGN KEY (CODIGOMERCADO) REFERENCES MERCADO(ID),
  FOREIGN KEY (CODIGOPRODUTO) REFERENCES PRODUTO(ID)
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE HISTORICOMERCADORIA (
  HISTORICO BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  CODIGOMERCADORIA BIGINT(20) NOT NULL,
  VALOR DECIMAL(10,2) NOT NULL,
  DATAIMPORTADO DATE NOT NULL,
  FOREIGN KEY (CODIGOMERCADORIA) REFERENCES MERCADORIA(ID)
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




