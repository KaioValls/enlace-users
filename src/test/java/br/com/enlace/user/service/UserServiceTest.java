package br.com.enlace.user.service;

import br.com.enlace.user.domain.User;
import br.com.enlace.user.domain.http.GroupDTO;
import br.com.enlace.user.repository.UserRepository;
import br.com.enlace.user.service.http.GroupHttpService;
import br.com.enlace.user.utils.UserFixture;
import br.com.enlace.user.validations.http.exceptions.GroupDoesNotExistException;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import io.vertx.core.Vertx;
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
        Mockito.when(groupHttpService.getGroupDTOById(123L))
                .thenReturn(Uni.createFrom().nullItem());

        Mockito.when(userRepository.findById(1L))
                .thenReturn(UserFixture.createUser());

        var latch = new java.util.concurrent.CountDownLatch(1);

        userService.addGroupToUser(1L, 123L)
                .subscribe().with(
                        success -> {
                            Assertions.fail("Era esperada uma exceção");
                            latch.countDown();
                        },
                        failure -> {
                            Assertions.assertTrue(failure instanceof GroupDoesNotExistException);
                            Mockito.verify(userRepository, Mockito.never()).persist((User) Mockito.any());
                            latch.countDown();
                        }
                );

        // Espera o resultado por até 5 segundos
        try {
            if (!latch.await(5, java.util.concurrent.TimeUnit.SECONDS)) {
                Assertions.fail("O teste expirou antes de terminar");
            }
        } catch (InterruptedException e) {
            Assertions.fail("O teste foi interrompido");
        }
    }


    @Test
    public void shouldCreateWhenUserHasAllRequiredFieldsCorrect(){
        GroupDTO groupDTO = UserFixture.createGroupDTO().await().indefinitely();
        User user = UserFixture.createUser().await().indefinitely();

        Mockito.when(groupHttpService.getGroupDTOById(123L))
                .thenReturn(Uni.createFrom().item(groupDTO));

        Mockito.when(userRepository.findById(1L))
                .thenReturn(Uni.createFrom().item(user));


        Vertx.vertx().runOnContext(r ->{
            userService.addGroupToUser(1L,123L).await().indefinitely();

            Mockito.verify(userRepository).persist(user);
        });
    }
}
