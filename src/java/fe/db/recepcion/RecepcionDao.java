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

    public RCfdi getCfdi(String uuid) {
        RCfdi cfdi = null;
        
        Session ss = HibernateRecepcionApl.getSession();
        try {
            Query qry = ss.getNamedQuery("RCfdi.findUuid");
            qry.setParameter("uuid", uuid);
            
            List<RCfdi> lst = qry.getResultList();
            if ( lst != null ) {
                Iterator<RCfdi> it = lst.iterator();
                if ( it != null && it.hasNext() )
                    cfdi = it.next();
            }
        } finally {
            HibernateRecepcionApl.close(ss);
        }
        
        return cfdi;
    }

    public RCfdiPagos getCfdiPagos(String uuid) {
        RCfdiPagos cfdi = null;
        
        Session ss = HibernateRecepcionApl.getSession();
        try {
            Query qry = ss.getNamedQuery("RCfdiPagos.findUuid");
            qry.setParameter("uuid", uuid);
            
            List<RCfdiPagos> lst = qry.getResultList();
            if ( lst != null ) {
                Iterator<RCfdiPagos> it = lst.iterator();
                if ( it != null && it.hasNext() )
                    cfdi = it.next();
            }
        } finally {
            HibernateRecepcionApl.close(ss);
        }
        
        return cfdi;
    }

    public RCfdiNomina getCfdiNomina(String uuid) {
        RCfdiNomina cfdi = null;
        
        Session ss = HibernateRecepcionApl.getSession();
        try {
            Query qry = ss.getNamedQuery("RCfdiNomina.findUuid");
            qry.setParameter("uuid", uuid);
            
            List<RCfdiNomina> lst = qry.getResultList();
            if ( lst != null ) {
                Iterator<RCfdiNomina> it = lst.iterator();
                if ( it != null && it.hasNext() )
                    cfdi = it.next();
            }
        } finally {
            HibernateRecepcionApl.close(ss);
        }
        
        return cfdi;
    }    
}
