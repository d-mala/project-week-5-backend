package d_mala.project_week_5_backend.services;

import d_mala.project_week_5_backend.entities.Utente;
import d_mala.project_week_5_backend.exceptions.ResourceNotFoundException;
import d_mala.project_week_5_backend.repositories.UtenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public void salvaUtenti(List<Utente> utenti) {
        utenteRepository.saveAll(utenti);
        log.info("Utenti salvati nel database: {}", utenti);
    }

    public Utente trovaUtente(Long id) {
        return utenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utente non trovato con id " + id));
    }
}
