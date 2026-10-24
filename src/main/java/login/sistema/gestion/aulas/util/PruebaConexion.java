package login.sistema.gestion.aulas.util;

import org.hibernate.Session;

/**
 * Clase utilizada para verificar la conexión
 * entre Hibernate y la base de datos MySQL.
 *
 * Esta prueba ejecuta una consulta sencilla (SELECT 1)
 * para comprobar que la conexión funciona correctamente.
 */


public class PruebaConexion {

    public static void main(String[] args) {
        // Abre una sesión de Hibernate para conectarse a MySQL.
        try (Session sesion = HibernateUtil
                .getSessionFactory()
                .openSession()) {
          // Ejecuta una consulta sencilla para verificar la conexión.
            sesion.createNativeQuery("SELECT 1", Integer.class)
                    .getSingleResult();
             // Muestra un mensaje si la conexión fue exitosa.
            System.out.println("CONEXION EXITOSA A MYSQL");

        } catch (Exception e) {
            // Captura y muestra cualquier error de conexión.
            System.err.println("ERROR DE CONEXION:");
            e.printStackTrace();
        }
    }
}