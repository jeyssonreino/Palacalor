package com.proyecto.palacalor.controller;


import com.proyecto.palacalor.dto.PedidoDto;
import com.proyecto.palacalor.repository.PedidoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private PedidoRepository pedidoRepository;

    PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping("/obtenerpedidos")
    public List<PedidoDto> obtenerPedidos() {
        return this.pedidoRepository.findAll();
    }

    @PostMapping("/guardarpedido")
    public PedidoDto guardarPedido(@RequestBody PedidoDto pedidoDto) {
        return this.pedidoRepository.save(pedidoDto);
    }

    @GetMapping("/obtenerpedido/{id}")
    public ResponseEntity<PedidoDto> obtenerPedidoPorIde(@PathVariable int id) {
        Optional<PedidoDto> pedidoDtoOptional = this.pedidoRepository.findById(id);

        if (pedidoDtoOptional.isPresent()) {
            PedidoDto pedidoEncontrado = pedidoDtoOptional.get();
            return ResponseEntity.ok(pedidoEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminarpedido/{id}")
    public ResponseEntity<PedidoDto> eliminarPedidoPorIde(@PathVariable int id) {
        Optional<PedidoDto> pedidoDtoOptional = this.pedidoRepository.findById(id);

        if (pedidoDtoOptional.isPresent()) {
            pedidoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/editarpedido/{id}")
    public ResponseEntity<PedidoDto> editarPedidoPorIde(@PathVariable int id, @RequestBody PedidoDto pedidoDto) {
        Optional<PedidoDto> pedidoDtoOptional = this.pedidoRepository.findById(id);

        if (pedidoDtoOptional.isPresent()) {
            PedidoDto pedidoDtoEncotrado = pedidoDtoOptional.get();
            pedidoDtoEncotrado.setCantidad(pedidoDto.getCantidad());
            pedidoDtoEncotrado.setCedula(pedidoDto.getCedula());
            pedidoDtoEncotrado.setCelular(pedidoDto.getCelular());
            pedidoDtoEncotrado.setDireccion(pedidoDto.getDireccion());
            pedidoDtoEncotrado.setId_producto(pedidoDto.getId_producto());
            pedidoDtoEncotrado.setNombre(pedidoDto.getNombre());
            PedidoDto pedidoDtoActualizado = pedidoRepository.save(pedidoDtoEncotrado);
            return ResponseEntity.ok(pedidoDtoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}
