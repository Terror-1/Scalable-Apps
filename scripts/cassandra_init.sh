#!/bin/bash
nami start cassandra
echo "script stuff here to run after cassandra starts"
CREATE KEYSPACE IF NOT EXISTS store WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : '1' };

CREATE TABLE IF NOT EXISTS session (
    user_id text PRIMARY KEY,
    name text
);

CREATE TABLE IF NOT EXISTS cart_item (
  user_id text,
  item_id text,
  sku text,
  small_mid_large_one_size text,
  color text,
  name text,
  size_number int,
  item_price double,
  quantity int,
  PRIMARY KEY (user_id, item_id)
);

CREATE TABLE IF NOT EXISTS reviews (
    product_id TEXT,
    user_id TEXT,
    user_name TEXT,
    rating INT,
    review TEXT,
    PRIMARY KEY (product_id, user_id)
);

