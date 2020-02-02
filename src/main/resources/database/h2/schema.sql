DROP ALL OBJECTS;

/*-----------------------------------------------*/
DROP TABLE IF EXISTS `USER`;

CREATE TABLE USER
(
	id INT(11) NOT NULL AUTO_INCREMENT COMMENT 'user_id',
	username VARCHAR(64) DEFAULT NULL UNIQUE COMMENT 'name',
	password VARCHAR(64) DEFAULT NULL COMMENT 'password',
	email VARCHAR(256) NOT NULL COMMENT 'email',
	status CHAR NOT NULL COMMENT 'user state',
	role VARCHAR(64) DEFAULT NULL COMMENT 'role',
	created_at DATETIME NOT NULL COMMENT 'created time',
	updated_at DATETIME COMMENT 'update time',
	created_by VARCHAR(64) NOT NULL COMMENT 'created by user name',
	updated_by VARCHAR(64) COMMENT 'update by user name',
	last_login_time DATETIME COMMENT 'the time when the user login',
	PRIMARY KEY (id)
);

DROP TABLE IF EXISTS `LOGS`;

CREATE TABLE LOGS
(
	id INT(11) NOT NULL AUTO_INCREMENT COMMENT 'log_id',
	operation_name VARCHAR(1024) COMMENT 'operationName',
	request VARCHAR(1024) COMMENT 'request',
	response VARCHAR(1024) COMMENT 'response',
	error VARCHAR(1024) COMMENT 'error',
	take_time BIGINT DEFAULT NULL COMMENT 'takeTime',
	create_time DATETIME DEFAULT NULL COMMENT 'createTime',
	stack VARCHAR(1024) COMMENT 'stack',
	PRIMARY KEY (id)
);
