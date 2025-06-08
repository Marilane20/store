package marilane.store.store.service;

import marilane.store.store.dto.EmployeDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import marilane.store.store.model.Poste;
import marilane.store.store.repositories.PosteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PosteService {

    private final PosteRepository posteRepository;

    // Ajouter un nouveau poste en BD
    public Poste addPoste(Poste poste) {
        // Vérifie si le libellé existe déjà
        if (posteRepository.existsDistinctByLibellePoste(poste.getLibellePoste())) {
            throw new IllegalArgumentException("Ce libellé existe déjà.");
        }
        return posteRepository.save(poste);
    }

    // Récupérer la liste de tous les postes dans la BD
    public List<Poste> getAllPostes() {
        return posteRepository.findAll();
    }

    // Récupérer un poste par son ID
    public Optional<Poste> getPosteById(UUID id) {
        return posteRepository.findById(id);
    }

    // Modifier un poste existant en BD
    public Poste updatePoste(UUID id, Poste updatedPoste) {
        return posteRepository.findById(id).map(
                existingPoste -> {
                    existingPoste.setLibellePoste(updatedPoste.getLibellePoste());
                    existingPoste.setSalaireMax(updatedPoste.getSalaireMax());
                    existingPoste.setSalaireMin(updatedPoste.getSalaireMin());
                    return posteRepository.save(existingPoste);
                }
        ).orElseThrow(() -> new RuntimeException("Poste non trouvé !"));
    }

    // Supprimer un poste par ID
    public void deletePoste(UUID id) {
        if (!posteRepository.existsById(id)) {
            throw new EntityNotFoundException("Le poste avec id " + id + " n'existe pas !");
        }
        posteRepository.deleteById(id);
    }
}

