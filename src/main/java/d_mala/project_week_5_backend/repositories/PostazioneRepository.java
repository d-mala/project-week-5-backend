package d_mala.project_week_5_backend.repositories;

import d_mala.project_week_5_backend.entities.Postazione;
import d_mala.project_week_5_backend.entities.TipoPostazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostazioneRepository extends JpaRepository<Postazione, Long> {
    List<Postazione> findByTipoAndEdificio_Citta(TipoPostazione tipo, String citta);

}
