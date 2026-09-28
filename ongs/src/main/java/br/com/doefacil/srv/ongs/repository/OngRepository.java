package br.com.doefacil.srv.ongs.repository;

import br.com.doefacil.srv.ongs.repository.entity.OngsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OngRepository extends JpaRepository<OngsEntity, Long> {
    Optional<OngsEntity> findByCnpj(String cnpj);
}
