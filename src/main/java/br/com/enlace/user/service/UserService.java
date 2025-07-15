package br.com.enlace.user.service;

import br.com.enlace.user.domain.User;
import br.com.enlace.user.domain.UserGroupRoles;
import br.com.enlace.user.domain.UserPreferences;
import br.com.enlace.user.domain.http.GroupDTO;
import br.com.enlace.user.repository.UserRepository;
import br.com.enlace.user.service.http.GroupHttpService;
import br.com.enlace.user.validations.http.exceptions.GroupDoesNotExistException;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserService {

    @RestClient
    private GroupHttpService groupHttpService;

    private final UserRepository userRepository;
    private List<User> userList = new ArrayList<>();


    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void create(User user){
        UserPreferences userPreferences = new UserPreferences();

        if(user.getFirstName() == null){
            Log.info("O usuario com email " + user.getEmail() + " foi cadastrado");
            throw new EntityNotFoundException();
        }
        Log.info("O usuario com email " + user.getEmail() + " foi cadastrado");
        userRepository.persist(user);
        System.out.println("Usuário cadastrado!");
    }

    public List<User> getUsers(){
        return userRepository.findAll()
                .stream()
                .toList();
    }


    public User getUserById(Long userId){
        return userRepository.findById(userId);
    }

    public void delete(Long userId){
        Log.info("O usuario com id " + userId + " foi deletado");
        userRepository.deleteById(userId);
    }

    public void update(User user){
        Log.info("O usuario com email " + user.getEmail() + " foi alterada");
        userRepository.update(
                "firstName = ?1, lastName = ?2, nickName = ?3 where id = ?4",
                user.getFirstName(), user.getLastName(), user.getNickName(), user.getId()
        );
    }


    public void addGroupToUser(Long userId, Long groupId){
        GroupDTO groupDTOById = groupHttpService.getGroupDTOById(groupId);

        if(groupDTOById == null){
            throw new GroupDoesNotExistException();
        }
        User byId = userRepository.findById(userId);

        UserGroupRoles userGroupRoles = new UserGroupRoles();
        userGroupRoles.setGroupId(groupId);
        byId.addUserGroupsRoles(userGroupRoles);
        userRepository.persist(byId);
    }
}
