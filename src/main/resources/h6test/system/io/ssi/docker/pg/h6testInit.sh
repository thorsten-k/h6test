#!/bin/bash
# Automaticall generated script for SQL restore

export PGPASSWORD=docker

psql -U postgres -h localhost -c "SELECT pg_terminate_backend(pg_stat_activity.pid) FROM pg_stat_activity WHERE pg_stat_activity.datname = 'h6test' AND pid <> pg_backend_pid();"
psql -U postgres -h localhost -c "SELECT pg_terminate_backend(pg_stat_activity.pid) FROM pg_stat_activity WHERE pg_stat_activity.datname = 'template1' AND pid <> pg_backend_pid();"

psql -U postgres -h localhost -c "DROP DATABASE IF EXISTS h6test;"
psql -U postgres -h localhost -c "DROP USER IF EXISTS h6test;"

psql -U postgres -h localhost -c "CREATE USER h6test WITH PASSWORD 'tmp';"
psql -U postgres -h localhost -c "ALTER USER h6test WITH PASSWORD 'ksjNRGLOnwegbadfbre';"

psql -U postgres -h localhost -c "CREATE DATABASE h6test OWNER h6test ENCODING 'UTF8';"
psql -U postgres -h localhost -c "ALTER ROLE h6test CONNECTION LIMIT 0;"

psql -U postgres -h localhost -d h6test -c "REVOKE CREATE ON SCHEMA public FROM PUBLIC;"
psql -U postgres -h localhost -d h6test -c "GRANT CREATE ON SCHEMA public TO h6test;"
psql -U postgres -h localhost -d h6test -c "CREATE EXTENSION postgis;"
psql -U postgres -h localhost -d h6test -c "CREATE EXTENSION pg_stat_statements;"

psql -U postgres -h localhost -d h6test -c "CREATE SCHEMA IF NOT EXISTS envers AUTHORIZATION pg_database_owner;"
psql -U postgres -h localhost -d h6test -c "COMMENT ON SCHEMA envers IS 'Revision information of Hibernate Envers';"
psql -U postgres -h localhost -d h6test -c "GRANT CREATE ON SCHEMA envers TO h6test;"

psql -U postgres -h localhost -d h6test -c "GRANT pg_monitor TO h6test;"


psql -U postgres -h localhost -c "ALTER ROLE h6test CONNECTION LIMIT -1;"

unset PGPASSWORD