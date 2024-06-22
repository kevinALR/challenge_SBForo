package com.foro.challengeForo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Transactional
    public Topico registrarTopico(TopicoRequest topicoRequest) {
        // Verificar si ya existe un tópico con el mismo título
        if (topicoRepository.findByTitulo(topicoRequest.getTitulo()).isPresent()) {
            throw new RuntimeException("Ya existe un tópico con el mismo título");
        }
        System.out.println(topicoRequest.getMensaje());
        // Crear una nueva instancia de Topico
        Topico topico = new Topico();
        topico.setTitulo(topicoRequest.getTitulo());
        topico.setMensaje(topicoRequest.getMensaje());
        topico.setFechaCreacion(LocalDateTime.now());
        topico.setAutor_id(topicoRequest.getAutorId());

        // Guardar el tópico en la base de datos
        return topicoRepository.save(topico);
    }
    @Transactional(readOnly = true)
    public List<TopicoResponse> obtenerTodosLosTopicos() {
        List<Topico> topicos = topicoRepository.findAll();
        return topicos.stream()
                .map(topico -> new TopicoResponse(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaCreacion(),
                        topico.getAutor_id()))
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public TopicoResponse obtenerTopicoPorId(Long id) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();
            return new TopicoResponse(
                    topico.getId(),
                    topico.getTitulo(),
                    topico.getMensaje(),
                    topico.getFechaCreacion(),
                    topico.getAutor_id());
        } else {
            throw new RuntimeException("Tópico no encontrado");
        }
    }
    @Transactional
    public Topico actualizarTopico(Long id, TopicoRequest topicoRequest) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (!optionalTopico.isPresent()) {
            throw new RuntimeException("Tópico no encontrado");
        }

        Topico topico = optionalTopico.get();
        if (topicoRepository.findByTitulo(topicoRequest.getTitulo()).isPresent() &&
                !topicoRepository.findByTitulo(topicoRequest.getTitulo()).get().getId().equals(id)) {
            throw new RuntimeException("Ya existe un tópico con el mismo título");
        }

        topico.setTitulo(topicoRequest.getTitulo());
        topico.setMensaje(topicoRequest.getMensaje());
        topico.setAutor_id(topicoRequest.getAutorId());
        topico.setFechaCreacion(LocalDateTime.now());

        return topicoRepository.save(topico);
    }
    @Transactional
    public void eliminarTopico(Long id) {
        if (!topicoRepository.findById(id).isPresent()) {
            throw new RuntimeException("Tópico no encontrado");
        }
        topicoRepository.deleteById(id);
    }
}