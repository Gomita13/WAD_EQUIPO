--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2
-- Dumped by pg_dump version 14.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: colaborador; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.colaborador (
    nombreproyecto character varying(255) NOT NULL,
    emailpersona character varying(255) NOT NULL
);


ALTER TABLE public.colaborador OWNER TO postgres;

--
-- Name: persona; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.persona (
    email character varying(255) NOT NULL,
    nombre character varying(50) NOT NULL,
    apellidos character varying(70) NOT NULL,
    password character varying(255) NOT NULL
);


ALTER TABLE public.persona OWNER TO postgres;

--
-- Name: proyecto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.proyecto (
    nombreproyecto character varying(255) NOT NULL,
    inicio date NOT NULL,
    fin date NOT NULL
);


ALTER TABLE public.proyecto OWNER TO postgres;

--
-- Name: tarea; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tarea (
    nombretarea character varying(255) NOT NULL,
    nombreproyecto character varying(255) NOT NULL,
    encargado character varying(255) NOT NULL,
    descripcion text NOT NULL
);


ALTER TABLE public.tarea OWNER TO postgres;

--
-- Data for Name: colaborador; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.colaborador (nombreproyecto, emailpersona) FROM stdin;
\.


--
-- Data for Name: persona; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.persona (email, nombre, apellidos, password) FROM stdin;
\.


--
-- Data for Name: proyecto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.proyecto (nombreproyecto, inicio, fin) FROM stdin;
\.


--
-- Data for Name: tarea; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tarea (nombretarea, nombreproyecto, encargado, descripcion) FROM stdin;
\.


--
-- Name: colaborador colaborador_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.colaborador
    ADD CONSTRAINT colaborador_pkey PRIMARY KEY (nombreproyecto, emailpersona);


--
-- Name: persona persona_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (email);


--
-- Name: proyecto proyecto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.proyecto
    ADD CONSTRAINT proyecto_pkey PRIMARY KEY (nombreproyecto);


--
-- Name: tarea tarea_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tarea
    ADD CONSTRAINT tarea_pkey PRIMARY KEY (nombretarea, nombreproyecto);


--
-- Name: colaborador colaborador_emailpersona_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.colaborador
    ADD CONSTRAINT colaborador_emailpersona_fkey FOREIGN KEY (emailpersona) REFERENCES public.persona(email) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: colaborador colaborador_nombreproyecto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.colaborador
    ADD CONSTRAINT colaborador_nombreproyecto_fkey FOREIGN KEY (nombreproyecto) REFERENCES public.proyecto(nombreproyecto) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: tarea constrain; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tarea
    ADD CONSTRAINT constrain FOREIGN KEY (nombreproyecto) REFERENCES public.proyecto(nombreproyecto) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: tarea tarea_encargado_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tarea
    ADD CONSTRAINT tarea_encargado_fkey FOREIGN KEY (encargado) REFERENCES public.persona(email) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

