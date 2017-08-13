/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fe.db;

import java.io.Serializable;

/**
 *
 * @author CARLO
 */
public class M_Flujo implements Serializable {
    private Long id;
    private String nombre;
    private static final long serialVersionUID = 1L;
    
    public M_Flujo(String nombre, Long id) {
        this.nombre = nombre;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}
