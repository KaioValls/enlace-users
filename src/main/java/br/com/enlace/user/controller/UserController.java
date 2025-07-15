package br.com.enlace.user.controller;

import br.com.enlace.user.domain.User;
import br.com.enlace.user.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/user")
public class UserController {

    private final UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @POST
    @Transactional
    public RestResponse<Void> create(User user, @Context UriInfo uriInfo){
        this.userService.create(user);
        return RestResponse.created(uriInfo.getAbsolutePath());
    }

    @GET
    public RestResponse<List<User>> getUsers(){
        List<User> users = userService.getUsers();
        return RestResponse.ok(users);
    }

    @GET
    @Path("/{userId}")
    public RestResponse<User> getUser(Long userId){
        User userById = userService.getUserById(userId);
        return RestResponse.ok(userById);
    }

    @DELETE
    @Transactional
    @Path("/{userId}")
    public RestResponse<Void> delete(Long userId){
        userService.delete(userId);
        return RestResponse.ok();
    }

    @PUT
    @Transactional
    public RestResponse<Void> update(User user){
        userService.update(user);
        return RestResponse.ok();
    }
}