package br.net.villeverbes.service;

import br.net.villeverbes.dto.EmailAjudaDTO;
import br.net.villeverbes.entity.EmailAjudaEntity;
import br.net.villeverbes.repository.EmailAjudaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailAjudaService {

    private final EmailAjudaRepository repository;

    public EmailAjudaService(EmailAjudaRepository repository) {
        this.repository = repository;
    }

    public EmailAjudaDTO salvar(EmailAjudaDTO dto) {
        System.out.println("== Recebendo novo e-mail de ajuda ==");
        System.out.println("Remetente: " + dto.getRemetente());
        System.out.println("Mensagem: " + dto.getMensagem());

        EmailAjudaEntity entity = new EmailAjudaEntity();
        entity.setEmailRemetente(dto.getRemetente());
        entity.setMensagem(dto.getMensagem());
        entity.setDataEnvio(LocalDateTime.now());

        EmailAjudaEntity salvo = repository.save(entity);

        EmailAjudaDTO resposta = new EmailAjudaDTO();
        resposta.setRemetente(salvo.getEmailRemetente());
        resposta.setMensagem(salvo.getMensagem());

        return resposta;
    }

    public List<EmailAjudaDTO> listarTodos() {
        return repository.findAll().stream()
                .map(entity -> new EmailAjudaDTO(entity.getEmailRemetente(), entity.getMensagem()))
                .collect(Collectors.toList());
    }
}
