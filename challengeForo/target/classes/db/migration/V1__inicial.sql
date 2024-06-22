

CREATE TABLE usuario (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL,
                         contrasenia VARCHAR(255) NOT NULL
);

CREATE TABLE  topico (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      titulo VARCHAR(255) NOT NULL,
                                      mensaje VARCHAR(255) NOT NULL,
                                      fecha_creacion datetime(6),
                                      autor_id BIGINT,
                                      CONSTRAINT fk_autor FOREIGN KEY (autor_id) REFERENCES usuario(id)
);

