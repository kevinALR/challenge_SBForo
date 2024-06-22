package com.foro.challengeForo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/topicos")
@Validated
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<Topico> registrarTopico(@Valid @RequestBody TopicoRequest topicoRequest) {
        Topico topico = topicoService.registrarTopico(topicoRequest);
        System.out.println(topicoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(topico);
    }

    @GetMapping
    public ResponseEntity<List<TopicoResponse>> listarTopicos() {
        List<TopicoResponse> topicos = topicoService.obtenerTodosLosTopicos();
        return ResponseEntity.ok(topicos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponse> obtenerTopicoPorId(@PathVariable Long id) {
        TopicoResponse topicoResponse = topicoService.obtenerTopicoPorId(id);
        return ResponseEntity.ok(topicoResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Topico> actualizarTopico(@PathVariable Long id, @Valid @RequestBody TopicoRequest topicoRequest) {
        Topico topico = topicoService.actualizarTopico(id, topicoRequest);
        return ResponseEntity.ok(topico);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}