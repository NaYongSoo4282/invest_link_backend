package creative.invest_link_backend.dto;

import java.time.LocalDateTime;

//to Front with AiNarrative
public record AiNarrativeResponseDto(
        String narrativeText,
        LocalDateTime updatedAt
) {}
