package marilane.store.store.repositories;

import marilane.store.store.model.Poste;
import marilane.store.store.model.TypePoste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/** JpaRepository<Poste, UUID> Poste = Nom entite
 et UUID = type de la clef primaire de l'entité Poste */

@Repository
public interface PosteRepository extends JpaRepository<Poste, UUID> {
    // Pour eviter les doublons sur les postes en BD
    boolean existsDistinctByLibellePoste(TypePoste libellePoste);
}
