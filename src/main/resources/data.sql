CREATE TABLE IF NOT EXISTS usuario (
    username VARCHAR(100) PRIMARY KEY,
    nome VARCHAR(100),
    sobrenome VARCHAR(100),
    data_nascimento DATE,
    senha VARCHAR(100) NOT NULL,
    foto_perfil VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS livro (
    isbn VARCHAR(100) PRIMARY KEY,
    rating INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS reviews (
    review_id BIGSERIAL PRIMARY KEY,
    usuario_username VARCHAR(100) REFERENCES usuario (username),
    livro_isbn VARCHAR(100) REFERENCES livro (isbn),
    data_inicio DATE,
    data_fim DATE,
    avaliacao INTEGER,
    conteudo TEXT NOT NULL
);

-- Inserção de dados na tabela 'usuario'
INSERT INTO usuario (username, nome, sobrenome, data_nascimento, senha, foto_perfil) VALUES
('johndoe', 'John', 'Doe', '1990-01-15', 'senha123', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVV1YjdvYZkK2A63pPCku8Sx6D82rVrEdMAHBxdSbU0Fhx2jzx'),
('janedoe', 'Jane', 'Doe', '1992-03-20', 'senha456', 'https://forbes.com.br/wp-content/uploads/2023/09/silvio-santos-divulgacao-sbt-1024x683.jpg'),
('mike92', 'Mike', 'Smith', '1992-07-25', 'senha789', 'https://s.ebiografia.com/img/ju/st/justin_bieber_2009.jpg?auto_optimize=low&width=655'),
('sarahjohnson', 'Sarah', 'Johnson', '1988-12-05', 'senha101', 'https://akamai.sscdn.co/uploadfile/letras/fotos/3/0/0/4/3004f53f48849a584d318296a714ed8f.jpg')
ON CONFLICT (username) DO NOTHING;

-- Inserção de dados na tabela 'livro'
INSERT INTO livro (isbn, rating) VALUES
('9788580416718', 4),
('9786555951783', 5),
('9786556402628', 3),
('9786555616415', 4)
ON CONFLICT (isbn) DO NOTHING;

-- Inserção de dados na tabela 'reviews'
INSERT INTO reviews (usuario_username, livro_isbn, data_inicio, data_fim, avaliacao, conteudo) VALUES
('johndoe', '9788580416718', '2023-01-10', '2023-01-15', 4, 'Um livro incrível que me prendeu do começo ao fim.'),
('janedoe', '9786555951783', '2023-02-20', '2023-02-28', 5, 'Uma obra-prima! Super recomendo.'),
('mike92', '9788580416718', '2023-03-15', '2023-03-20', 3, 'Bom, mas poderia ser melhor.'),
('sarahjohnson', '9786556402628', '2023-04-05', '2023-04-10', 4, 'Excelente narrativa e personagens bem construídos.')
ON CONFLICT DO NOTHING;