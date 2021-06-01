package api.ferreteria.service;

import api.ferreteria.modelo.Cargo;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Microservicio para CRUD de Cargo
 */

//Ruta en la que se invoca a este microservicio
@Path("/cargo")
public class MSCargo{
    
    //Injección de clase EntityManager para administrar la persistencia en Base de Datos
    @Inject EntityManager manager;
    
    /**
     * Método para listar todos los cargos.
     * No recibe parámetros, es para listarlos todos
     * @return Collection<Cargo>
     */
    @GET //Método GET de HTTP (Para leer)
    @Produces(MediaType.APPLICATION_JSON)  //Tipo de salida del microservicio. En este caso el formato es JSON.
    public Collection<Cargo> getCargos(){
        String query = "SELECT C FROM CARGO C ORDER BY C.nomcargo ASC"; //Query JPQL para traer todos los cargos.
        return manager.createQuery(query, Cargo.class).getResultList(); //El EntityManager inyectado al comienzo se encarga del manejo de las clases.
    }
    
    /**
     * Método para obtener un Cargo basado en un IDCARGO
     * @param idcargo Integer con el IDCARGO que queremos buscar
     * @return Cargo.
     */
    @GET //Método GET de HTTP (Para leer)
    @Path("/{idcargo}") //Parámetro que se agrega en la URL, para quedar de esta manera: http://api.js-services.tk/api/ferreteria/cargo/1
    @Produces(MediaType.APPLICATION_JSON) //Tipo de salida del microservicio. En este caso el formato es JSON.
    public Cargo getCargo(@PathParam("idcargo") Integer idcargo){
        String query = "SELECT C FROM CARGO C WHERE C.idcargo = " + idcargo; //Query JPQL para traer el cargo por su ID.
        List<Cargo> cargos = manager.createQuery(query, Cargo.class).getResultList(); //Pedimos el Cargo a EntityManager y guardamos en una lista lo que venga.
        if(cargos.size() < 1){ //Si no hay cargos, retornamos un nuevo cargo vacío.
            return new Cargo();
        }
        return cargos.get(0); //Retornar el cargo encontrado por ID.
    }
    
    /**
     * Método para publicar (insertar) un Cargo
     * @param cargo Cargo a publicar
     * @return Cargo. El cargo insertado.
     */
    @POST //Método POST de HTTP (Para escribir)
    @Produces(MediaType.APPLICATION_JSON) //Tipo de salida del microservicio. En este caso el formato es JSON.
    @Consumes(MediaType.APPLICATION_JSON) //Tipo de entrada del microservicio. En este caso el formato es JSON.
    @Transactional //Transaccional le indica al modelo de persistencia que va a realizar operaciones de NO lectura en la Base de Datos
    public Cargo postCargo(Cargo cargo){
        manager.persist(cargo); //El EntityManager publica el cargo
        manager.flush(); //Limpia su contenido
        return manager.find(Cargo.class, cargo.getIdcargo()); //Devuelve el cargo que acaba de publicar.
    }
    
    /**
     * Método para "parchar" (update) un Cargo
     * @param cargo Cargo con sus campos ya modificados desde la vista, pero con el ID que le corresponde.
     * @return Cargo modificado.
     */
    @PATCH //Método PATCH de HTTP (Para hacer update de algo)
    @Produces(MediaType.APPLICATION_JSON) //Tipo de salida del microservicio. En este caso el formato es JSON.
    @Consumes(MediaType.APPLICATION_JSON) //Tipo de entrada del microservicio. En este caso el formato es JSON.
    @Transactional //Transaccional le indica al modelo de persistencia que va a realizar operaciones de NO lectura en la Base de Datos
    public Cargo patchCargo(Cargo cargo){
        manager.merge(cargo); //EntityManager se encarga de actualizar lo que haya cambiado en el objeto sobre la BD.
        manager.flush(); //Limpia su contenido.
        return manager.find(Cargo.class, cargo.getIdcargo()); //Devuelve el Cargo que acaba de actualizar.
    }
}
