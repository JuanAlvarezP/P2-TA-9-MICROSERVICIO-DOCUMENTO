package com.evalueytor.documento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evalueytor.documento.models.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long >{

}
