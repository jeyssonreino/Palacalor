package com.proyecto.palacalor.repository;

import com.proyecto.palacalor.dto.ProductoDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<ProductoDto,Integer> {

List<ProductoDto> findAll();

ProductoDto save(ProductoDto producto);


Optional<ProductoDto> findById(int id);








}
