package pl.gr.veterinaryapp.model.dto;

import java.time.OffsetTime;

public class VetResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String photoUrl;
    private OffsetTime workStartTime;
    private OffsetTime workEndTime;
}
