package br.com.enlace.user.utils;

import br.com.enlace.user.domain.User;
import br.com.enlace.user.domain.http.GroupDTO;
import io.smallrye.mutiny.Uni;

public class UserFixture {

    public static Uni<User> createUser(){
        return Uni.createFrom().item(new User());
    }

    public static Uni<GroupDTO> createGroupDTO(){
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(123L);
        return Uni.createFrom().item(groupDTO);
    }
}
