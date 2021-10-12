package com.project.web.ms.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.web.ms.modelo.Empleado;
import com.project.web.ms.modelo.OrdenCompra;
import com.project.web.ms.modelo.Proveedor;

@Repository
public interface OrdenCompraRepositorio extends JpaRepository<OrdenCompra, Long>{

	public  List<OrdenCompra> findByProveedor(Proveedor proveedor);
	public  List<OrdenCompra> findByEmpleado(Empleado empleado);
}
