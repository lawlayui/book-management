CREATE TABLE books (
    id int AUTO_INCREMENT PRIMARY KEY,
    title varchar(255) NOT NULL,
    description varchar(255),
    author varchar(255),
    price decimal NOT NULL,
    stock int,
    publication_year int,
    created_at timestamp NOT NULL,
    update_at timestamp
    created_by int,
    CONSTRAINT fk_createdBy_user
    FOREIGN KEY (created_by) REFERENCES users(id)
);

CREATE TABLE users (
    id int AUTO_INCREMENT PRIMARY KEY,
    name varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    password varchar(300) NOT NULL,
    ROLE ENUM('ROLE_ADMIN', 'ROLE_USER'),
    created_at timestamp NOT NULL,
    update_at timestamp
);

CREATE TABLE transactions (
    id int AUTO_INCREMENT PRIMARY KEY, 
    user_id int, 
    total_amount DECIMAL(15, 2), 
    status ENUM('PENDING', 'REFUNDED', 'PAID', 'CANCELLED'),
    created_at timestamp NOT NULL, 
    update_at timestamp NOT NULL
    CONSTRAINT fk_transaction_user
    FOREIGN KEY (user_id) REFERENCES users(id)
);