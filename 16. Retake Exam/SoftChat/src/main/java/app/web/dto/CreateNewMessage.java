package app.web.dto;

import jakarta.validation.constraints.Size;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateNewMessage {

    @NotBlank(message = "You must select a receiver!")
    private String receiver;

    @NotBlank(message = "")
    @Size(min = 5, max = 15, message = "Subject length must be between 5 and 15 characters!")
    private String subject;

    @NotBlank(message = "")
    @Size(min = 10, max = 100, message = "Content length must be between 10 and 100 characters!")
    private String content;

}
