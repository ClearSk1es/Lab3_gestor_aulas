# Sistema de Gestión de Aulas

Aplicación de escritorio para la gestión de aulas universitarias, desarrollada con Java, JavaFX, Maven, Hibernate y MySQL.

## Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- JDK 17 o superior, compatible con la configuración del proyecto.
- Apache Maven.
- MySQL Server.
- NetBeans o un IDE compatible con proyectos Maven.

## Configuración de Hibernate

Cada desarrollador debe configurar su propia conexión a la base de datos. Por seguridad, las credenciales locales no se incluyen en el repositorio.

### 1. Crear el archivo de configuración

Dentro del proyecto, accede a:

`src/main/resources/hibernate/`

Encontrarás el archivo `hibernate.cfg.xml.example`, que contiene una plantilla de configuración.

Copia ese archivo en el mismo directorio y renombra la copia como:

`hibernate.cfg.xml`

Al finalizar, ambos archivos deben coexistir:

- `hibernate.cfg.xml.example`: plantilla compartida mediante Git.
    
- `hibernate.cfg.xml`: configuración local de cada desarrollador, excluida del repositorio.
    

### 2. Configurar la conexión a MySQL

Abre `hibernate.cfg.xml` y ajusta las propiedades de conexión para que coincidan con tu instalación local:

- **URL de conexión:** dirección del servidor, puerto y nombre de la base de datos.
- **Usuario:** usuario de MySQL con los permisos necesarios.
- **Contraseña:** contraseña correspondiente al usuario configurado.

No compartas tus credenciales ni las incluyas en otros archivos del repositorio.

### 3.Configuración de la base de datos

### Crear la base de datos

El proyecto incluye un script SQL que crea la base de datos, las tablas y sus respectivas restricciones.

El archivo se encuentra en:

`database/db_setup.sql`

Para ejecutarlo:

1. Abre MySQL Workbench o phpMyAdmin.
2. Abre o importa el archivo `database/db_setup.sql`.
3. Ejecuta el script.
4. Comprueba que se haya creado la base de datos `gestion_aulas` y que contenga las tablas correspondientes.

El script incluye datos de prueba para facilitar la verificación inicial de la aplicación.

### 4. Preparar la base de datos

Asegúrate de que la base de datos exista y de que su esquema sea compatible con las entidades del proyecto.

La configuración actual de Hibernate utiliza `hbm2ddl.auto=validate`. Esto significa que Hibernate verifica que el esquema existente sea compatible con los mapeos, pero no crea ni actualiza automáticamente las tablas.

Si todavía no tienes la estructura de la base de datos, consulta el script SQL del proyecto.

## Colaboración

Antes de comenzar a trabajar, descarga los últimos cambios del repositorio y configura tu entorno local.

No subas archivos con contraseñas, credenciales ni configuraciones personales.