#DB 생성
DROP DATABASE IF EXISTS text_board;
CREATE DATABASE text_board;

#DB 선택
USE text_board;

#게시물 테이블 생성
CREATE TABLE article(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL
);

#회원 테이블 생성
CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(20) NOT NULL,
    loginPw CHAR(100) NOT NULL,
    `name` CHAR(200) NOT NULL
);

/*
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = CONCAT('제목',RAND()),
`body` = CONCAT('내용',RAND());
*/

SELECT *
FROM article;

SELECT *
FROM `member`;
/*
SELECT COUNT(*) > 0
FROM `member`
WHERE loginId = 'aaa'
*/