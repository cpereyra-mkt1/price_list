
CREATE TABLE product (id INTEGER NOT NULL, sequence INTEGER, PRIMARY KEY (id));

CREATE TABLE size (id INTEGER NOT NULL, product_id INTEGER, back_soon BIT, special BIT , PRIMARY KEY (id));

CREATE TABLE stock (size_id INTEGER NOT NULL, quantity INTEGER, PRIMARY KEY (size_id) );
CREATE TABLE price (brand_id INTEGER NOT NULL, start_date DATETIME, DATETIME end_date , INTEGER PRICE_LIST, INTEGER PRODUCT_ID, INTEGER PRIORITY, DOUBLE  price, String currency);

ALTER TABLE size ADD CONSTRAINT size_product_id_fkey FOREIGN KEY (product_id) REFERENCES product (id);
ALTER TABLE stock ADD CONSTRAINT stock_size_id_fkey FOREIGN KEY (size_id) REFERENCES size (id);


