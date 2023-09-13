package com.proyecto.palacalor.repository;

import com.proyecto.palacalor.dto.PedidoDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<PedidoDto,Integer> {

    List<PedidoDto> findAll();

    PedidoDto save(PedidoDto pedido);

    Optional<PedidoDto> findById(int id);




}
