/**
 * 
 */
package com.jayaprabahar.filmland.monthlysubs.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> Project : monthlysubs </p>
 * <p> Title : AvailableServicesDO.java </p>
 * <p> Description: </p>
 * <p> Created: Jan 22, 2019</p>
 * 
 * @version 1.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AvailableServicesDO {

	private @Id String name;
	private int availableContent;
	private double price;
}
