package br.com.doefacil.srv.colaboradores.repository;

import br.com.doefacil.srv.colaboradores.repository.entity.ColaboradorEntity;
import br.com.doefacil.srv.colaboradores.repository.entity.OngEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OngRepository extends JpaRepository<OngEntity, Long> {
}
