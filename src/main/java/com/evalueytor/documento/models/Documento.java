package com.evalueytor.documento.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data

public class Documento {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
private long detalleFormulario_id;
@Lob
private byte[] archivo;

}
