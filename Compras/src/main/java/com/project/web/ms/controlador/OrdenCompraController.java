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
import com.project.web.ms.modelo.OrdenCompra;
import com.project.web.ms.modelo.Proveedor;
import com.project.web.ms.servicio.OrdenCompraServicio;

@RestController
@RequestMapping(value = "/ordenCompra")
public class OrdenCompraController {
	
	@Autowired
	OrdenCompraServicio ordenCompraServis;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<OrdenCompra>> ListarDocReclamo(@RequestParam(name = "proveeId", 
	required = false) Long proveeId){
		
		List<OrdenCompra> ordenCompra = new ArrayList<>();
		
		if (proveeId == null) {
			ordenCompra = ordenCompraServis.ListAllOrdenCompra();
			if (ordenCompra.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		}else {
			ordenCompra = ordenCompraServis.findByProveedor(Proveedor.builder()
					.idproveedor(proveeId).build());
			if (ordenCompra.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		}
		
		return ResponseEntity.ok(ordenCompra);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<OrdenCompra> getOrdenCompra(@PathVariable("id") Long id){
		
		OrdenCompra ordenCompra = ordenCompraServis.getOrdenCompra(id);
		if (ordenCompra == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(ordenCompra);
	}
	
	//@RequestMapping(value = "/",method = RequestMethod.POST)
	@PostMapping
	public ResponseEntity<OrdenCompra> CrearOrdenCompra(@Valid @RequestBody OrdenCompra ordenCompra, BindingResult result){
		if(result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result)); 
			
		}
		OrdenCompra ordenCompraCreado = ordenCompraServis.createOrdenCompra(ordenCompra);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ordenCompraCreado);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<OrdenCompra> actualizarOrdenCompra(@PathVariable("id") Long id,
			@RequestBody OrdenCompra ordenCompra){
		
		ordenCompra.setIdordencompra(id);
		OrdenCompra ordenCompraEncontrados = ordenCompraServis.updateOrdenCompra(ordenCompra);
		
		if (ordenCompraEncontrados == null) {
			return ResponseEntity.notFound().build();
			
		}
		return ResponseEntity.ok(ordenCompraEncontrados);			
		
	}
	
	@DeleteMapping(value = "/eliminar/{id}")
	public ResponseEntity<OrdenCompra> deleteOrdenCompra(@PathVariable("id") Long id){
		OrdenCompra ordenCompraDelete = ordenCompraServis.deleteOrdenCompra(id);
		
		if(ordenCompraDelete == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(ordenCompraDelete);
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
