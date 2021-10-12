package com.project.web.ms.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.web.ms.modelo.Empleado;
import com.project.web.ms.modelo.OrdenCompra;
import com.project.web.ms.modelo.Producto;
import com.project.web.ms.modelo.Proveedor;
import com.project.web.ms.repositorio.OrdenCompraRepositorio;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdenCompraServicioImplementa implements OrdenCompraServicio{
	
	public final OrdenCompraRepositorio ordenConpraRepositorio;
	
	@Override
	public List<OrdenCompra> ListAllOrdenCompra() {
		return ordenConpraRepositorio.findAll();
	}

	@Override
	public OrdenCompra getOrdenCompra(Long id) {
		return ordenConpraRepositorio.findById(id).orElse(null);
	}

	@Override
	public OrdenCompra createOrdenCompra(OrdenCompra ordenCompra) {
		ordenCompra.setStatus("EN PROCESO");
		ordenCompra.setCreate_at(new Date());
		
		return ordenConpraRepositorio.save(ordenCompra);
	}

	@Override
	public OrdenCompra updateOrdenCompra(OrdenCompra ordenCompra) {
		OrdenCompra ordenCompraupdate = getOrdenCompra(ordenCompra.getIdordencompra());
		
		if (ordenCompraupdate == null) {
			return null;
		}
		ordenCompraupdate.setProveedor(ordenCompra.getProveedor());
		ordenCompraupdate.setEmpleado(ordenCompra.getEmpleado());
		ordenCompraupdate.setTotal(ordenCompra.getTotal());
		
		return ordenConpraRepositorio.save(ordenCompra);
	}

	@Override
	public OrdenCompra deleteOrdenCompra(Long id) {
		OrdenCompra ordenCompraDelete = getOrdenCompra(id);
		
		if (ordenCompraDelete == null) {
			return null;
		}
		ordenCompraDelete.setStatus("ELIMINADO");
		
		return ordenConpraRepositorio.save(ordenCompraDelete);
	}

	@Override
	public List<OrdenCompra> findByProveedor(Proveedor proveedor) {
		// TODO Auto-generated method stub
		return ordenConpraRepositorio.findByProveedor(proveedor);
	}

	@Override
	public List<OrdenCompra> findByEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
		return ordenConpraRepositorio.findByEmpleado(empleado);
	}

	

}
