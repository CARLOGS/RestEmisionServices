/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fe.db;

import java.io.Serializable;

/**
 *
 * @author Carlo García Sánchez
 */
public class ItemId implements Serializable {
    private int id = 0;
    private String desscripcion = "";

    public ItemId(int id, String descripcion) {
        this.id = id;
        this.desscripcion = descripcion;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the desscripcion
     */
    public String getDesscripcion() {
        return desscripcion;
    }

    /**
     * @param desscripcion the desscripcion to set
     */
    public void setDesscripcion(String desscripcion) {
        this.desscripcion = desscripcion;
    }
    
    @Override
    public String toString() {
        return desscripcion;
    }
}
