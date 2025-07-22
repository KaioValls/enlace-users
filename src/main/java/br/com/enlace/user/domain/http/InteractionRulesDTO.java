package br.com.enlace.user.domain.http;

import br.com.enlace.user.domain.Role;

import java.util.List;

public class InteractionRulesDTO {

    private VisibilityLevel visibility;
    private List<Role> whoCanInvite;
    private List<Role> whoCanPost;
    private List<Role> whoCanModerate;
    private Boolean requireApprovalToJoin;
    private int maxMembers;
    private Boolean allowMemberInvite;

}
