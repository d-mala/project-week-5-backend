package d_mala.project_week_5_backend.repositories;

import d_mala.project_week_5_backend.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    Optional<Prenotazione> findByUtenteIdAndData(Long utenteId, LocalDate data);

    Optional<Prenotazione> findByPostazioneIdAndData(Long postazioneId, LocalDate data);

}
