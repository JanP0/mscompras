package com.project.web.ms.servicio;

import java.util.List;

import com.project.web.ms.modelo.Categoria;
import com.project.web.ms.modelo.Producto;

public interface ProductoServicio {

	public List<Producto> ListAllProducto();
	public Producto getProducto(Long id);
	
	public Producto createProducto(Producto producto);
	public Producto updateProducto(Producto producto);
	public Producto deleteProducto(Long id);
	
	public List<Producto> findByCategoria(Categoria categoria);
}
