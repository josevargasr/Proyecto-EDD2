package proyecto;


public class Campo {
    String tipo;
    String nom;
    

    public Campo(String nombre, String tipo) {
        this.nom = nombre;
        this.tipo = tipo;        
    }

    public Campo() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    
    @Override
    public String toString() {
        return  nom;
    }
}
