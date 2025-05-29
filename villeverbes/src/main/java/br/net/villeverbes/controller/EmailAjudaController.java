package br.net.villeverbes.controller;

import br.net.villeverbes.dto.EmailAjudaDTO;
import br.net.villeverbes.service.EmailAjudaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ajuda-emails")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class EmailAjudaController {

    private final EmailAjudaService emailAjudaService;

    public EmailAjudaController(EmailAjudaService emailAjudaService) {
        this.emailAjudaService = emailAjudaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmailAjudaDTO salvarEmailAjuda(@RequestBody EmailAjudaDTO emailAjudaDTO) {
        System.out.println("Recebido no controller: remetente=" + emailAjudaDTO.getRemetente()
                + ", mensagem=" + emailAjudaDTO.getMensagem());

        if (emailAjudaDTO.getRemetente() == null || emailAjudaDTO.getMensagem() == null) {
            throw new IllegalArgumentException("Remetente e mensagem não podem ser nulos.");
        }

        // SALVAR no banco de dados usando o serviço
        return emailAjudaService.salvar(emailAjudaDTO);
    }

    @GetMapping
    public List<EmailAjudaDTO> listarAjuda() {
        return emailAjudaService.listarTodos();
    }
}
