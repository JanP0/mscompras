package com.project.web.ms.controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.web.ms.modelo.Categoria;
import com.project.web.ms.modelo.Producto;
import com.project.web.ms.servicio.ProductoServicio;


@RestController
@RequestMapping(value = "/produc")
public class ProductoController {
	
	@Autowired
	ProductoServicio productoServicio;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Producto>> ListarProducto(@RequestParam(name = "categoriaId", 
	required = false) Long categoriaId){
		
		List<Producto> product = new ArrayList<>();
		
		if (categoriaId == null) {
			product = productoServicio.ListAllProducto();
			if (product.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		}else {
			product = productoServicio.findByCategoria(Categoria.builder()
					.idcategoria(categoriaId).build());
			if (product.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		}
		
		return ResponseEntity.ok(product);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<Producto> getProducto(@PathVariable("id") Long id){
		
		Producto product = productoServicio.getProducto(id);
		if (product == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(product);
	}
	
	//@RequestMapping(value = "/",method = RequestMethod.POST)
	@PostMapping
	public ResponseEntity<Producto> CrearProducto(@Valid @RequestBody Producto product, BindingResult result){
		if(result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result)); 
			
		}
		Producto productoCreado = productoServicio.createProducto(product);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);
	}

	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Producto> actualizarDocReclamo(@PathVariable("id") Long id,
			@RequestBody Producto produc){
		
		produc.setIdproducto(id);
		Producto productosEncontrados = productoServicio.updateProducto(produc);
		
		if (productosEncontrados == null) {
			return ResponseEntity.notFound().build();
			
		}
		return ResponseEntity.ok(productosEncontrados);			
		
	}
	
	@DeleteMapping(value = "/eliminar/{id}")
	public ResponseEntity<Producto> deleteDocReclamo(@PathVariable("id") Long id){
		Producto productoDelete = productoServicio.deleteProducto(id);
		
		if(productoDelete == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(productoDelete);
	}
	
	private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
