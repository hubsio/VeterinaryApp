package pl.gr.veterinaryapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gr.veterinaryapp.exception.IncorrectDataException;
import pl.gr.veterinaryapp.exception.ResourceNotFoundException;
import pl.gr.veterinaryapp.mapper.VetMapper;
import pl.gr.veterinaryapp.model.dto.VetRequestDto;
import pl.gr.veterinaryapp.model.dto.VetResponseDto;
import pl.gr.veterinaryapp.model.entity.Vet;
import pl.gr.veterinaryapp.repository.VetRepository;
import pl.gr.veterinaryapp.service.VetService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class VetServiceImpl implements VetService {

    private final VetRepository vetRepository;
    private final VetMapper mapper;

    @Override
    public VetResponseDto getVetById(Long id) {
        Vet vet = vetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wrong id."));
        return mapper.mapToDto(vet);
    }

    @Override
    public List<VetResponseDto> getAllVets() {
        return vetRepository.findAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public VetResponseDto createVet(VetRequestDto vetRequestDTO) {
        if (vetRequestDTO.getSurname() == null || vetRequestDTO.getName() == null) {
            throw new IncorrectDataException("Name and Surname cannot be null.");
        }
        Vet vet = mapper.map(vetRequestDTO);
        vet = vetRepository.save(vet);
        return mapper.mapToDto(vet);
    }

    @Transactional
    @Override
    public void deleteVet(Long id) {
        Vet result = vetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wrong id."));
        vetRepository.delete(result);
    }
}
