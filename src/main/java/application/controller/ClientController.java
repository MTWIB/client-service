package application.controller;

import application.model.dto.AddClientDto;
import application.model.dto.GetAndUpdateClientRequestDto;
import application.model.dto.GetAndUpdateClientResponseDto;
import application.service.ClientService;
import application.validation.Validator;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("clients")
public class ClientController {
    @Inject
    private Validator validator;
    @Inject
    private ClientService clientService;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(AddClientDto addClientDto) {
        if (!validator.validate(addClientDto)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Long id = clientService.add(addClientDto);
        String message = "{\"Client ID\": \"" + id + "\"}";
        return Response.status(Response.Status.OK).entity(message).build();
    }

    @PATCH
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(GetAndUpdateClientRequestDto getAndUpdateClientRequestDto) {
        if (!validator.validate(getAndUpdateClientRequestDto)
                || clientService.getById(getAndUpdateClientRequestDto.getClientId()).isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Object response = clientService.update(getAndUpdateClientRequestDto);
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") long id) {
        Optional<GetAndUpdateClientResponseDto> getAndUpdateClientResponseDto
                = clientService.getById(id);
        if (getAndUpdateClientResponseDto.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK)
                .entity(getAndUpdateClientResponseDto.get()).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) {
        Long deletedClientId = clientService.delete(id);
        if (deletedClientId == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).build();
    }
}
