/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login.sistema.gestion.aulas.dao;


import login.sistema.gestion.aulas.entidades.Usuario;
import login.sistema.gestion.aulas.util.HibernateUtil;
import org.hibernate.Session;


/**
 *
 * @author marck
 */
public class UsuarioDAO {
    
    public Usuario autenticar(String correo, String clave){
        String hql = "FROM Usuario u "
        + "WHERE u.Correo = :correo "
        + "AND u.Clave = :clave "
        + "AND u.Estado = 'ACTIVO'";
        
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()){
            return sesion.createQuery(hql, Usuario.class)
                    .setParameter("correo", correo)
                    .setParameter("clave", clave)
                    .uniqueResultOptional()
                    .orElse(null);
        }
        
    }
    
}
