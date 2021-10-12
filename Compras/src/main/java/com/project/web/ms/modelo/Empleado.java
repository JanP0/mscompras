package com.project.web.ms.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleado")

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idempleado;
	private String nombre;
	private String num_documento;
	private String direccion;
	private String telefono;
	private String email;
}
