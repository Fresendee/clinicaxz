package com.clinicaxz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "convenios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Convenio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nome;

    @Column(length = 20)
    private String cnpj;

    @Column(length = 15)
    private String telefone;

    @Column(length = 200)
    private String email;

    @Column(nullable = false)
    private Boolean ativo = true;
}
