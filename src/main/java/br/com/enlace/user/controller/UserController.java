package br.com.enlace.user.controller;

import br.com.enlace.user.domain.User;
import br.com.enlace.user.service.UserService;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.mutiny.Uni;
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
    public Uni<RestResponse<Void>> create(User user, @Context UriInfo uriInfo){
        return this.userService.create(user)
                .replaceWith(RestResponse.created(uriInfo.getAbsolutePath()));
    }

    @PUT
    @NonBlocking
    @Path("/addGroup")
    public Uni<RestResponse<Void>> addGroup(@QueryParam("userId") Long userId,@QueryParam("groupId") Long groupId, @Context UriInfo uriInfo){
        System.out.println(groupId);
        System.out.println(userId);
        return this.userService.addGroupToUser(userId, groupId)
                .replaceWith(RestResponse.created(uriInfo.getAbsolutePath()));
    }

    @GET
    @WithSession
    public Uni<RestResponse<List<User>>> getUsers(){
        return  userService.getUsers().onItem().transform(RestResponse::ok);
    }

    @GET
    @Path("/{userId}")
    public Uni<RestResponse<User>> getUser(Long userId){
        return userService.getUserById(userId).onItem().transform(RestResponse::ok);
    }

    @DELETE
    @Path("/{userId}")
    public Uni<RestResponse<Void>> delete(Long userId){
        return userService.delete(userId).replaceWith(RestResponse.ok());
    }

    @PUT
    public Uni<RestResponse<Void>> update(User user){
        return userService.update(user).replaceWith(RestResponse.ok());
    }
}