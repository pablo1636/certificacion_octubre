/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import modelo.*;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author pablo
 */
public class operaciones {
    
       
    public static List<Curso> cursosVer(int id){
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        List<Curso> cursos = session.createCriteria(Curso.class).add(Restrictions.eq("idTematica", id)).list();
        tx.commit();
        session.close();
        return cursos;
    }
    public static List<Tematica> tematicaVer(){
        List<Tematica> listaTematica;
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Criteria crit = session.createCriteria(Tematica.class);
        listaTematica = crit.list();
        tx.commit();
        session.close();
        return listaTematica;
    
    }
    
     public static int cantidadCursos(String mail){
        //List<Inscripcion> inscripcion; 
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        List<Inscripcion> inscripciones = session.createCriteria(Inscripcion.class).add(Restrictions.eq("mail", mail)).list();
        int cantidad = inscripciones.size();
        tx.commit();
        session.close();
        try{
        return cantidad;
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, "No se puede calcular cantidad", "" ,JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
            cantidad = 0;
            return cantidad;
        }
        
        
        
            
    }
     public static boolean cupo(Integer id){
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Curso curso = (Curso) session.createCriteria(Curso.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
        tx.commit();
        session.close();
        if (curso.getCupo() > 0){
            return true;
        } else {
            return false;
        }
        
     }
    public static int guardarinscripcion(Inscripcion inscripcion){
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        int idGenerado = ( int )session.save( inscripcion );
        // session.save( inscripcion );
        tx.commit();
        session.close();
        return idGenerado;
        // JOptionPane.showMessageDialog(null, "Empleado insertado correctamente");
    }
    
    
    
    
    public static Participante buscar (String email){
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = session.beginTransaction();
        Participante part = (Participante) session.createCriteria(Participante.class)
                .add(Restrictions.eq("email", email)).uniqueResult();
        tx.commit();
        session.close();
        try {
            if(part!=null){
                    JOptionPane.showMessageDialog(null, "Estudiante encontrado ", "" ,JOptionPane.INFORMATION_MESSAGE);
                    return part;
            }else{
                JOptionPane.showMessageDialog(null, "Estudiante no Existe ", "" ,JOptionPane.ERROR_MESSAGE);
                return part;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Estudiante no existe", "" ,JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
            return part;
        }
    }
     








}
