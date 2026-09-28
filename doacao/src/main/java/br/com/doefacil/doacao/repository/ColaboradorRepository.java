package br.com.doefacil.doacao.repository;

import br.com.doefacil.doacao.repository.entity.ColaboradorEntity;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ColaboradorRepository extends JpaRepository<ColaboradorEntity, Long> {
    Optional<ColaboradorEntity> findByNome(@NotBlank String nome);
    Optional<ColaboradorEntity> findByNomeAndOng_Id(String nome, Long ongId);
}
