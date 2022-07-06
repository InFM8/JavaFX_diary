Guidelines for localhost (XAMPP)

1. Go to localhost/phpmyadmin - > SQL
2. CREATE DATABASE diary;
3. Select db and create tables as follows

 CREATE TABLE users(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(63) NOT NULL,
    password VARCHAR(255) NOT NULL
);
CREATE TABLE diary(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(127) NOT NULL,
    text TEXT,
    user_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

4. Test app.
