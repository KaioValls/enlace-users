package br.com.enlace.user.service.http;

import br.com.enlace.user.domain.http.GroupDTO;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/group")
@RegisterRestClient(configKey = "group-api")
public interface GroupHttpService {

    @GET
    @Path("{id}")
    Uni<GroupDTO> getGroupDTOById(Long id);
}
