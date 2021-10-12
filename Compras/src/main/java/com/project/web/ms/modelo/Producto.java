package com.project.web.ms.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Producto {
 
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idproducto;
	
	@NotNull(message = "la categoria no puede estar vacio")
	@ManyToOne(fetch =FetchType.EAGER)
	@JoinColumn(name = "idcategoria")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Categoria categoria;
	
	private String codigo;
	private String nombre;
	private Double precio;
	private Double stock;
	private String descripcion;
	private String status;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_at;
}
