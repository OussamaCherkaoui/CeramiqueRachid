package com.CeramiqueRachid.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nomEtPrenom;
    @Column
    private Long telephone;
    @Column
    private String email;
    @Column
    private String message;
    @Column
    private LocalDate dateEnvoi;
    @Column
    private LocalDate dateReponse;
    @Column
    private Boolean estRepondue;
    @ManyToOne
    @JoinColumn(name = "idAdmin")
    private Admin admin;

    @Override
    public String toString() {
        return this.id + " " + this.nomEtPrenom + " " + this.telephone + " " + this.email+ " " + this.message + " " + this.dateEnvoi + " " + this.dateReponse ;
    }

}
