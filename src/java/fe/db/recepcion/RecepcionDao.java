/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fe.db.recepcion;

import java.util.Iterator;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author CarloGS
 */
public class RecepcionDao {

    public MCfdi getCfdi(String uuid) {
        MCfdi cfdi = null;
        
        Session ss = HibernateRecepcionApl.getSession();
        try {
            Query qry = ss.getNamedQuery("MCfdi.findUuid");
            qry.setParameter("uuid", uuid);
            
            List<MCfdi> lst = qry.getResultList();
            if ( lst != null ) {
                Iterator<MCfdi> it = lst.iterator();
                if ( it != null && it.hasNext() )
                    cfdi = it.next();
            }
        } finally {
            HibernateRecepcionApl.close(ss);
        }
        
        return cfdi;
    }
    
}
