--
-- PostgreSQL database dump
--

-- Dumped from database version 10.1
-- Dumped by pg_dump version 10.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = ON;
SET check_function_bodies = FALSE;
SET client_min_messages = WARNING;
SET row_security = OFF;

SET search_path = orlando;

SET default_tablespace = '';

SET default_with_oids = FALSE;

CREATE TABLE rsvp (
  id          INTEGER               NOT NULL,
  name        CHARACTER VARYING(50) NOT NULL,
  come        INTEGER,
  people      INTEGER,
  message     CHARACTER VARYING(300),
  created_at  BIGINT,
  modified_at BIGINT
);

CREATE SEQUENCE rsvp_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  NO MAXVALUE
  CACHE 1;

ALTER SEQUENCE rsvp_id_seq
OWNED BY rsvp.id;

ALTER TABLE ONLY rsvp
  ADD CONSTRAINT rsvp_pkey PRIMARY KEY (id);
