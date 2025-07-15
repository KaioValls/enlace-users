package br.com.enlace.user.utils;

import br.com.enlace.user.domain.User;
import br.com.enlace.user.domain.http.GroupDTO;

public class UserFixture {

    public static User createUser(){
        return new User();
    }

    public static GroupDTO createGroupDTO(){
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(123L);
        return groupDTO;
    }
}
