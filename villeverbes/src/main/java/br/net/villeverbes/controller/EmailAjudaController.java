package br.net.villeverbes.controller;

import br.net.villeverbes.dto.EmailAjudaDTO;
import br.net.villeverbes.service.EmailAjudaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ajuda")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class EmailAjudaController {

    private final EmailAjudaService emailAjudaService;

    public EmailAjudaController(EmailAjudaService emailAjudaService) {
        this.emailAjudaService = emailAjudaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmailAjudaDTO salvarEmailAjuda(@RequestBody EmailAjudaDTO dto) {
        return emailAjudaService.enviarEGravar(dto);
    }

    @GetMapping
    public List<EmailAjudaDTO> listarTodos() {
        return emailAjudaService.listarTodos();
    }

    // ✅ Novo endpoint: responder um e-mail
    @PutMapping("/{id}/resposta")
    public EmailAjudaDTO responder(@PathVariable Long id, @RequestBody String resposta) {
        return emailAjudaService.responder(id, resposta);
    }

    @GetMapping("/tem-nova-mensagem")
    public boolean temNovaMensagem() {
    return emailAjudaService.temMensagensNaoRespondidas();
}
@GetMapping("/quantidade-nao-respondidas")
    public Long contarNaoRespondidas() {
        return emailAjudaService.contarNaoRespondidas();
    }

}
