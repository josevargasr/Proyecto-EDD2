package proyecto;

import java.io.Serializable;

public class Registro implements Serializable{
    int llave;
    long byteOffset;
    
    public Registro() {
    }
 
    public Registro(int key) {
        this.llave = key;
    }

    public int getLlave() {
        return llave;
    }

    public void setLlave(int llave) {
        this.llave = llave;
    }

    public long getByteOffset() {
        return byteOffset;
    }

    public void setByteOffset(long byteOffset) {
        this.byteOffset = byteOffset;
    }
    
    @Override
    public String toString() {
        return llave +"-";
    }
    
}