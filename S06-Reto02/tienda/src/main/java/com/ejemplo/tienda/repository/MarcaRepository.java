package com.ejemplo.tienda.repository;

import com.ejemplo.tienda.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

    // Método para buscar marca por nombre
    Marca findByNombre(String nombre);
}
