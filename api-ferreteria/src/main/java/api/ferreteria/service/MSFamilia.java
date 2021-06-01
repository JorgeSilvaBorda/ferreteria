package api.ferreteria.service;

import api.ferreteria.modelo.Cargo;
import api.ferreteria.modelo.Familia;
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

@Path("/familia")
public class MSFamilia {
    
    @Inject EntityManager manager;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Familia> getFamilias(){
        String query = "SELECT F FROM FAMILIA F ORDER BY F.nomfamilia ASC";
        return manager.createQuery(query, Familia.class).getResultList();
    }
    
    @GET
    @Path("/{idfamilia}")
    @Produces(MediaType.APPLICATION_JSON)
    public Familia getFamilia(@PathParam("idfamilia") Integer idfamilia){
        String query = "SELECT F FROM FAMILIA F WHERE F.idfamilia = " + idfamilia;
        List<Familia> familias = manager.createQuery(query, Familia.class).getResultList();
        if(familias.size() < 1){
            return new Familia();
        }
        return familias.get(0); 
    }
    
    @POST 
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Familia postFamilia(Familia familia){
        manager.persist(familia);
        manager.flush(); 
        return manager.find(Familia.class, familia.getIdfamilia());
    }
    
    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Familia patchFamilia(Familia familia){
        manager.merge(familia);
        manager.flush();
        return manager.find(Familia.class, familia.getIdfamilia());
    }
}
