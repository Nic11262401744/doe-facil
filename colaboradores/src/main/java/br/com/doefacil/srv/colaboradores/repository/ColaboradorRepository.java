package br.com.doefacil.srv.colaboradores.repository;

import br.com.doefacil.srv.colaboradores.repository.entity.ColaboradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface ColaboradorRepository extends JpaRepository<ColaboradorEntity, Long> {

    @Query(value = "SELECT d.* FROM colaboradores d WHERE d.ong_id=?1",
            nativeQuery = true)
    ArrayList<ColaboradorEntity>  findByIdOng(Long id);

}
