/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fe.db.mongo;

import java.io.Serializable;

/**
 *
 * @author CarloGS
 */
public class ItKeys implements Serializable {
    private byte [] key;
    private byte [] cer;

    public byte[] getKey() { return key; }
    public void setKey(byte[] key) { this.key = key; }

    public byte[] getCer() { return cer; }
    public void setCer(byte[] cer) { this.cer = cer; }
}
