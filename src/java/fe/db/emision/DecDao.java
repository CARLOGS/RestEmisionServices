/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fe.db.emision;

import fe.db.pagos.EPagos;
import fe.db.pagos.EPagosBorrador;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author CARLO
 */
public class DecDao {
    

    public ETipoAcceso getTipoAcceso(String tipo) {
        ETipoAcceso prov = null;

        Session ss = HibernateDecApl.getSession();
//        Transaction tran = ss.beginTransaction();
        try {
            Criteria cr = ss.createCriteria(ETipoAcceso.class);
            cr.add(Restrictions.eq("tipo", tipo));
            Iterator<ETipoAcceso> it = cr.list().iterator();
            if (it.hasNext()) {
                prov = it.next();
            }

//            tran.commit();
        } finally {
            HibernateDecApl.close(ss);
        }

        return prov;
    }

    public EClientes getCliente(String rfc) {
        EClientes cte = null;

        Session ss = HibernateDecApl.getSession();
//        Transaction tran = ss.beginTransaction();
        try {
//            Criteria cr = ss.createCriteria(EClientes.class);
//            cr.add(Restrictions.eq("rfc", rfc));
//            Iterator<EClientes> it = cr.list().iterator();
            org.hibernate.query.Query<EClientes> query = ss.getNamedQuery("DInstitucion.findAllRfc");
            query.setParameter("rfc", rfc);

            Iterator<EClientes> it = query.getResultList().iterator();
            if (it.hasNext()) {
                cte = it.next();
            }

//            tran.commit();
        } finally {
            HibernateDecApl.close(ss);
        }

        return cte;
    }

    public EClientes getCliente(Long cteId) {
        EClientes cte = null;

        Session ss = HibernateDecApl.getSession();
//        Transaction tran = ss.beginTransaction();
        try {
//            Criteria cr = ss.createCriteria(EClientes.class);
//            cr.add(Restrictions.eq("id", cteId));
//            Iterator<EClientes> it = cr.list().iterator();
            org.hibernate.query.Query<EClientes> query = ss.getNamedQuery("DInstitucion.findAllId");
            query.setParameter("id", cteId);

            Iterator<EClientes> it = query.getResultList().iterator();
            if (it.hasNext())
                cte = it.next();

//            tran.commit();
        } finally {
            HibernateDecApl.close(ss);
        }

        return cte;
    }

    public EPerfiles getPerfil(Long id) {
        EPerfiles per = null;

        Session ss = HibernateDecApl.getSession();
//        Transaction tran = ss.beginTransaction();
        try {
//            Criteria cr = ss.createCriteria(EPerfiles.class);
//            cr.add(Restrictions.eq("id", id));
//            Iterator<EPerfiles> it = cr.list().iterator();
            org.hibernate.query.Query<EPerfiles> query = ss.getNamedQuery("DPerfiles.findAllId");
            query.setParameter("id", id);

            Iterator<EPerfiles> it = query.getResultList().iterator();
            if (it.hasNext())
                per = it.next();

//            tran.commit();
        } finally {
            HibernateDecApl.close(ss);
        }

        return per;
    }

    public EAcceso getUsuarios(Long id) {
        EAcceso acc = null;

        Session ss = HibernateDecApl.getSession();
//        Transaction tran = ss.beginTransaction();
        try {
//            Criteria cr = ss.createCriteria(EAcceso.class);
//            cr.add(Restrictions.eq("id", id));
//            Iterator<EAcceso> it = cr.list().iterator();
//            if (it.hasNext()) {
//                acc = it.next();
//            }
            org.hibernate.query.Query<EAcceso> query = ss.getNamedQuery("DAcceso.findAllId");
            query.setParameter("id", id);

            Iterator<EAcceso> it = query.getResultList().iterator();
            if ( it.hasNext() )
                acc = it.next();

//            tran.commit();
        } finally {
            HibernateDecApl.close(ss);
        }

        return acc;
    }

    public EAcceso getUsuario(String user) {
        EAcceso acc = null;

        Session ss = HibernateDecApl.getSession();
//        Transaction tran = ss.beginTransaction();
        try {
//            Criteria cr = ss.createCriteria(EAcceso.class);
//            cr.add(Restrictions.eq("usuario", user));
//            Iterator<EAcceso> it = cr.list().iterator();
//            if (it.hasNext()) {
//                acc = it.next();
//            }
            org.hibernate.query.Query<EAcceso> query = ss.getNamedQuery("DAcceso.findAllNUs");
            query.setParameter("usuario", user);

            Iterator<EAcceso> it = query.getResultList().iterator();
            if (it.hasNext())
                acc = it.next();

//            tran.commit();
        } finally {
            HibernateDecApl.close(ss);
        }

        return acc;
    }

    public List<DCfdi> getRangoCFDIR(String anio, int mes, EClientes cte) {
        List<DCfdi> lst = null;
        Session session = HibernateDecApl.getSession();
//        Transaction tran = session.beginTransaction();
        try {
            // Fecha Inicio
            Calendar cal1 = Calendar.getInstance();
            cal1.set(Integer.parseInt(anio), mes, 1);
            // Fecha Fin
            Calendar cal2 = Calendar.getInstance();
            cal2.set(Integer.parseInt(anio), mes, 1);
            cal2.add(Calendar.MONTH, 1);

//            String sql
//                    = "SELECT "
//                    + "  c.RFC, "
//                    + "  c.NOMBRE, "
//                    + "  c.FECHA, "
//                    + "  c.FECHA_RECIBIDO, "
//                    + "  c.UUID, "
//                    + "  c.NUMERO_FACTURA, "
//                    + "  c.IMPORTE, "
//                    + "  c.RRFC, "
//                    + "  'Nacional', "
//                    + "  c.MONEDA, "
//                    + "  c.TIPO_CAMBIO, "
//                    + "  CASE WHEN pp.NOMBRE IS NULL THEN 'PROVEEDOR' ELSE pp.NOMBRE END AS TIPO "
//                    + "FROM E_CFDI c "
//                    + "  LEFT JOIN PROVEEDORES p "
//                    + "    ON c.proveedores_ID = p.ID "
//                    + "  LEFT JOIN PROVEEDORES_CLIENTES pc "
//                    + "    ON p.ID = pc.proveedores_ID "
//                    + "    AND pc.clientes_ID = :CTEID "
//                    + "  LEFT JOIN PERFIL_PROVEEDOR pp "
//                    + "    ON pc.perfilProveedor_ID = pp.ID "
//                    + "WHERE "
//                    + "  c.clientes_ID = :CTEID "
//                    + "  AND c.FECHA_RECIBIDO >= :FINI "
//                    + "  AND c.FECHA_RECIBIDO < :FFIN "
//                    + "  AND c.acceso_ID IS NULL";

//            Query qry = ss.createSQLQuery(sql);
//            qry.setLong("CTEID", cteID);
//            qry.setDate("FINI", cal1.getTime());
//            qry.setDate("FFIN", cal2.getTime());
//
//            lst = qry.list();
            org.hibernate.query.Query<DCfdi> query = session.getNamedQuery("DCfdi.findAllN2");
            query.setParameter("clientes", cte);
            query.setParameter("fIni", cal1.getTime());
            query.setParameter("fFin", cal2.getTime());
            lst = query.getResultList();

//            tran.commit();
        } finally {
            HibernateDecApl.close(session);
        }

        return lst;
    }

    public DCfdi getCfdi(Long id) {
        DCfdi cfdi = null;

        // Recupera Número de Factura(s)
        Session ss = HibernateDecApl.getSession();
//        Transaction tran = ss.beginTransaction();
        try {
//            Criteria cr = ss.createCriteria(DCfdi.class);
//            cr = cr.add(Restrictions.eq("id", id));
//
//            Iterator<DCfdi> it = cr.list().iterator();
            org.hibernate.query.Query<DCfdi> query = ss.getNamedQuery("DCfdi.findAllId");
            query.setParameter("id", id);

            Iterator<DCfdi> it = query.getResultList().iterator();

            if (it.hasNext()) {
                cfdi = it.next();
            }

//            tran.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace(System.err);
        } finally {
            HibernateDecApl.close(ss);
        }

        return cfdi;
    }

    public List<DCfdi> getQuerySearch(String nombre, String rfc, String uuid, Integer edoDoc, Long wf, Date fIni, Date fFin, EClientes cte) {
        List<DCfdi> lst = null;
        
        Session ss = HibernateDecApl.getSession();
//        Transaction tran = ss.beginTransaction();
        try {
            String str = "DCfdi.findAllN";
            if ( nombre != null)
                str = "DCfdi.findAllNNombre";
            else if ( rfc != null)
                str = "DCfdi.findAllNRfc";
            else if ( uuid != null)
                str = "DCfdi.findAllNUuid";
            else if ( edoDoc != null)
                str = "DCfdi.findAllNEdoDoc";
            else if ( wf != null)
                str = "DCfdi.findAllNWF";
            
            org.hibernate.query.Query query = ss
                    .getNamedQuery(str)
                    .setParameter("clientes", cte);
            
            if ( nombre != null)
                query.setParameter("nombre", "%" + nombre + "%");
            else if ( rfc != null)
                query.setParameter("rfc", rfc);
            else if ( uuid != null)
                query.setParameter("uuid", uuid);
            else if ( edoDoc != null)
                query.setParameter("estadoDocumento", edoDoc);
            else if ( wf != null)
                query.setParameter("wf", wf);
            else if (  fIni != null && fFin != null && wf == null) {
                query.setParameter("fIni", fIni);
                query.setParameter("fFin", fFin);
            }
            
            query.setMaxResults(100);
            
            lst = query.getResultList();

//            tran.commit();
        } finally {
            HibernateDecApl.close(ss);
        }

        return lst;
    }

    public List<DCfdi> getCfdiRazonSocial(String razon, EClientes cte) {
        List<DCfdi> lst = null;

        Session ss = HibernateDecApl.getSession();
//        Transaction tran = ss.beginTransaction();
        try {
            org.hibernate.query.Query<DCfdi> query = 
                ss.getNamedQuery("DCfdi.findAllNNombre");
            query.setParameter("nombre", "%" + razon + "%");
            query.setParameter("clientes", cte); 
            
            lst = query.getResultList();
            
//            tran.commit();
        } finally {
            HibernateDecApl.close(ss);
        }
        
        return lst;
    }

    public List<DCfdi> getCfdiRFC(String rfc, EClientes cte) {
        List<DCfdi> lst = null;

        Session ss = HibernateDecApl.getSession();
//        Transaction tran = ss.beginTransaction();
        try {
            org.hibernate.query.Query<DCfdi> query = 
                ss.getNamedQuery("DCfdi.findAllNRfc");
            query.setParameter("rfc", rfc);
            query.setParameter("clientes", cte); 
            
            lst = query.getResultList();
            
//            tran.commit();
        } finally {
            HibernateDecApl.close(ss);
        }
        
        return lst;
    }

    public List<DCfdi> getCfdiNoFactura(String noFactura, EClientes cte) {
        List<DCfdi> lst = null;

        Session ss = HibernateDecApl.getSession();
//        Transaction tran = ss.beginTransaction();
        try {
            org.hibernate.query.Query<DCfdi> query = 
                ss.getNamedQuery("DCfdi.findAllNNoFactura");
            query.setParameter("numeroFactura", noFactura);
            query.setParameter("clientes", cte); 
            
            lst = query.getResultList();
            
//            tran.commit();
        } finally {
            HibernateDecApl.close(ss);
        }
        
        return lst;
    }

    public DCfdi getCfdiUuid(String uuid) {
        DCfdi cfdi = null;

        Session ss = HibernateDecApl.getSession();
//        Transaction tran = ss.beginTransaction();
        try {
            org.hibernate.query.Query<DCfdi> query = 
                ss.getNamedQuery("DCfdi.findCfdiUuid");
            query.setParameter("uuid", uuid);
            
            for (DCfdi cf :  query.getResultList() ) {
                cfdi = cf;
                break;
            }
            
//            tran.commit();
        } finally {
            HibernateDecApl.close(ss);
        }
        
        return cfdi;
    }

    public EPagos getPagosUuid(String uuid) {
        EPagos epago = null;

        Session ss = HibernateDecApl.getSession();
//        Transaction tran = ss.beginTransaction();
        try {
            org.hibernate.query.Query<EPagos> query = 
                ss.getNamedQuery("EPagos.findAllUuid");
            query.setParameter("uuid", uuid);
            
            for (EPagos cf :  query.getResultList() ) {
                epago = cf;
                break;
            }
            
//            tran.commit();
        } finally {
            HibernateDecApl.close(ss);
        }
        
        return epago;
    }

    public EPagosBorrador getPagosBorradorUuid(String uuid) {
        EPagosBorrador epago = null;

        Session ss = HibernateDecApl.getSession();
//        Transaction tran = ss.beginTransaction();
        try {
            org.hibernate.query.Query<EPagosBorrador> query = 
                ss.getNamedQuery("EPagosBorrador.findAllUuid");
            query.setParameter("uuid", uuid);
            
            for (EPagosBorrador cf :  query.getResultList() ) {
                epago = cf;
                break;
            }
            
//            tran.commit();
        } finally {
            HibernateDecApl.close(ss);
        }
        
        return epago;
    }
}
