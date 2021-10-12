package com.project.web.ms.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.web.ms.modelo.Categoria;
import com.project.web.ms.modelo.Producto;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long>{

	public  List<Producto> findByCategoria(Categoria categoria);
}
