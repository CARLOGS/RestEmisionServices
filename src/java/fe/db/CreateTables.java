package fe.db;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
// import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;

public class CreateTables {
	public static void main(String[] args) throws Exception {
            new CreateTables().process();
        }
        
        public void process() throws Exception {
    	Configuration cfg = new Configuration().configure();

    	// Statistics
        SessionFactory sessionFactory = cfg.buildSessionFactory();
//        Statistics stats = sessionFactory.getStatistics();
//        stats.setStatisticsEnabled(true);

		// Crea Schema de tablas
//    	SchemaExport se = new SchemaExport(cfg);
    	// (show scripts,drop tables)
//    	se.create(true, false);
    	
    	SessionFactory factory = cfg.buildSessionFactory();
    	Session session = factory.getCurrentSession();
    	Transaction tran = session.beginTransaction();
        
    	// Select fr tabla Acceso
		List acceso = session.createCriteria(MLicencias.class)
     		.add( Restrictions.like("usuario", "adm%") )
     		.setMaxResults(100)
     		.list();

		// rs.next()
    	Iterator i = acceso.iterator();
    	while(i.hasNext()) {
    		MLicencias msg = (MLicencias)i.next();
                System.out.append( msg.getRfc() );
    	}

//    	System.out.println ( "Connections: " + stats.getConnectCount() );
//
        tran.commit();
//    	session.close();
    }
}