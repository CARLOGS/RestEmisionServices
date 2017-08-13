/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fe.db.licenses;

import fe.db.MItems;
import fe.db.MItemsBin;
import fe.db.MItemsLicencias;
import fe.db.MLicencias;
import fe.db.emision.EClientes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author CARLO
 */
public class LicenciasDao {
    public MLicencias getLicencia(Long id) {
        MLicencias lic = null;
        
        Session ss = HibernateLicenciasApl.getSession();
        Transaction tran = ss.beginTransaction();
        try {
            Criteria cr = ss.createCriteria(MLicencias.class);
            cr.add(Restrictions.eq("id", id));
            Iterator<MLicencias> it = cr.list().iterator();
            if ( it.hasNext() )
                lic = it.next();
            
            tran.commit();
        } finally {
            HibernateLicenciasApl.close(ss);
        }
        
        return lic;
    }

    public MLicencias getLicencia(String rfc) {
        MLicencias lic = null;
        
        Session ss = HibernateLicenciasApl.getSession();
        Transaction tran = ss.beginTransaction();
        try {
            Criteria cr = ss.createCriteria(MLicencias.class);
            cr.add(Restrictions.eq("rfc", rfc));
            Iterator<MLicencias> it = cr.list().iterator();
            if ( it.hasNext() )
                lic = it.next();
            
            tran.commit();
        } finally {
            HibernateLicenciasApl.close(ss);
        }
        
        return lic;
    }

    public byte[] getPlantilla(Long licencia) {
        byte[] bytes = null;
        
        Session ss = HibernateLicenciasApl.getSession();
        Transaction tran = ss.beginTransaction();
        try {
            Criteria cr = ss.createCriteria(MItemsBin.class);
            cr.add(Restrictions.eq("items", getItem(licencia)));
            
            Iterator<MItemsBin> it = cr.list().iterator();
            if ( it.hasNext() )
                bytes = it.next().getObjeto();
            
            tran.commit();
        } finally {
            HibernateLicenciasApl.close(ss);
        }

        return bytes;
    }

    public byte[] getPlantillaById(Long itemId) {
        byte[] bytes = null;
        
        Session ss = HibernateLicenciasApl.getSession();
        Transaction tran = ss.beginTransaction();
        try {
            Criteria cr0 = ss.createCriteria(MItems.class);
            cr0.add(Restrictions.eq("id", itemId != null ? itemId.intValue() : null));
            MItems mItem = (MItems)cr0.uniqueResult();
            
            Criteria cr = ss.createCriteria(MItemsBin.class);
            cr.add(Restrictions.eq("items", mItem == null ? getGenerica() : mItem));
            
            Iterator<MItemsBin> it = cr.list().iterator();
            if ( it.hasNext() )
                bytes = it.next().getObjeto();
            
            tran.commit();
        } finally {
            HibernateLicenciasApl.close(ss);
        }

        return bytes;
    }

    public MItems getItem(Long licencia) {
        MItems item = null;
        
        Session ss = HibernateLicenciasApl.getSession();
        Transaction tran = ss.beginTransaction();
        try {
            Criteria cr = ss.createCriteria(MItemsLicencias.class);
            cr = ss.createCriteria(MItemsLicencias.class);
            cr.add(Restrictions.eq("licenciasID", licencia));
            cr.add(Restrictions.eq("operacion", "+"));
            List<MItemsLicencias> lst = cr.list();
            
            ArrayList<Integer> alst = new ArrayList<Integer>();
            for ( MItemsLicencias mil : lst )
                alst.add(mil.getItemsID());

            if ( alst != null && alst.size() > 0 ) {
                cr = ss.createCriteria(MItems.class);
                cr.add(Restrictions.eq("tipo", "Report"));
                cr.add(Restrictions.eq("nombre", "cfdi01.jasper"));
                cr.add(Restrictions.in("id", alst));
            
                Iterator<MItems> it = cr.list().iterator();
                if ( it.hasNext() )
                    item = it.next();
                else
                    item = getGenerica();
            } else
                item = getGenerica();
            
            tran.commit();
        } finally {
            HibernateLicenciasApl.close(ss);
        }

        return item;
    }

    private MItems getGenerica() {
        MItems item = null;
        
        Session ss = HibernateLicenciasApl.getSession();
        Transaction tran = ss.beginTransaction();
        try {
            Criteria cr = ss.createCriteria(MItems.class);
            cr.add(Restrictions.eq("alcance", 2));
            cr.add(Restrictions.eq("tipo", "Report"));
            cr.add(Restrictions.eq("nombre", "cfdi01.jasper"));
            
            item = (MItems)cr.uniqueResult();
            
            tran.commit();
        } finally {
            HibernateLicenciasApl.close(ss);
        }

        return item;
    }

    public List<MItems> getAdendas(EClientes ctes) {
        List<MItems> lAdendas = new ArrayList<MItems>();
        Session ss = HibernateLicenciasApl.getSession();
        Transaction tran = ss.beginTransaction();
        try {
            Criteria cr = ss.createCriteria(MItemsLicencias.class);
            cr.add(Restrictions.eq("licenciasID", ctes.getLicenciaID()));
            cr.add(Restrictions.eq("operacion", "+"));
            
            Iterator<MItemsLicencias> it = cr.list().iterator();
            List<Integer> vItems = new ArrayList<Integer>();
            while(it.hasNext())
                vItems.add(it.next().getItemsID());
            
            Criteria cr2 = ss.createCriteria(MItems.class);
            if ( vItems == null || vItems.size() == 0 )
                return lAdendas;
            cr2.add(Restrictions.in("id", vItems));
            cr2.add(Restrictions.eq("tipo", "Addenda"));

            lAdendas = cr2.list();
            
            tran.commit();
        } finally {
            HibernateLicenciasApl.close(ss);
        }

        return lAdendas;
    }

    public List<MItems> getComplementos(EClientes ctes) {
        List<MItems> lAdendas = new ArrayList<MItems>();
        Session ss = HibernateLicenciasApl.getSession();
        Transaction tran = ss.beginTransaction();
        try {
            Criteria cr = ss.createCriteria(MItemsLicencias.class);
            cr.add(Restrictions.eq("licenciasID", ctes.getLicenciaID()));
            cr.add(Restrictions.eq("operacion", "+"));
            
            Iterator<MItemsLicencias> it = cr.list().iterator();
            List<Integer> vItems = new ArrayList<Integer>();
            while(it.hasNext())
                vItems.add(it.next().getItemsID());
            
            cr = ss.createCriteria(MItems.class);
            if ( vItems == null || vItems.size() == 0 )
                return lAdendas;
            cr.add(Restrictions.in("id", vItems));
            cr.add(Restrictions.eq("tipo", "Complemento"));
            
            lAdendas = cr.list();
            
            tran.commit();
        } finally {
            HibernateLicenciasApl.close(ss);
        }

        return lAdendas;
    }
}
