package com.foro.challengeForo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TopicoRequest {

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotBlank(message = "El mensaje es obligatorio")
    @Size(min = 5, message = "El mensaje debe tener al menos 5 caracteres")
    private String mensaje;

    @NotNull(message = "El autorId es obligatorio")
    private Long autorId; // Agregamos el campo autorId

}
