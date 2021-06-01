package api.ferreteria.modelo;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@ApplicationScoped
@Entity(name = "PRODUCTO")
public class Producto implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPRODUCTO")
    Integer idproducto;
    
    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "IDFAMILIA")
    Familia familia;
    
    @Column(name = "NOMPRODUCTO")
    String nomproducto;
    
    @Column(name = "STOCKCRITICO")
    String stockcritico;

    public Producto() {
    }

    public Producto(Integer idproducto, Familia familia, String nomproducto, String stockcritico) {
        this.idproducto = idproducto;
        this.familia = familia;
        this.nomproducto = nomproducto;
        this.stockcritico = stockcritico;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public String getNomproducto() {
        return nomproducto;
    }

    public void setNomproducto(String nomproducto) {
        this.nomproducto = nomproducto;
    }

    public String getStockcritico() {
        return stockcritico;
    }

    public void setStockcritico(String stockcritico) {
        this.stockcritico = stockcritico;
    }

    
}