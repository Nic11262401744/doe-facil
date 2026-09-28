package br.com.doefacil.srv.colaboradores.repository;

import br.com.doefacil.srv.colaboradores.repository.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {

    Optional<EnderecoEntity> findByCep(String cep);

    Optional<EnderecoEntity> findByRuaAndNumero(String rua, String numero);
}
