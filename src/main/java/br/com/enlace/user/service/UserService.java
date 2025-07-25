package br.com.enlace.user.service;

import br.com.enlace.user.domain.Role;
import br.com.enlace.user.domain.User;
import br.com.enlace.user.domain.UserGroupRoles;
import br.com.enlace.user.domain.UserPreferences;
import br.com.enlace.user.domain.http.GroupDTO;
import br.com.enlace.user.domain.http.GroupStatus;
import br.com.enlace.user.repository.UserRepository;
import br.com.enlace.user.service.http.GroupHttpService;
import br.com.enlace.user.validations.http.exceptions.GroupDoesNotExistException;
import io.micrometer.core.instrument.MeterRegistry;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserService {


    private final MeterRegistry meterRegistry;
    private final UserRepository userRepository;

    @RestClient
    private GroupHttpService groupHttpService;

    private List<User> userList = new ArrayList<>();


    UserService(UserRepository userRepository, MeterRegistry meterRegistry){
        this.userRepository = userRepository;
        this.meterRegistry = meterRegistry;
    }

    @WithTransaction
    public Uni<Void> create(User user){
        UserPreferences userPreferences = new UserPreferences();

        if(user.getFirstName() == null){
            Log.info("É preciso informar um Primeiro Nome");
            throw new EntityNotFoundException();
        }
        meterRegistry.counter("user_added_counter").increment();
        Log.info("O usuario com email " + user.getEmail() + " foi cadastrado");
        System.out.println("Usuário cadastrado!");
        return userRepository.persist(user).replaceWithVoid();
    }

    @WithSession
    public Uni<List<User>> getUsers(){
        return userRepository.listAll();
    }

    @WithSession
    public Uni<User> getUserById(Long userId){
        return userRepository.findById(userId)
                .onItem().ifNull().failWith(()-> new NotFoundException("User not Found"));
    }

    @WithTransaction
    public Uni<Void> delete(Long userId){
        Log.info("O usuario com id " + userId + " foi deletado");
        return userRepository.deleteById(userId).replaceWithVoid();
    }

    @WithTransaction
    public Uni<Void> update(User user){
        Log.info("O usuario com email " + user.getEmail() + " foi alterada");

        return userRepository.update(
                "firstName = ?1, lastName = ?2, nickName = ?3, phone = ?4  where id = ?5",
                user.getFirstName(), user.getLastName(), user.getNickName(), user.getPhone(), user.getId()
        ).replaceWithVoid();
    }

    @WithTransaction
    @CircuitBreaker(
            requestVolumeThreshold = 5,
            failureRatio = 0.5,
            delay = 2000,
            successThreshold = 2
    )
    public Uni<Void> addGroupToUser(Long userId, Long groupId){
        Uni<GroupDTO> groupDTOById = groupHttpService.getGroupDTOById(groupId);

        //Testing integration, suposed to fail with
        return groupDTOById
                .onItem().ifNull().failWith(new GroupDoesNotExistException())
                .onItem().transformToUni(group -> {
                    if(!group.getStatus().equals(GroupStatus.ACTIVE)){
                        throw new EntityNotFoundException();
                    }
                    return  persistGroupToUser(userId, group);
                });
    }

    private Uni<Void> persistGroupToUser(Long userId, GroupDTO groupId) {
        Uni<User> byId = userRepository.findById(userId);

        return byId
                .onItem().ifNull().failWith(NotFoundException::new)
                .onItem().transformToUni(user -> {
                    UserGroupRoles userGroupRoles = new UserGroupRoles();
                    userGroupRoles.setGroupId(groupId.getId());
                    userGroupRoles.setUser(user);
                    userGroupRoles.setRole(Role.MEMBER);
                    user.addUserGroupsRoles(userGroupRoles);

                    return userRepository.persist(user);
                })
                .replaceWithVoid();
    }
}
