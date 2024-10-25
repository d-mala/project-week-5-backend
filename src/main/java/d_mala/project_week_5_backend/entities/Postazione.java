package d_mala.project_week_5_backend.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "postazioni")
@Getter
@Setter
@NoArgsConstructor
public class Postazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codice;

    @Column
    private String descrizione;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPostazione tipo;

    @Column(name = "max_occupanti", nullable = false)
    private Integer maxOccupanti;

    @ManyToOne
    @JoinColumn(name = "edificio_id", nullable = false)
    private Edificio edificio;

    @OneToMany(mappedBy = "postazione")
    private List<Prenotazione> prenotazioni;

    public Postazione(String codice, String descrizione, TipoPostazione tipo, Integer maxOccupanti, Edificio edificio) {
        this.edificio = edificio;
        this.maxOccupanti = maxOccupanti;
        this.tipo = tipo;
        this.descrizione = descrizione;
        this.codice = codice;
    }

    @Override
    public String toString() {
        return "Postazione{" +
                "id=" + id +
                ", codice='" + codice + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", tipo=" + tipo +
                ", maxOccupanti=" + maxOccupanti +
                ", edificio=" + edificio.getNome() +  // Solo il nome per evitare loop
                '}';
    }
}

