package api.ferreteria.service;

import api.ferreteria.modelo.Familia;
import api.ferreteria.modelo.Producto;
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

@Path("/producto")
public class MSProducto {
    @Inject EntityManager manager;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Producto> getProductos(){
        String query = "SELECT P FROM PRODUCTO P ORDER BY P.nomproducto ASC";
        return manager.createQuery(query, Producto.class).getResultList();
    }
    
    @GET
    @Path("/{idproducto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Producto getProducto(@PathParam("idproducto") Integer idproducto){
        String query = "SELECT P FROM PRODUCTO P WHERE P.idproducto = " + idproducto;
        List<Producto> productos = manager.createQuery(query, Producto.class).getResultList();
        if(productos.size() < 1){
            return new Producto();
        }
        return productos.get(0); 
    }
    
    @POST 
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Producto postProducto(Producto producto){
        manager.persist(producto);
        manager.flush(); 
        return manager.find(Producto.class, producto.getIdproducto());
    }
    
    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Producto patchProducto(Producto producto){
        manager.merge(producto);
        manager.flush();
        return manager.find(Producto.class, producto.getIdproducto());
    }
}
