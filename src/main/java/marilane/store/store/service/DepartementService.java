package marilane.store.store.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import marilane.store.store.model.Departement;
import marilane.store.store.repositories.DepartementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepartementService {

    private final DepartementRepository departementRepository;

    // Ajouter un nouveau département en BD
    public Departement addDepartement(Departement departement) {
        return departementRepository.save(departement);
    }

    // Récupérer tous les départements
    public List<Departement> allDepartements() {
        return departementRepository.findAll();
    }

    // Récupérer un département par son ID
    public Optional<Departement> getDepartementById(UUID id) {
        return departementRepository.findById(id);
    }

    // Modifier les infos d’un département existant
    public Departement updateDepartement(UUID id, Departement updatedDepartement) {
        return departementRepository.findById(id).map(
                existingDepartement -> {
                    existingDepartement.setLibelleDepartement(updatedDepartement.getLibelleDepartement());
                    return departementRepository.save(existingDepartement);
                }
        ).orElseThrow(() -> new RuntimeException("Département non trouvé !"));
    }

    // Supprimer un département par ID
    public void deleteDepartement(UUID id) {
        if (!departementRepository.existsById(id)) {
            throw new EntityNotFoundException("Le département avec id " + id + " n'existe pas !");
        }
        departementRepository.deleteById(id);
    }
}

