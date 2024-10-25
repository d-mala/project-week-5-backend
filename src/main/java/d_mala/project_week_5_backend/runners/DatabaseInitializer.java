package d_mala.project_week_5_backend.runners;

import com.github.javafaker.Faker;
import d_mala.project_week_5_backend.entities.Edificio;
import d_mala.project_week_5_backend.entities.Postazione;
import d_mala.project_week_5_backend.entities.Utente;
import d_mala.project_week_5_backend.services.EdificioService;
import d_mala.project_week_5_backend.services.PostazioneService;
import d_mala.project_week_5_backend.services.PrenotazioneService;
import d_mala.project_week_5_backend.services.UtenteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Slf4j
@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private EdificioService edificioService;

    @Autowired
    private PostazioneService postazioneService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(new Locale("it"));

        // 1. Creazione Edifici
        List<Edificio> edifici = new ArrayList<>();
        edifici.add(new Edificio("Edificio A", "Via Roma, 1", "Milano"));
        edifici.add(new Edificio("Edificio B", "Piazza Duomo, 10", "Roma"));
        edificioService.salvaEdifici(edifici);

        // 2. Creazione Postazioni
        List<Postazione> postazioni = new ArrayList<>();
        postazioni.add(new Postazione("P1", "Postazione Open Space", TipoPostazione.OPENSPACE, 5, edifici.get(0)));
        postazioni.add(new Postazione("P2", "Sala Riunioni Grande", TipoPostazione.SALA_RIUNIONI, 10, edifici.get(0)));
        postazioni.add(new Postazione("P3", "Postazione Privata", TipoPostazione.PRIVATO, 1, edifici.get(1)));
        postazioneService.salvaPostazioni(postazioni);

        // 3. Creazione Utenti casuali
        List<Utente> utenti = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            utenti.add(new Utente(faker.name().username(), faker.name().fullName(), faker.internet().emailAddress()));
        }
        utenteService.salvaUtenti(utenti);

        // 4. Creazione Prenotazioni
        try {
            prenotazioneService.creaPrenotazione(2L, 2L, LocalDate.now().plusDays(3));
        } catch (Exception e) {
            log.error("Errore durante la creazione delle prenotazioni: {}", e.getMessage());
        }
    }
}
