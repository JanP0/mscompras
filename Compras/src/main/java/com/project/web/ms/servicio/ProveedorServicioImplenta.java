package com.project.web.ms.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.web.ms.modelo.Proveedor;
import com.project.web.ms.repositorio.ProveedorRepositorio;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProveedorServicioImplenta implements ProveedorServicio{
	
	public final ProveedorRepositorio proveedorRepositorio;

	@Override
	public List<Proveedor> ListAllProveedor() {
		return proveedorRepositorio.findAll();
	}

	@Override
	public Proveedor getProveedor(Long id) {
		return proveedorRepositorio.findById(id).orElse(null);
	}

	@Override
	public Proveedor createProveedor(Proveedor proveedor) {
		return proveedorRepositorio.save(proveedor);
	}

	@Override
	public Proveedor updateProveedor(Proveedor proveedor) {
		Proveedor proveedorupdate = getProveedor(proveedor.getIdproveedor());
		
		if (proveedorupdate == null) {
			return null;
		}
		proveedorupdate.setNombre(proveedor.getNombre());
		proveedorupdate.setDireccion(proveedor.getDireccion());
		proveedorupdate.setTelefono(proveedor.getTelefono());
		
		return proveedorRepositorio.save(proveedor);
	}

	@Override
	public Proveedor deleteProveedor(Long id) {
		Proveedor proveedorDelete = getProveedor(id);
		
		if (proveedorDelete == null) {
			return null;
		}
		
		return proveedorRepositorio.save(proveedorDelete);
	}
	
	


}
