/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login.sistema.gestion.aulas.util;

import login.sistema.gestion.aulas.entidades.Usuario;

/**
 *
 * @author marck
 */
public class SesionUsuario {
    
    public static Usuario usuarioActual;
    
    public static void iniciar(Usuario usuario){
        usuarioActual = usuario;
    }
    
    public static Usuario getUsuarioActual(){
        return usuarioActual;
    }
    
    public static void cerrar(){
        usuarioActual = null;
    }
    
}
