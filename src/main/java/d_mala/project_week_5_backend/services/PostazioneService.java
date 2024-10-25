package d_mala.project_week_5_backend.services;

import d_mala.project_week_5_backend.entities.Postazione;
import d_mala.project_week_5_backend.entities.TipoPostazione;
import d_mala.project_week_5_backend.exceptions.ResourceNotFoundException;
import d_mala.project_week_5_backend.repositories.PostazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostazioneService {

    @Autowired
    private PostazioneRepository postazioneRepository;

    public void salvaPostazioni(List<Postazione> postazioni) {
        postazioneRepository.saveAll(postazioni);
    }

    public List<Postazione> trovaPostazioniPerTipoECitta(TipoPostazione tipo, String citta) {
        return postazioneRepository.findByTipoAndEdificio_Citta(tipo, citta);
    }

    public Postazione trovaPostazione(Long id) {
        return postazioneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Postazione non trovata con id " + id));
    }
}
