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
import com.project.web.ms.modelo.Proveedor;
import com.project.web.ms.servicio.ProveedorServicio;

@RestController
@RequestMapping(value = "/provee")
public class ProveedorController {

	@Autowired
	ProveedorServicio proveedorServico;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Proveedor>> ListarProducto(@RequestParam(name = "proveedoraId", 
	required = false) Long proveedorId){
		
		List<Proveedor> proveedor = new ArrayList<>();
		
		if (proveedorId == null) {
			proveedor = proveedorServico.ListAllProveedor();
			if (proveedor.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		
		}
		
		return ResponseEntity.ok(proveedor);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<Proveedor> getProveedor(@PathVariable("id") Long id){
		
		Proveedor proveed = proveedorServico.getProveedor(id);
		if (proveed == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(proveed);
	}
	
	//@RequestMapping(value = "/",method = RequestMethod.POST)
	@PostMapping
	public ResponseEntity<Proveedor> CrearProveedor(@Valid @RequestBody Proveedor proveedor, BindingResult result){
		if(result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result)); 
			
		}
		Proveedor proveedorCreado = proveedorServico.createProveedor(proveedor);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(proveedorCreado);
	}

	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable("id") Long id,
			@RequestBody Proveedor proveedor){
		
		proveedor.setIdproveedor(id);
		Proveedor proveedorEncontrados = proveedorServico.updateProveedor(proveedor);
		
		if (proveedorEncontrados == null) {
			return ResponseEntity.notFound().build();
			
		}
		return ResponseEntity.ok(proveedorEncontrados);			
		
	}
	
	@DeleteMapping(value = "/eliminar/{id}")
	public ResponseEntity<Proveedor> deleteProveedor(@PathVariable("id") Long id){
		Proveedor prodveedortoDelete = proveedorServico.deleteProveedor(id);
		
		if(prodveedortoDelete == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(prodveedortoDelete);
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
