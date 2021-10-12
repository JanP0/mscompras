package com.project.web.ms.servicio;

import java.util.List;

import com.project.web.ms.modelo.Proveedor;

public interface ProveedorServicio {
	public List<Proveedor> ListAllProveedor();
	public Proveedor getProveedor(Long id);
	
	public Proveedor createProveedor(Proveedor proveedor);
	public Proveedor updateProveedor(Proveedor proveedor);
	public Proveedor deleteProveedor(Long id);

}
