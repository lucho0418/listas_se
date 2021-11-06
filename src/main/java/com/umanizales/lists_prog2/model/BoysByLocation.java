package com.umanizales.lists_prog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * clase para almacenar la informacion de los niños lpor locacion
 * maneja datos obligatorios opara la locacion y el contador
 * @autor l castano
 */

@Data
@AllArgsConstructor
public class BoysByLocation {
    private Location location;
    private int count;
}
