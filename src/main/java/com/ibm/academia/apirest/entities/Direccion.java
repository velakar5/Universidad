package com.ibm.academia.apirest.entities;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class Direccion implements Serializable {
	private String calle;
	private String numero;
	private String codigo_postal;
	private String departamento;
	private String piso;
	private String localidad;

	private static final long serialVersionUID = 8500313989796186076L;
}
