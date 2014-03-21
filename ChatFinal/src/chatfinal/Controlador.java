/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatfinal;

import Chat.Servicio.*;
import Vista.Paneles.login;
import Vista.*;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author eduardo
 */
public class Controlador {

    Login log;
    Chat cha;
    Servidor servicio;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Controlador();
    }

    public Controlador() {
        Initialize();
        
       muestraLogin();
       
       // System.exit(0);
              
    }

    private void muestraLogin() {
        log.setVisible(true);
    }
    private void ocultaLogin() {
        log.setVisible(false);
    }


    private void muestraChat() {
        cha.setVisible(true);
    }
    
    private void ConetarMouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        ocultaLogin();
       
        muestraChat();
    }  
    private void IniciaServicioClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        ocultaLogin();
         IniciarServicio();
       // muestraChat();
    } 

    private void IniciarServicio() {
        servicio = new Servidor();
    }

    private void Initialize() {
        cha = new Chat(null, true);
       log = new Login(null,true);
       
       log.Get_login().getconetar().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ConetarMouseClicked(evt);
            }
        });
        log.Get_login().Iniciar_Servicio.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IniciaServicioClicked(evt);
            }
        });
    
    }
    
    
}
