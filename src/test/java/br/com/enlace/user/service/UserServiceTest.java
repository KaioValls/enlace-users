package br.com.enlace.user.service;

import br.com.enlace.user.domain.User;
import br.com.enlace.user.domain.http.GroupDTO;
import br.com.enlace.user.repository.UserRepository;
import br.com.enlace.user.service.http.GroupHttpService;
import br.com.enlace.user.utils.UserFixture;
import br.com.enlace.user.validations.http.exceptions.GroupDoesNotExistException;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


@QuarkusTest
public class UserServiceTest {

    @InjectMock
    private UserRepository userRepository;

    @InjectMock
    @RestClient
    private GroupHttpService groupHttpService;

    @Inject
    private UserService userService;

    @Test
    public void shouldNotAddGroupWhenGroupDoesNotExist(){
        Mockito.when(groupHttpService.getGroupDTOById(123L)).thenReturn(null);
        User user = UserFixture.createUser();
        Mockito.when(userRepository.findById(1L)).thenReturn(user);

        Assertions.assertThrows(GroupDoesNotExistException.class, ()-> userService.addGroupToUser(1L,123L));

        Mockito.verify(userRepository, Mockito.never()).persist(user);
    }

    @Test
    public void shouldCreateWhenUserHasAllRequiredFieldsCorrect(){
        GroupDTO groupDTO = UserFixture.createGroupDTO();
        Mockito.when(groupHttpService.getGroupDTOById(123L)).thenReturn(groupDTO);
        User user = UserFixture.createUser();
        Mockito.when(userRepository.findById(1L)).thenReturn(user);

        userService.addGroupToUser(1L,123L);

        Mockito.verify(userRepository).persist(user);
        Assertions.assertEquals(user.getUserGroupsRoles().stream().findFirst().get().getGroupId(), groupDTO.getId());
    }
}
