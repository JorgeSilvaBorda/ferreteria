package api.ferreteria.service;

import api.ferreteria.modelo.Empleado;
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
 * Microservicio para CRUD de Empleado
 */

//Ruta en la que se invoca a este microservicio
@Path("/empleado")
public class MSEmpleado {
    
    @Inject EntityManager manager;
    
    /**
     * Método para listar empleados.
     * @return Collection<Empleado> con la lista de empleados que hay en la BD.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Empleado> getEmpleados(){
        //Al buscar el listado de empleados, el modelo de persistencia se encarga de amarrar con los cargos.
        String query = "SELECT E FROM EMPLEADO E";
        return manager.createQuery(query, Empleado.class).getResultList();
    }
    
    /**
     * Método para obtener un empleado por ID
     * @param idempleado Integer que viaja en la URL para la búsqueda
     * @return Empleado.
     */
    @GET
    @Path("/{idempleado}")
    @Produces(MediaType.APPLICATION_JSON)
    public Empleado getEmpleado(@PathParam("idempleado") Integer idempleado){
        String query = "SELECT E FROM EMPLEADO E WHERE E.idempleado = " + idempleado;
        List<Empleado> empleados = manager.createQuery(query, Empleado.class).getResultList();
        if(empleados.size() < 1){
            return new Empleado();
        }
        return empleados.get(0);
    }
    
    /**
     * Método para publicar un Empleado
     * @param empleado Empleado a publicar.
     * @return Empleado publicado.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Empleado postEmpleado(Empleado empleado){
        manager.persist(empleado);
        manager.flush();
        return manager.find(Empleado.class, empleado.getIdempleado());
    }
    
    /**
     * Método para hacer update sobre un empleado.
     * @param empleado Empleado con sus valores modificados.
     * @return Empleado modificado.
     */
    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Empleado patchEmpleado(Empleado empleado){
        manager.merge(empleado);
        manager.flush();
        return manager.find(Empleado.class, empleado.getIdempleado());
    }
    
}
