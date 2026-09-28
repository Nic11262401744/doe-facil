package br.com.doefacil.doacao.repository;

import br.com.doefacil.doacao.repository.entity.DoacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface DoacaoRepository extends JpaRepository<DoacaoEntity, Long> {

    java.util.Optional<DoacaoEntity> findByIdAndOng_Id(Long id, Long ongId);

    @Query(value = "SELECT d.* FROM doacoes d WHERE d.ong_id=?1",
            nativeQuery = true)
    ArrayList<DoacaoEntity> seachDoacoesByOngId(Long ongId);

}
