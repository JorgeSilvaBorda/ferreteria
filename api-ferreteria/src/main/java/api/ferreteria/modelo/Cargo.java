package api.ferreteria.modelo;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Clase para manejar la persistencia de la entidad CARGO en la base de datos.
 */
@ApplicationScoped //Indicación para que las librerías de persistencia incluyan esta clase en el mapeo y validación del modelo de datos de la base de datos.
@Entity(name = "CARGO") //Nombre de la tabla en base de datos para esta clase.
public class Cargo implements Serializable{ //Implementa serializable. Es necesario serializar las clases (convertirlas en texto o archivos), para poder manejar estados fuera de la BD.
    
    //Campo idcargo
    @Id //Anotación que indica que este campo de la clase es la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Anotación que indica que este campo se genera de manera automática (auto_increment en SQL).
    @Column(name = "IDCARGO") //Nombre de la columna en la tabla para este campo.
    Integer idcargo; //Declaración del campo en Java.
    
    //Campo nomcargo
    @Column(name = "NOMCARGO")//Nombre de la columna en la tabla para este campo.
    String nomcargo;//Declaración del campo en Java.

    //Constructor vacío
    public Cargo() {
    }

    //Constructor con todos los campos
    public Cargo(Integer idcargo, String nomcargo) {
        this.idcargo = idcargo;
        this.nomcargo = nomcargo;
    }

    //Getters y Setters (Requerido por persistencia).
    public Integer getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(Integer idcargo) {
        this.idcargo = idcargo;
    }

    public String getNomcargo() {
        return nomcargo;
    }

    public void setNomcargo(String nomcargo) {
        this.nomcargo = nomcargo;
    }
    
    @Override
    public String toString(){
        return "{idcargo: " + this.idcargo + ", nomcargo: " + this.nomcargo + "}";
    }
}
