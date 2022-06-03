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
    fin date NOT NULL,
    administrador character varying(255) NOT NULL
);


ALTER TABLE public.proyecto OWNER TO postgres;

--
-- Name: tarea; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tarea (
    nombretarea character varying(255) NOT NULL,
    nombreproyecto character varying(255) NOT NULL,
    encargado character varying(255) NOT NULL,
    descripcion text NOT NULL,
    completada boolean NOT NULL
);


ALTER TABLE public.tarea OWNER TO postgres;

--
-- Data for Name: colaborador; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.colaborador (nombreproyecto, emailpersona) FROM stdin;
Bellas Artes	saulgame2010@gmail.com
Bellas Artes	aaron@gmail.com
Redes	saulgame2010@gmail.com
Redes	aaron@gmail.com
Redes	edson@gmail.com
Distribuidos	saulgame2010@gmail.com
Distribuidos	zu├▒iga@gmail.com
Web	emillie@gmail.com
Web	saulgame2010@gmail.com
Web	aaron@gmail.com
Micros	saulgame2010@gmail.com
Micros	zu├▒iga@gmail.com
\.


--
-- Data for Name: persona; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.persona (email, nombre, apellidos, password) FROM stdin;
saulgame2010@gmail.com	Sa├║l	Garc├¡a Medina	saul
emillie@gmail.com	Emillie	P├®rez Flores	emillie
aaron@gmail.com	Aar├│n Gamaliel	S├ínchez Castro	gama
zu├▒iga@gmail.com	Luis Eduardo	Z├║├▒iga Vera	luis
edson@gmail.com	Edson Uriel	Flores	edson
pedro@gmail.com	Pedro	Gomez Gutierrez	pedro
\.


--
-- Data for Name: proyecto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.proyecto (nombreproyecto, inicio, fin, administrador) FROM stdin;
Redes	2022-03-01	2022-06-08	aaron@gmail.com
Bellas Artes	2022-01-31	2022-06-10	saulgame2010@gmail.com
Distribuidos	2022-05-25	2022-06-12	zu├▒iga@gmail.com
Web	2022-03-01	2022-06-30	emillie@gmail.com
Micros	2022-05-15	2022-06-02	zu├▒iga@gmail.com
\.


--
-- Data for Name: tarea; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tarea (nombretarea, nombreproyecto, encargado, descripcion, completada) FROM stdin;
Agregar controles	Bellas Artes	saulgame2010@gmail.com	hola	t
Hacer lo de snmp	Redes	saulgame2010@gmail.com	hola	t
Comprar servomotores	Micros	zu├▒iga@gmail.com	hola	f
Arreglar normales	Bellas Artes	aaron@gmail.com	hola	t
Hacer el codigo del relok	Micros	zu├▒iga@gmail.com	hola	f
Detectar topolog├¡a	Redes	aaron@gmail.com	hola	t
Agregar CSS	Web	emillie@gmail.com	Hay que agregar el css porque se ve dlv este proyecto jajajaja	f
Hacer las gr├â┬íficas	Web	aaron@gmail.com	Hay que hacer que hacer las gr├â┬íficas de acuerdo con los datos del proyecto	f
Hacer el reporte	Micros	saulgame2010@gmail.com	Tienes que acabar el reporte donde hay que poner el c├â┬│digo	f
Buenas tienes	Distribuidos	saulgame2010@gmail.com	caca	f
Tarea que no es tuya	Distribuidos	emillie@gmail.com	ssss	f
nueva tarea asi es	Web	edson@gmail.com	Aqui vamos a hacer una prueba cacosa	f
Comprar servos	Micros	pedro@gmail.com	hola hay que comprar servos	f
Hacer la presentacion por fa	Redes	aaron@gmail.com	hola por favor haz la presentaci├â┬│n we	f
pendejo	Redes	saulgame2010@gmail.com	puto modificado WEEE CABRON LA PUTAMADRE	f
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

