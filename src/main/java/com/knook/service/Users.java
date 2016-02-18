package com.knook.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.knook.dao.UserDao;
import com.knook.model.User;
import com.knook.serializer.UserSerializer;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
public class Users {

    private GsonBuilder builder = new GsonBuilder()
        .setPrettyPrinting()
        .registerTypeAdapter(User.class, new UserSerializer());;
    private Gson gson = builder.create();
    
    private UserDao userDao = new UserDao();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public String listUsers() {
        return gson.toJson(userDao.list());
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(@PathParam("id") Long id) {
        return gson.toJson(userDao.get(id));
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        if (userDao.create(user)) {
            return Response.status(Response.Status.OK).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User user, @PathParam("id") Long id) {
        user.setId(id);
        if (userDao.update(user)) {
            return Response.status(Response.Status.OK).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") Long id) {
        User user = userDao.get(id);
        if (user != null && userDao.delete(user)) {
            return Response.status(Response.Status.OK).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
