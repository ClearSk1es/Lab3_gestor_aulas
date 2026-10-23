sql
-- ============================================
-- Base de datos: gestion_aulas
-- Sistema de Gestión de Aulas
-- ============================================

CREATE DATABASE IF NOT EXISTS gestion_aulas
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE gestion_aulas;

-- ============================================
-- Tabla: usuarios
-- Almacena los usuarios que pueden iniciar sesión
-- ============================================

CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    correo VARCHAR(150) NOT NULL UNIQUE,
    clave VARCHAR(255) NOT NULL,
    rol VARCHAR(30) NOT NULL DEFAULT 'USUARIO',
    estado VARCHAR(20) NOT NULL DEFAULT 'ACTIVO',

    CONSTRAINT chk_usuario_rol
        CHECK (rol IN ('ADMINISTRADOR', 'USUARIO')),

    CONSTRAINT chk_usuario_estado
        CHECK (estado IN ('ACTIVO', 'INACTIVO')),

    CONSTRAINT chk_usuario_nombre
        CHECK (CHAR_LENGTH(TRIM(nombre)) > 0),

    CONSTRAINT chk_usuario_apellido
        CHECK (CHAR_LENGTH(TRIM(apellido)) > 0),

    CONSTRAINT chk_usuario_correo
        CHECK (CHAR_LENGTH(TRIM(correo)) > 0),

    CONSTRAINT chk_usuario_clave
        CHECK (CHAR_LENGTH(TRIM(clave)) > 0)
);

-- ============================================
-- Tabla: aulas
-- Almacena la información de las aulas
-- ============================================

CREATE TABLE IF NOT EXISTS aulas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    nombre VARCHAR(30) NOT NULL,
    edificio VARCHAR(100) NOT NULL,
    planta INT NOT NULL,
    capacidad INT NOT NULL,

    tipo_aula ENUM(
        'NORMAL',
        'CENTRO_DE_COMPUTO',
        'LABORATORIO',
        'ESPECIAL'
    ) NOT NULL,

    responsable VARCHAR(150) NOT NULL,

    estado ENUM(
        'ASIGNADA',
        'ASIGNADA_TEMPORALMENTE',
        'DISPONIBLE',
        'EN_MANTENIMIENTO'
    ) NOT NULL DEFAULT 'DISPONIBLE',

    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- Planta y capacidad deben ser mayores que cero.
    CONSTRAINT chk_aula_planta
        CHECK (planta > 0),

    CONSTRAINT chk_aula_capacidad
        CHECK (capacidad > 0),

    -- Ningún campo de texto obligatorio puede estar vacío
    -- ni contener únicamente espacios.
    CONSTRAINT chk_aula_nombre
        CHECK (CHAR_LENGTH(TRIM(nombre)) > 0),

    CONSTRAINT chk_aula_edificio
        CHECK (CHAR_LENGTH(TRIM(edificio)) > 0),

    CONSTRAINT chk_aula_responsable
        CHECK (CHAR_LENGTH(TRIM(responsable)) > 0),

    -- No permite repetir el nombre de un aula
    -- dentro del mismo edificio.
    CONSTRAINT uq_aula_nombre_edificio
        UNIQUE (nombre, edificio)
);

-- ============================================
-- Datos iniciales de prueba
-- ============================================

INSERT INTO aulas
    (nombre, edificio, planta, capacidad,
     tipo_aula, responsable, estado)
VALUES
    ('B-204', 'Edificio B', 2, 35,
     'NORMAL', 'Simon Pablo', 'DISPONIBLE'),

    ('LAB-01', 'Edificio A', 1, 25,
     'LABORATORIO', 'Jose Ramirez', 'ASIGNADA'),

    ('CC-03', 'Edificio C', 3, 40,
     'CENTRO_DE_COMPUTO', 'Eduardo Marquez',
     'ASIGNADA_TEMPORALMENTE');

-- ============================================
-- Consultar las aulas registradas
-- ============================================

SELECT * FROM aulas;
