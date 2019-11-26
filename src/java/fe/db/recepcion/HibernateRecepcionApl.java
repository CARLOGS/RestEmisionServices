package fe.db.recepcion;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author Carlo
 */
public class HibernateRecepcionApl {

    private static final SessionFactory sessionFactory;

    static {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure("hibernate.recepcion_apl.cfg.xml") // configures settings from hibernate.cfg.xml
            .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.out);
            StandardServiceRegistryBuilder.destroy( registry );
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void close(Session session) {
        try {
            session.close();
        } catch (Exception e) {
        }
    }
}