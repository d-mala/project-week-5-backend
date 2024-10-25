package d_mala.project_week_5_backend.repositories;

import d_mala.project_week_5_backend.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
}
