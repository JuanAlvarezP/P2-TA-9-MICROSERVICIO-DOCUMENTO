package com.evalueytor.documento.controllers;

import java.util.List;
import java.util.Optional;

import javax.print.Doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evalueytor.documento.models.Documento;
import com.evalueytor.documento.repositories.DocumentoRepository;

@RestController
@RequestMapping("/api/documento")
public class DocumentoController {
    @Autowired
    DocumentoRepository documentoRepository;

    // Listar todo
    @GetMapping("/findall")
    public List<Documento> list() {
        return documentoRepository.findAll();
    }

    // Listar por Id
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Documento> obtenerPremioPorId(@PathVariable Long id) {
        Optional<Documento> premioOptional = documentoRepository.findById(id);
        return premioOptional.map(premio -> new ResponseEntity<>(premio, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear una nueva documento
    @PostMapping("/save")
    public ResponseEntity<Documento> crearPremio(@RequestBody Documento nuevoPremio) {
        Documento premioGuardado = documentoRepository.save(nuevoPremio);
        return new ResponseEntity<>(premioGuardado, HttpStatus.CREATED);
    }

    // Actualizar documento
    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<Documento> actualizarDocumento(@PathVariable Long id, @RequestBody Documento documentoActual) {
        Optional<Documento> documentoOptional = documentoRepository.findById(id);
        return documentoOptional.map(documento -> {
            documento.setId(id);
            documento.setDetalleFormulario_id(documentoActual.getDetalleFormulario_id());
            documento.setArchivo(documentoActual.getArchivo());
            Documento documentoActualGuardado = documentoRepository.save(documento);
            return new ResponseEntity<>(documentoActualGuardado, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar un empresa por ID
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Void> eliminarDocumento(@PathVariable Long id) {
        Optional<Documento> documentoOptional = documentoRepository.findById(id);
        if (documentoOptional.isPresent()) {
            documentoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
