package by.bootcamp.markmiklash.userservice.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationDTO {
    private String fieldValue;
    private String fieldName;
    private String regexPattern;
    private int maxLength;
}