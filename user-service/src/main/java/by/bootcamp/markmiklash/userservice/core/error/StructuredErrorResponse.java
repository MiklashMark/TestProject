package by.bootcamp.markmiklash.userservice.core.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StructuredErrorResponse {
    private String logRef;
    private List<ErrorDetail> errors = new ArrayList<>();


    public void addError(ErrorDetail error) {
        errors.add(error);
    }
}
