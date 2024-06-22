package com.foro.challengeForo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class TopicoResponse {
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Long autorId;

    // Constructor
    public TopicoResponse(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, Long autorId) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.autorId = autorId;
    }

}
