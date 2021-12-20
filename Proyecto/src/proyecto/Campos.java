package proyecto;


public class Campos {
    
    public char[] nombre;
    public int tipoCampo;
    public long tamByte;
    public long tam;
    public boolean llave = false;
    public String cad = "123456789012345678901234567890";

    public boolean entro = false;

    public Campos() {
        long Bytes_en_Caracteres = 5 + 1;
        this.tamByte = 8 + 8 + 4 + Bytes_en_Caracteres + 1;
    }

    @SuppressWarnings("empty-statement")
    public Campos(int FieldType, long size_dec, boolean key) {
        this.tipoCampo = FieldType;
        long Bytes_en_Caracteres = 5 + 1;
        this.tamByte = 8 + 8 + 4 + Bytes_en_Caracteres + 1;
        this.tam = size_dec;
        this.llave = key;
    }

    public boolean agregarNombre(String a) {
        if (a.length() <= cad.length()) {
            if (entro == false) {
                nombre = new char[a.length()];
                for (int i = 0; i < a.length(); i++) {
                    nombre[i] = a.charAt(i);
                }
                tamByte += (a.length());
                cad = a;
                entro = true;
            } else {
                for (int i = 0; i < cad.length(); i++) {
                    if (i < a.length()) {
                        nombre[i] = a.charAt(i);
                    } else {
                        nombre[i] = ' ';
                        a += " ";
                    }
                }
                cad = a;
            }
            return true;
        } else {
            System.out.println("El Registro es Muy Grande debe ser mas pequeño");
            return false;
        }
    }

    public char[] getNombre() {
        return nombre;
    }

    public char getChar(int x) {
        return nombre[x];
    }

    public void setNombre(char[] nombre) {
        this.nombre = nombre;
    }

    public int getTipoCampo() {
        return tipoCampo;
    }

    public void setTipoCampo(int tipoCampo) {
        this.tipoCampo = tipoCampo;
    }

    public Long getSizeBytes() {
        return tamByte;
    }

    public void setTamByte(long tamByte) {
        this.tamByte = tamByte;
    }

    public Long getSize_dec() {
        return tam;
    }

    public void setTam(long tam) {
        this.tam = tam;
    }

    public boolean isLlave() {
        return llave;
    }

    public void setLlave(boolean llave) {
        this.llave = llave;
    }

    @Override
    public String toString() {
        return cad + "|" + tipoCampo + "|" + llave;
    }

}