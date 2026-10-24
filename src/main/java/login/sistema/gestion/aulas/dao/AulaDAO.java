/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login.sistema.gestion.aulas.dao;

import login.sistema.gestion.aulas.entidades.Aula;
import login.sistema.gestion.aulas.util.HibernateUtil; // O la ubicación de tu HibernateUtil
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class AulaDAO {

    public boolean guardar(Aula aula) {
        Transaction transaction = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            transaction = sesion.beginTransaction();
            sesion.persist(aula);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public List<Aula> obtenerTodas() {
        String hql = "FROM Aula";
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            return sesion.createQuery(hql, Aula.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
