
DROP TABLE IF EXISTS user_role;

CREATE TABLE user_role(
	role_id INTEGER PRIMARY KEY,	
	role_title VARCHAR(10) NOT NULL UNIQUE 
);

DROP TABLE IF EXISTS account_type;

CREATE TABLE account_type(
	type_id INTEGER PRIMARY KEY, --make serial?
	accnt_type VARCHAR(10) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS account_status;

CREATE TABLE account_status(
	status_id INTEGER PRIMARY KEY,	--make serial?
	accnt_status VARCHAR(10) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS user_info;

CREATE TABLE user_info(
	user_id SERIAL PRIMARY KEY,
	username VARCHAR(30) NOT NULL UNIQUE,
	pass_word VARCHAR(30) NOT NULL,
	first_name VARCHAR (30) NOT NULL,
	last_name VARCHAR(30) NOT NULL,
	email VARCHAR(30) NOT NULL,
	user_role INTEGER REFERENCES user_role(role_id)						--FK
);

DROP TABLE IF EXISTS account;

CREATE TABLE account(
	account_id SERIAL PRIMARY KEY,
	account_balance DOUBLE PRECISION NOT NULL,
	account_status INTEGER REFERENCES account_status(status_id),		--FK
	account_type INTEGER REFERENCES account_type(type_id),				--FK
	account_user INTEGER REFERENCES user_info(user_id) 					--FK
);

INSERT INTO user_role(role_id, role_title)
	VALUES(1, 'Admin'), (2, 'Employee'), (3, 'Standard');

INSERT INTO user_info(username, pass_word, first_name, last_name, email, user_role)
	VALUES ('lguzik', 'hello', 'Lindsay', 'Guzik', 'lindsayguzik@gmail.com', 1),
			('painter1', 'password', 'Pablo', 'Picasso', 'picasso@gmail.com', 2);

INSERT INTO account_status(status_id, accnt_status)
	VALUES(1, 'Open'), (2, 'Pending'), (3, 'Closed'), (4, 'Denied');

INSERT INTO account_type(type_id, accnt_type)
	VALUES(1, 'Checking'), (2, 'Savings');

SELECT * FROM user_info;

INSERT INTO account(account_balance, account_status, account_type, account_user)
	VALUES(1600.76, 1, 1, 1);
INSERT INTO account(account_balance, account_status, account_type, account_user)
	VALUES(500.98, 1, 2, 4);
	
SELECT * FROM user_info;

SELECT * FROM account;

INSERT INTO user_info(username, pass_word, first_name, last_name, email, user_role)
	VALUES ('frida90', 'password2', 'Frida', 'Kahlo', 'kahlo@gmail.com', 3),
			('sal12', 'password3', 'Salvador', 'Dali', 'dali@gmail.com', 3);

		
INSERT INTO account(account_balance, account_status, account_type, account_user)
	VALUES(37.49, 1, 2, 1);
INSERT INTO account(account_balance, account_status, account_type, account_user)
	VALUES(893.42, 1, 2, 2);

			