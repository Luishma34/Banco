PGDMP                         {            banco    14.4    14.4 /               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16394    banco    DATABASE     e   CREATE DATABASE banco WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE banco;
                postgres    false            �            1259    16396    cliente    TABLE       CREATE TABLE public.cliente (
    id_cliente integer NOT NULL,
    nome character varying,
    cpf character varying,
    data_nas character varying,
    telefone character varying,
    rg character varying,
    login character varying,
    senha character varying
);
    DROP TABLE public.cliente;
       public         heap    postgres    false            �            1259    16395    Cliente_id_cliente_seq    SEQUENCE     �   CREATE SEQUENCE public."Cliente_id_cliente_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public."Cliente_id_cliente_seq";
       public          postgres    false    210                       0    0    Cliente_id_cliente_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public."Cliente_id_cliente_seq" OWNED BY public.cliente.id_cliente;
          public          postgres    false    209            �            1259    32848    agencia    TABLE     �   CREATE TABLE public.agencia (
    id_agencia integer NOT NULL,
    nome character varying NOT NULL,
    endereco character varying NOT NULL,
    telefone character varying NOT NULL
);
    DROP TABLE public.agencia;
       public         heap    postgres    false            �            1259    32847    agencia_id_agencia_seq    SEQUENCE     �   CREATE SEQUENCE public.agencia_id_agencia_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.agencia_id_agencia_seq;
       public          postgres    false    219                       0    0    agencia_id_agencia_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.agencia_id_agencia_seq OWNED BY public.agencia.id_agencia;
          public          postgres    false    218            �            1259    24633    conta    TABLE     �   CREATE TABLE public.conta (
    id_conta integer NOT NULL,
    num_conta character varying NOT NULL,
    saldo double precision,
    id_cliente integer NOT NULL,
    id_agencia integer NOT NULL
);
    DROP TABLE public.conta;
       public         heap    postgres    false            �            1259    32839    conta_id_agencia_seq    SEQUENCE     �   CREATE SEQUENCE public.conta_id_agencia_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.conta_id_agencia_seq;
       public          postgres    false    211                       0    0    conta_id_agencia_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.conta_id_agencia_seq OWNED BY public.conta.id_agencia;
          public          postgres    false    217            �            1259    24645    conta_id_cliente_seq    SEQUENCE     �   CREATE SEQUENCE public.conta_id_cliente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.conta_id_cliente_seq;
       public          postgres    false    211                       0    0    conta_id_cliente_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.conta_id_cliente_seq OWNED BY public.conta.id_cliente;
          public          postgres    false    213            �            1259    24636    conta_id_conta_seq    SEQUENCE     �   CREATE SEQUENCE public.conta_id_conta_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.conta_id_conta_seq;
       public          postgres    false    211                        0    0    conta_id_conta_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.conta_id_conta_seq OWNED BY public.conta.id_conta;
          public          postgres    false    212            �            1259    32792    extrato    TABLE     1  CREATE TABLE public.extrato (
    id_extrato integer NOT NULL,
    id_cliente integer NOT NULL,
    tipo character varying NOT NULL,
    data timestamp with time zone NOT NULL,
    valor double precision,
    saldo double precision,
    origem character varying NOT NULL,
    destino character varying
);
    DROP TABLE public.extrato;
       public         heap    postgres    false            �            1259    32789    extrato_id_cliente_seq    SEQUENCE     �   CREATE SEQUENCE public.extrato_id_cliente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.extrato_id_cliente_seq;
       public          postgres    false    216            !           0    0    extrato_id_cliente_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.extrato_id_cliente_seq OWNED BY public.extrato.id_cliente;
          public          postgres    false    215            �            1259    32788    extrato_id_extrato_seq    SEQUENCE     �   CREATE SEQUENCE public.extrato_id_extrato_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.extrato_id_extrato_seq;
       public          postgres    false    216            "           0    0    extrato_id_extrato_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.extrato_id_extrato_seq OWNED BY public.extrato.id_extrato;
          public          postgres    false    214            t           2604    32851    agencia id_agencia    DEFAULT     x   ALTER TABLE ONLY public.agencia ALTER COLUMN id_agencia SET DEFAULT nextval('public.agencia_id_agencia_seq'::regclass);
 A   ALTER TABLE public.agencia ALTER COLUMN id_agencia DROP DEFAULT;
       public          postgres    false    219    218    219            n           2604    16399    cliente id_cliente    DEFAULT     z   ALTER TABLE ONLY public.cliente ALTER COLUMN id_cliente SET DEFAULT nextval('public."Cliente_id_cliente_seq"'::regclass);
 A   ALTER TABLE public.cliente ALTER COLUMN id_cliente DROP DEFAULT;
       public          postgres    false    210    209    210            o           2604    24637    conta id_conta    DEFAULT     p   ALTER TABLE ONLY public.conta ALTER COLUMN id_conta SET DEFAULT nextval('public.conta_id_conta_seq'::regclass);
 =   ALTER TABLE public.conta ALTER COLUMN id_conta DROP DEFAULT;
       public          postgres    false    212    211            p           2604    24646    conta id_cliente    DEFAULT     t   ALTER TABLE ONLY public.conta ALTER COLUMN id_cliente SET DEFAULT nextval('public.conta_id_cliente_seq'::regclass);
 ?   ALTER TABLE public.conta ALTER COLUMN id_cliente DROP DEFAULT;
       public          postgres    false    213    211            q           2604    32840    conta id_agencia    DEFAULT     t   ALTER TABLE ONLY public.conta ALTER COLUMN id_agencia SET DEFAULT nextval('public.conta_id_agencia_seq'::regclass);
 ?   ALTER TABLE public.conta ALTER COLUMN id_agencia DROP DEFAULT;
       public          postgres    false    217    211            r           2604    32795    extrato id_extrato    DEFAULT     x   ALTER TABLE ONLY public.extrato ALTER COLUMN id_extrato SET DEFAULT nextval('public.extrato_id_extrato_seq'::regclass);
 A   ALTER TABLE public.extrato ALTER COLUMN id_extrato DROP DEFAULT;
       public          postgres    false    214    216    216            s           2604    32796    extrato id_cliente    DEFAULT     x   ALTER TABLE ONLY public.extrato ALTER COLUMN id_cliente SET DEFAULT nextval('public.extrato_id_cliente_seq'::regclass);
 A   ALTER TABLE public.extrato ALTER COLUMN id_cliente DROP DEFAULT;
       public          postgres    false    215    216    216                      0    32848    agencia 
   TABLE DATA           G   COPY public.agencia (id_agencia, nome, endereco, telefone) FROM stdin;
    public          postgres    false    219   35                 0    16396    cliente 
   TABLE DATA           ^   COPY public.cliente (id_cliente, nome, cpf, data_nas, telefone, rg, login, senha) FROM stdin;
    public          postgres    false    210   �5                 0    24633    conta 
   TABLE DATA           S   COPY public.conta (id_conta, num_conta, saldo, id_cliente, id_agencia) FROM stdin;
    public          postgres    false    211   I6                 0    32792    extrato 
   TABLE DATA           d   COPY public.extrato (id_extrato, id_cliente, tipo, data, valor, saldo, origem, destino) FROM stdin;
    public          postgres    false    216   �6       #           0    0    Cliente_id_cliente_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public."Cliente_id_cliente_seq"', 22, true);
          public          postgres    false    209            $           0    0    agencia_id_agencia_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.agencia_id_agencia_seq', 2, true);
          public          postgres    false    218            %           0    0    conta_id_agencia_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.conta_id_agencia_seq', 1, false);
          public          postgres    false    217            &           0    0    conta_id_cliente_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.conta_id_cliente_seq', 1, false);
          public          postgres    false    213            '           0    0    conta_id_conta_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.conta_id_conta_seq', 7, true);
          public          postgres    false    212            (           0    0    extrato_id_cliente_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.extrato_id_cliente_seq', 1, false);
          public          postgres    false    215            )           0    0    extrato_id_extrato_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.extrato_id_extrato_seq', 27, true);
          public          postgres    false    214            v           2606    16403    cliente Cliente_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT "Cliente_pkey" PRIMARY KEY (id_cliente);
 @   ALTER TABLE ONLY public.cliente DROP CONSTRAINT "Cliente_pkey";
       public            postgres    false    210            |           2606    32855    agencia agencia_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.agencia
    ADD CONSTRAINT agencia_pkey PRIMARY KEY (id_agencia);
 >   ALTER TABLE ONLY public.agencia DROP CONSTRAINT agencia_pkey;
       public            postgres    false    219            x           2606    24644    conta conta_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.conta
    ADD CONSTRAINT conta_pkey PRIMARY KEY (id_conta);
 :   ALTER TABLE ONLY public.conta DROP CONSTRAINT conta_pkey;
       public            postgres    false    211            z           2606    32802    extrato extrato_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.extrato
    ADD CONSTRAINT extrato_pkey PRIMARY KEY (id_extrato);
 >   ALTER TABLE ONLY public.extrato DROP CONSTRAINT extrato_pkey;
       public            postgres    false    216            ~           2606    32856    conta conta_id_agencia_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.conta
    ADD CONSTRAINT conta_id_agencia_fkey FOREIGN KEY (id_agencia) REFERENCES public.agencia(id_agencia) NOT VALID;
 E   ALTER TABLE ONLY public.conta DROP CONSTRAINT conta_id_agencia_fkey;
       public          postgres    false    3196    211    219                       2606    32803    extrato extrato_id_cliente_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.extrato
    ADD CONSTRAINT extrato_id_cliente_fkey FOREIGN KEY (id_cliente) REFERENCES public.cliente(id_cliente);
 I   ALTER TABLE ONLY public.extrato DROP CONSTRAINT extrato_id_cliente_fkey;
       public          postgres    false    210    216    3190            }           2606    24653    conta id_cliente    FK CONSTRAINT     �   ALTER TABLE ONLY public.conta
    ADD CONSTRAINT id_cliente FOREIGN KEY (id_cliente) REFERENCES public.cliente(id_cliente) NOT VALID;
 :   ALTER TABLE ONLY public.conta DROP CONSTRAINT id_cliente;
       public          postgres    false    211    3190    210               v   x�3�tL?�*/93Q!�(H$�p:���e�$*�&�}�R3�u�95,L5��MtM��-��ڃS�K�R/j�*MTpJ�IT�,.�420��4762յ060����� �%M         �   x�m̻
1@�z�)���<�t�~���"�]Ee�ߤm/�#����q���s��E1���j �l�;K{_�4�h&�y<K����>�!t��|�^@�1����_;J
Yc�J���İ�?�s���,	         ,   x�ȱ 0����/�e���Q�UB�j·5a�&������         �   x��̽�0��ڞ�|���f(i,d�4	$�k�\��������cv�~���>;F��)`�U�5b@��6a��"En�s6��n���Iƈ#���)ǥM�/��tۖӊd���bʠe0���71�.�     