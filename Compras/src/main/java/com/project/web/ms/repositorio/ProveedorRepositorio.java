package com.project.web.ms.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.web.ms.modelo.Proveedor;

@Repository
public interface ProveedorRepositorio  extends JpaRepository<Proveedor, Long>{

}
