package com.project.web.ms.servicio;

import java.util.List;

import com.project.web.ms.modelo.Empleado;
import com.project.web.ms.modelo.OrdenCompra;
import com.project.web.ms.modelo.Proveedor;



public interface OrdenCompraServicio {

	public List<OrdenCompra> ListAllOrdenCompra();
	public OrdenCompra getOrdenCompra(Long id);
	
	public OrdenCompra createOrdenCompra(OrdenCompra ordenCompra);
	public OrdenCompra updateOrdenCompra(OrdenCompra ordenCompra);
	public OrdenCompra deleteOrdenCompra(Long id);
	
	public List<OrdenCompra> findByProveedor(Proveedor proveedor);
	public List<OrdenCompra> findByEmpleado(Empleado empleado);
}
