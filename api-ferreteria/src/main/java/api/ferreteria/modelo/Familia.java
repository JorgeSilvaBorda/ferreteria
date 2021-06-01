package api.ferreteria.modelo;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ApplicationScoped
@Entity(name = "FAMILIA")
public class Familia implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDFAMILIA")
    Integer idfamilia;
    
    @Column(name = "NOMFAMILIA")
    String nomfamilia;

    public Familia() {
    }

    public Familia(Integer idfamilia, String nomfamilia) {
        this.idfamilia = idfamilia;
        this.nomfamilia = nomfamilia;
    }

    public Integer getIdfamilia() {
        return idfamilia;
    }

    public void setIdfamilia(Integer idfamilia) {
        this.idfamilia = idfamilia;
    }

    public String getNomfamilia() {
        return nomfamilia;
    }

    public void setNomfamilia(String nomfamilia) {
        this.nomfamilia = nomfamilia;
    }
    
    
}
