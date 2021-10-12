package com.project.web.ms.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.web.ms.modelo.Categoria;
import com.project.web.ms.modelo.Producto;
import com.project.web.ms.repositorio.ProductoRepositorio;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoServicioImplementa  implements ProductoServicio{
	
	public final ProductoRepositorio productoRepositorio;
	
	@Override
	public List<Producto> ListAllProducto() {
		return productoRepositorio.findAll();
	}

	@Override
	public Producto getProducto(Long id) {
		return productoRepositorio.findById(id).orElse(null);
	}

	@Override
	public Producto createProducto(Producto producto) {
		producto.setStatus("EN PROCESO");
		producto.setCreate_at(new Date());
		
		return productoRepositorio.save(producto);
	}

	@Override
	public Producto updateProducto(Producto producto) {
		Producto productoupdate = getProducto(producto.getIdproducto());
		
		if (productoupdate == null) {
			return null;
		}
		productoupdate.setCategoria(producto.getCategoria());
		productoupdate.setCodigo(producto.getCodigo());
		productoupdate.setNombre(producto.getNombre());
		productoupdate.setPrecio(producto.getPrecio());
		productoupdate.setStock(producto.getStock());
		productoupdate.setDescripcion(producto.getDescripcion());
		
		return productoRepositorio.save(producto);
	}

	@Override
	public Producto deleteProducto(Long id) {

		Producto productoDelete = getProducto(id);
		
		if (productoDelete == null) {
			return null;
		}
		productoDelete.setStatus("ELIMINADO");
		
		return productoRepositorio.save(productoDelete);
	}

	@Override
	public List<Producto> findByCategoria(Categoria categoria) {
		return productoRepositorio.findByCategoria(categoria);
	}

}
