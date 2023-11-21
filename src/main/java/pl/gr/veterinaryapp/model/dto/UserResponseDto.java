package pl.gr.veterinaryapp.model.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private Long role;
}
