package d_mala.project_week_5_backend.services;

import d_mala.project_week_5_backend.entities.Postazione;
import d_mala.project_week_5_backend.entities.Prenotazione;
import d_mala.project_week_5_backend.entities.Utente;
import d_mala.project_week_5_backend.exceptions.PrenotazioneNonValidaException;
import d_mala.project_week_5_backend.exceptions.ResourceNotFoundException;
import d_mala.project_week_5_backend.repositories.PostazioneRepository;
import d_mala.project_week_5_backend.repositories.PrenotazioneRepository;
import d_mala.project_week_5_backend.repositories.UtenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private PostazioneRepository postazioneRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Transactional
    public Prenotazione creaPrenotazione(Long utenteId, Long postazioneId, LocalDate data) {
        Optional<Prenotazione> esistente = prenotazioneRepository
                .findByUtenteIdAndData(utenteId, data);

        if (esistente.isPresent()) {
            throw new PrenotazioneNonValidaException("L'utente ha già una prenotazione per questa data.");
        }

        Optional<Prenotazione> prenotazioneEsistente = prenotazioneRepository
                .findByPostazioneIdAndData(postazioneId, data);

        if (prenotazioneEsistente.isPresent()) {
            throw new PrenotazioneNonValidaException("La postazione è già prenotata per la data selezionata.");
        }

        Utente utente = utenteRepository.findById(utenteId)
                .orElseThrow(() -> new ResourceNotFoundException("Utente non trovato con id " + utenteId));
        Postazione postazione = postazioneRepository.findById(postazioneId)
                .orElseThrow(() -> new ResourceNotFoundException("Postazione non trovata con id " + postazioneId));

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setData(data);
        prenotazione.setUtente(utente);
        prenotazione.setPostazione(postazione);
        log.info("Prenotazione creata: {}", prenotazione);
        return prenotazioneRepository.save(prenotazione);
    }

    public void salvaPrenotazioni(List<Prenotazione> prenotazioni) {
        prenotazioneRepository.saveAll(prenotazioni);
    }
}
