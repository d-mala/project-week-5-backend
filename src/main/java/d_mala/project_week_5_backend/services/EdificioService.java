package d_mala.project_week_5_backend.services;

import d_mala.project_week_5_backend.entities.Edificio;
import d_mala.project_week_5_backend.exceptions.ResourceNotFoundException;
import d_mala.project_week_5_backend.repositories.EdificioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdificioService {
    @Autowired
    private EdificioRepository edificioRepository;

    public void salvaEdifici(List<Edificio> edifici) {
        edificioRepository.saveAll(edifici);
    }

    public List<Edificio> trovaTuttiEdifici() {
        return edificioRepository.findAll();
    }

    public Edificio trovaEdificio(Long id) {
        return edificioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Edificio non trovato con id " + id));
    }
}