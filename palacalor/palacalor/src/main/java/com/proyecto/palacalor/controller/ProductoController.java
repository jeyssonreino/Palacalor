package com.proyecto.palacalor.controller;


import com.proyecto.palacalor.dto.ProductoDto;
import com.proyecto.palacalor.repository.ProductoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping("/obtenerproductos")
    public List<ProductoDto> obtenerProductos() {
        return this.productoRepository.findAll();
    }

    @PostMapping("/guardarproductos")
    public ProductoDto guardarProductos(@RequestBody ProductoDto producto) {
        return productoRepository.save(producto);
    }

    @GetMapping("/obtenerproducto/{id}")
    public ResponseEntity<ProductoDto> obtenerProductoPorId(@PathVariable int id) {
        Optional<ProductoDto> productoDtoOptional = productoRepository.findById(id);

        if (productoDtoOptional.isPresent()) {
            ProductoDto productoEncontrado = productoDtoOptional.get();
            return ResponseEntity.ok(productoEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminarproducto/{id}")
    public ResponseEntity<ProductoDto> eliminarProductoPorId(@PathVariable int id) {
        Optional<ProductoDto> productoDtoOptional = productoRepository.findById(id);

        if (productoDtoOptional.isPresent()) {
            productoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/editarproducto/{id}")
    public ResponseEntity<ProductoDto> editarProductoPorId(@PathVariable int id, @RequestBody ProductoDto producto) {
        Optional<ProductoDto> productoDtoOptional = productoRepository.findById(id);

        if (productoDtoOptional.isPresent()) {
            ProductoDto productoEncontrado = productoDtoOptional.get();
            productoEncontrado.setNombre(producto.getNombre());
            productoEncontrado.setPrecio(producto.getPrecio());
            ProductoDto productoActualizado = productoRepository.save(productoEncontrado);
            return ResponseEntity.ok(productoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
