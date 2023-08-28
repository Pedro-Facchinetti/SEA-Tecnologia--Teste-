package com.pedrofacchinetti.TesteBackEnd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;

@Setter
@Getter
@Entity
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID do cliente", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "Nome do cliente", required = true)
    @NotBlank
    @Size(min = 3, max = 100)
    private String nome;

    @ApiModelProperty(value = "CPF do cliente, não pode ser um CPF falso.", required = true)
    @NotBlank
    @Column(unique = true)
    @CPF
    private String cpf;

    @ApiModelProperty(value = "Endereço do cliente", required = true)
    @Embedded
    private Endereco endereco;

    @ApiModelProperty(value = "Todos os números de telefone que um cliente possui", required = true)
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Telefone> telefones;

    @ApiModelProperty(value = "Todos os emails que um cliente possui", required = true)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
    @NotEmpty(message = "Lista de e-mails não pode ser vazia")
    private List<Email> emails;

}