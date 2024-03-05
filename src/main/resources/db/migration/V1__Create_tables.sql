
CREATE TABLE price (id INTEGER NOT NULL AUTO_INCREMENT ,brand_id INTEGER NOT NULL, start_date DATETIME, end_date DATETIME(6),
price_list INTEGER, product_id INTEGER, priority INTEGER, price DOUBLE, currency VARCHAR, PRIMARY KEY (id));



