package pl.gr.veterinaryapp.service;

import org.springframework.transaction.annotation.Transactional;
import pl.gr.veterinaryapp.model.dto.AnimalRequestDto;
import pl.gr.veterinaryapp.model.dto.AnimalResponseDto;
import pl.gr.veterinaryapp.model.entity.Animal;

import java.util.List;

public interface AnimalService {

    AnimalResponseDto getAnimalById(Long id);
    AnimalResponseDto createAnimal(AnimalRequestDto animalRequestDTO);
    void deleteAnimal(Long id);
    List<AnimalResponseDto> getAllAnimals();
}
