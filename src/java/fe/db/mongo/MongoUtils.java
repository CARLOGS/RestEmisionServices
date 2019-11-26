/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fe.db.mongo;

import fe.pki.PKI;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.crypto.Cipher;

/**
 *
 * @author CarloGS
 */
public class MongoUtils {

    public static synchronized byte[] getZip(byte[] xml, String name) throws Exception {
        byte[] bytes = null;
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ZipOutputStream zout = new ZipOutputStream(out);
        zout.putNextEntry(new ZipEntry(name));
        zout.write(xml);
        zout.close();

        bytes = out.toByteArray();
        
        return bytes;
    }
    
    public static synchronized byte [] cipher(byte [] bytes) throws Exception {
        return new PKI().cipher_bytes( bytes, "sebh12#", "Blowfish", Cipher.ENCRYPT_MODE );
    }

    public static synchronized byte[] getZipCipher(byte[] xml, String name) throws Exception {
        return cipher( getZip(xml, name) );
    }
    
}
