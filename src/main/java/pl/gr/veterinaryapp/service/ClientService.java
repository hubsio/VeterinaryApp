package pl.gr.veterinaryapp.service;

import pl.gr.veterinaryapp.model.dto.ClientRequestDto;
import pl.gr.veterinaryapp.model.dto.ClientResponseDto;
import pl.gr.veterinaryapp.model.entity.Client;

import java.util.List;

public interface ClientService {

    ClientResponseDto getClientById(Long id);
    ClientResponseDto createClient(ClientRequestDto clientRequestDTO);
    void deleteClient(Long id);
    List<ClientResponseDto> getAllClients();
}
