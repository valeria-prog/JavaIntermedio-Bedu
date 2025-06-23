package com.ejemplo.tienda.repository;

import com.ejemplo.tienda.entity.Marca;
import com.ejemplo.tienda.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Encuentra productos con precio mayor al especificado
    List<Producto> findByPrecioGreaterThan(double precio);

    // Encuentra productos que contengan el texto en el nombre (ignorando mayúsculas/minúsculas)
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    // Encuentra productos con precio en un rango específico
    List<Producto> findByPrecioBetween(double min, double max);

    // Encuentra productos cuyo nombre comience con el prefijo especificado (ignorando mayúsculas/minúsculas)
    List<Producto> findByNombreStartingWithIgnoreCase(String prefijo);

    // Encuentra productos por marca
    List<Producto> findByMarca(Marca marca);

    // Encuentra productos por ID de marca
    List<Producto> findByMarcaId(Long marcaId);
}
