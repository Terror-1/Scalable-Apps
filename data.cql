CREATE KEYSPACE IF NOT EXISTS store WITH REPLICATION = {
   'class' : 'SimpleStrategy',
   'replication_factor' : 1
};

USE store;

CREATE TYPE IF NOT EXISTS SIZE_TYPE (
   size TEXT,
   count INT
);

CREATE TYPE IF NOT EXISTS REVIEW_TYPE (
   stars INT,
   customer_id TEXT,
   rev_description TEXT
);

CREATE TABLE IF NOT EXISTS PRODUCT (
   product_id TEXT PRIMARY KEY,
   name TEXT,
   description TEXT,
   male BOOLEAN,
   category TEXT,
   size FROZEN<SIZE_TYPE>,
   reviews LIST<FROZEN<REVIEW_TYPE>>
);

CREATE TABLE IF NOT EXISTS session (
   session_id TEXT PRIMARY KEY,
   session_token TEXT,
   customer_id TEXT,
   credit_score INT,
   total_price DECIMAL
);

