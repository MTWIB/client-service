package application.controller;

import application.dao.impl.ClientDaoImpl;
import application.dao.impl.PersonalInfoDaoImpl;
import application.dao.impl.PhoneNumberDaoImpl;
import application.model.dto.AddClientDto;
import application.model.dto.GetAndUpdateClientRequestDto;
import application.model.dto.GetAndUpdateClientResponseDto;
import application.model.mapper.ResponseDtoMapper;
import application.service.ClientService;
import application.service.impl.ClientServiceImpl;
import application.validation.Validator;
import java.util.Optional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("clients")
public class ClientController {
    private final Validator validator = new Validator();
    private final ClientService clientService = new ClientServiceImpl(new ClientDaoImpl(),
            new PersonalInfoDaoImpl(), new PhoneNumberDaoImpl(), new ResponseDtoMapper());

    @GET
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(@QueryParam("fullName") String fullName,
             @QueryParam("mainPhoneNumber") String mainPhoneNumber) {
        AddClientDto addClientDto = new AddClientDto(fullName, mainPhoneNumber);
        if (!validator.validate(addClientDto)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Long id = clientService.add(addClientDto);
        String message = "{\"Client ID\": \"" + id + "\"}";
        return Response.status(Response.Status.OK).entity(message).build();
    }

    @GET
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@QueryParam("clientId") Long clientId,
                           @QueryParam("fullName") String fullName,
                           @QueryParam("passport") String passport,
                           @QueryParam("dateOfBirth") String dateOfBirth,
                           @QueryParam("additionalPhoneNumber") String additionalPhoneNumber) {
        GetAndUpdateClientRequestDto getAndUpdateClientRequestDto
                = new GetAndUpdateClientRequestDto(clientId,
                fullName, passport, dateOfBirth, additionalPhoneNumber);
        if (!validator.validate(getAndUpdateClientRequestDto)
                || clientService.getById(clientId).isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Object response = clientService.update(getAndUpdateClientRequestDto);
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @GET
    @Path("get/{id}")
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

    @GET
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) {
        Long deletedClientId = clientService.delete(id);
        if (deletedClientId == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).build();
    }
}
