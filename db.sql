DROP DATABASE IF EXISTS text_board;
CREATE DATABASE text_board;
USE text_board;

CREATE TABLE article(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL
);

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = CONCAT('제목',RAND()),
`body` = CONCAT('내용',RAND());

SELECT *
FROM article;
