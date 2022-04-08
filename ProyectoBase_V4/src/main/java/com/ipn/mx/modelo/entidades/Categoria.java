package com.ipn.mx.modelo.entidades;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author darkdestiny
 */
@Data
@NoArgsConstructor
public class Categoria implements Serializable{
    private int idCategoria;
    private String nombreCategoria;
    private String descripcionCategoria;
}