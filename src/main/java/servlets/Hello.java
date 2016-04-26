package servlets;

import modelo.servicios.UserService;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("hello")
@Stateless
public class Hello {
    @GET
    public String hello() {
        UserService userService = new UserService();
        userService.add("password", "email", "ubicación", "username");
        return "Hello";
    }

    @GET
    @Path("{multiplicando}")
    public int duplica(@PathParam("multiplicando") int multiplicando) {
        return 2 * multiplicando;
    }

    @GET
    @Path("new")
    @Produces(MediaType.TEXT_PLAIN)
    public String newMethod(@QueryParam("name") String name, @QueryParam("surname") String surname) {
        return name.toUpperCase() + "_" + surname.toUpperCase();
    }
}
