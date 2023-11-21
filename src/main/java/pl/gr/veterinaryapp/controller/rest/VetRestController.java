package pl.gr.veterinaryapp.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gr.veterinaryapp.model.dto.VetRequestDto;
import pl.gr.veterinaryapp.model.dto.VetResponseDto;
import pl.gr.veterinaryapp.model.entity.Vet;
import pl.gr.veterinaryapp.service.VetService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/vets")
public class VetRestController {

    private final VetService vetService;

    @GetMapping("/{id}")
    public VetResponseDto getVet(@PathVariable Long id) {
        return vetService.getVetById(id);
    }

    @PostMapping
    public VetResponseDto addVet(@RequestBody VetRequestDto vetRequestDTO) {
        return vetService.createVet(vetRequestDTO);
    }

    @GetMapping
    public List<VetResponseDto> getAllVets() {
        return vetService.getAllVets();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vetService.deleteVet(id);
    }
}
