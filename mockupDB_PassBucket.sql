CREATE DATABASE PasswordBucket;
USE PasswordBucket;


CREATE TABLE list (
id_list VARCHAR(150),
title VARCHAR(50),
PRIMARY KEY(id_list)

);

CREATE TABLE entry (
id_entry VARCHAR(150),
site VARCHAR(150),
username VARCHAR(100),
password VARCHAR(100),
id_list VARCHAR(150),
FOREIGN KEY (id_list) REFERENCES list (id_list),
PRIMARY KEY (id_entry)

);

CREATE TABLE aes (
secretkey VARCHAR(150)		
);

INSERT INTO aes (secretkey) VALUES ('U3HyrZ097bxHXPPFmPVTyQ==')