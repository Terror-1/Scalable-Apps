#!/bin/bash

# Start Cassandra
nami start cassandra
echo "Cassandra started"

# Wait for Cassandra to be ready
until cqlsh -e "DESC KEYSPACES"; do
  echo "Waiting for Cassandra to be ready..."
  sleep 5
done

echo "Running CQL script..."

# Run CQL commands
cqlsh <<EOF
CREATE KEYSPACE IF NOT EXISTS store WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : '1' };

CREATE TABLE IF NOT EXISTS store.session (
    user_id text PRIMARY KEY,
    name text
);

CREATE TABLE IF NOT EXISTS store.cart_item (
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

CREATE TABLE IF NOT EXISTS store.reviews (
    product_id TEXT,
    user_id TEXT,
    user_name TEXT,
    rating INT,
    review TEXT,
    PRIMARY KEY (product_id, user_id)
);
EOF

echo "CQL script executed"
