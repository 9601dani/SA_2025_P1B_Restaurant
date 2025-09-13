-- Tabla Category
CREATE TABLE category (
    name VARCHAR(255) NOT NULL,
    created_at DATETIME,
    PRIMARY KEY (name)
);

-- Tabla Dish
CREATE TABLE dish (
    id CHAR(36) NOT NULL,
    name VARCHAR(255),
    description TEXT,
    price DECIMAL(19,2),
    category VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME,
    image_url VARCHAR(500),
    dish_cost DECIMAL(19,2),
    PRIMARY KEY (id)
);

-- Tabla Orders
CREATE TABLE orders (
    id CHAR(36) NOT NULL,
    description TEXT,
    location_id CHAR(36),
    id_client VARCHAR(255),
    status VARCHAR(50),
    sub_total DECIMAL(19,2),
    discount_amount DECIMAL(19,2) NOT NULL DEFAULT 0,
    discount_code VARCHAR(255),
    tax DECIMAL(19,2),
    total DECIMAL(19,2),
    created_at DATETIME,
    updated_at DATETIME,
    user_employee_id CHAR(36),
    PRIMARY KEY (id)
);

-- Tabla Item
CREATE TABLE item (
    id CHAR(36) NOT NULL,
    dish_id CHAR(36),
    dish_name VARCHAR(255),
    quantity INT,
    unit_price DECIMAL(19,2),
    line_total DECIMAL(19,2),
    order_id CHAR(36) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_item_order FOREIGN KEY (order_id) REFERENCES orders(id),
    CONSTRAINT fk_item_dish FOREIGN KEY (dish_id) REFERENCES dish(id)
);

