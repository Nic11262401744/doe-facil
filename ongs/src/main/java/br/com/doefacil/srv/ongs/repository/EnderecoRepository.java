package br.com.doefacil.srv.ongs.repository;

import br.com.doefacil.srv.ongs.repository.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {

    Optional<EnderecoEntity> findByRuaLike(String rua);

    Optional<EnderecoEntity> findByCep(String cep);

}
