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

/**
 * Clase para manejar la persistencia de la entidad CARGO en la base de datos.
 */
@ApplicationScoped
@Entity(name = "EMPLEADO")
public class Empleado implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDEMPLEADO")
    Integer idempleado;
    
    /**
     * En este caso, existe una relación con la entidad CARGO, pero no indicamos su ID cargo como se encuentra en la Base de datos.
     * Se indica un objeto del tipo "Cargo" dentro de esta clase.
     * El modelo de persistencia se encarga de hacer los JOIN requeridos en este caso al momento de listar.
     */
    
    //Esta anotación indica que hay una relación de "Muchos a Uno" desde EMPLEADO hacia CARGO, puesto que los empleados DEBEN tener un cargo
    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "IDCARGO") //El nombre de la columna en la tabla EMPLEADO que tiene la llave foránea de la tabla CARGO.
    Cargo cargo;
    
    @Column(name = "RUTEMPELADO")
    String rutempleado;
    @Column(name = "NOMEMPLEADO")
    String nomempleado;
    @Column(name = "APPATERNO")
    String appaterno;
    @Column(name = "APMATERNO")
    String apmaterno;

    //Constructores
    public Empleado() {
    }

    public Empleado(Integer idempleado, Cargo cargo, String rutempleado, String nomempleado, String appaterno, String apmaterno) {
        this.idempleado = idempleado;
        this.cargo = cargo;
        this.rutempleado = rutempleado;
        this.nomempleado = nomempleado;
        this.appaterno = appaterno;
        this.apmaterno = apmaterno;
    }

    //Getters y Setters
    public Integer getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Integer idempleado) {
        this.idempleado = idempleado;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public String getRutempleado() {
        return rutempleado;
    }

    public void setRutempleado(String rutempleado) {
        this.rutempleado = rutempleado;
    }

    public String getNomempleado() {
        return nomempleado;
    }

    public void setNomempleado(String nomempleado) {
        this.nomempleado = nomempleado;
    }

    public String getAppaterno() {
        return appaterno;
    }

    public void setAppaterno(String appaterno) {
        this.appaterno = appaterno;
    }

    public String getApmaterno() {
        return apmaterno;
    }

    public void setApmaterno(String apmaterno) {
        this.apmaterno = apmaterno;
    }
    
    
}
