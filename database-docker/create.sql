-- Criação da tabela usuario
CREATE TABLE usuario (
    username VARCHAR(100) PRIMARY KEY,
    nome VARCHAR(100),
    sobrenome VARCHAR(100),
    data_nascimento DATE,
    senha VARCHAR(100) NOT NULL,
    foto_perfil VARCHAR(500)
);

-- Criação da tabela livro
CREATE TABLE livro (
    ibsn VARCHAR(100) PRIMARY KEY,
    rating INTEGER NOT NULL
);

-- Criação da tabela reviews
CREATE TABLE reviews (
    review_id BIGSERIAL PRIMARY KEY,
    usuario_username VARCHAR(100) REFERENCES usuario (username),
    livro_ibsn VARCHAR(100) REFERENCES livro (ibsn),
    data_inicio DATE,
    data_fim DATE,
    avaliacao INTEGER,
    conteudo TEXT NOT NULL
);

-- Criação da tabela livro_usuario
CREATE TABLE livro_usuario (
    livro_usuario_id BIGSERIAL PRIMARY KEY,
    usuario_username VARCHAR(100) REFERENCES usuario (username),
    livro_ibsn VARCHAR(100) REFERENCES livro (ibsn),
    review_id BIGINT REFERENCES reviews (review_id),
    status VARCHAR(30)
);
