package com.pedrofacchinetti.TesteBackEnd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pedrofacchinetti.TesteBackEnd.dto.ClienteDTO;
import com.pedrofacchinetti.TesteBackEnd.model.Cliente;
import com.pedrofacchinetti.TesteBackEnd.service.ClienteService;
import com.pedrofacchinetti.TesteBackEnd.service.DtoMapper;
import com.pedrofacchinetti.TesteBackEnd.util.JwtProvider;

import java.util.List;
import java.util.stream.Collectors;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Clientes")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private DtoMapper dtoMapper;

    @Autowired
    private JwtProvider jwtProvider;  

    @ApiOperation(value = "Obtém todos os clientes")
    @GetMapping
    public List<ClienteDTO> getAllClientes() {
        List<Cliente> clientes = clienteService.findAll();
        return clientes.stream()
                .map(dtoMapper::toClienteDTO)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Obtém um cliente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(
        @ApiParam(value = "ID do cliente", required = true) @PathVariable Long id) {
        Cliente cliente = clienteService.getClienteById(id);
        return ResponseEntity.ok(dtoMapper.toClienteDTO(cliente));
    }    

    @PostMapping
    public ResponseEntity<?> createCliente(@RequestBody Cliente cliente) {
        Cliente savedCliente = clienteService.createCliente(cliente);
        return new ResponseEntity<>(savedCliente, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {
        
        // Extrair o token do cabeçalho de autorização
        String token = authHeader.substring(7);
        
        // Extrair informações do token
        String role = jwtProvider.extractRole(token);
        
        if ("ADMIN".equals(role)) {
            clienteService.delete(id);
            return ResponseEntity.ok("Cliente deletado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Ação não permitida!");
        }
    }
}