--
-- PostgreSQL database dump
--

-- Dumped from database version 9.1.19
-- Dumped by pg_dump version 9.1.19
-- Started on 2016-03-14 15:15:41

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;


--
-- TOC entry 1910 (class 0 OID 60111)
-- Dependencies: 167 1917
-- Data for Name: author; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO author VALUES (1, 'Jack London');


--
-- TOC entry 1925 (class 0 OID 0)
-- Dependencies: 161
-- Name: author_seq_id; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('author_seq_id', 1, true);


--
-- TOC entry 1912 (class 0 OID 60121)
-- Dependencies: 169 1917
-- Data for Name: genre; Type: TABLE DATA; Schema: public; Owner: postgres
--


INSERT INTO genre VALUES (1, 'Drama');
INSERT INTO genre VALUES (2, 'Graphic novel');
INSERT INTO genre VALUES (3, 'Poem');
INSERT INTO genre VALUES (4, 'Myth');
INSERT INTO genre VALUES (5, 'Novel');
INSERT INTO genre VALUES (6, 'Novella');
INSERT INTO genre VALUES (7, 'Short story');


--
-- TOC entry 1927 (class 0 OID 0)
-- Dependencies: 163
-- Name: genre_seq_id; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('genre_seq_id', 7, true);


--
-- TOC entry 1911 (class 0 OID 60116)
-- Dependencies: 168 1917
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO book VALUES (1, 'Good book about motivation man!', 'Time does not wait!', 1, 5);


--
-- TOC entry 1926 (class 0 OID 0)
-- Dependencies: 162
-- Name: book_seq_id; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('book_seq_id', 1, false);




--
-- TOC entry 1913 (class 0 OID 60126)
-- Dependencies: 170 1917
-- Data for Name: permission; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO permission VALUES (1, true, true, true);
INSERT INTO permission VALUES (2, false, false, true);

--
-- TOC entry 1928 (class 0 OID 0)
-- Dependencies: 164
-- Name: permission_seq_id; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('permission_seq_id', 2, true);


--
-- TOC entry 1914 (class 0 OID 60131)
-- Dependencies: 171 1917
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO roles VALUES (1, 'ROLE_ADMIN', 1);
INSERT INTO roles VALUES (2, 'ROLE_USER', 2);

--
-- TOC entry 1929 (class 0 OID 0)
-- Dependencies: 165
-- Name: roles_seq_id; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('roles_seq_id', 2, true);


--
-- TOC entry 1915 (class 0 OID 60136)
-- Dependencies: 172 1917
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO users VALUES (1, 'Alex', 1, 'reserved', 1);


--
-- TOC entry 1916 (class 0 OID 60141)
-- Dependencies: 173 1917
-- Data for Name: users_book; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO users_book VALUES (1, 1);


--
-- TOC entry 1930 (class 0 OID 0)
-- Dependencies: 166
-- Name: users_seq_id; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('users_seq_id', 1, true);