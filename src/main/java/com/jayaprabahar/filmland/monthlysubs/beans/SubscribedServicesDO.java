/**
 * 
 */
package com.jayaprabahar.filmland.monthlysubs.beans;

import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> Project : monthlysubs </p>
 * <p> Title : SubscribedServicesDO.java </p>
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
public class SubscribedServicesDO {

	private @EmbeddedId SubscribedServicesKey servicesKey;
	private int remainContent;
	private double price;
	private LocalDate startDate;

	/**
	 * @param string
	 * @param string2
	 * @param i
	 * @param d
	 * @param now
	 */
	public SubscribedServicesDO(String email, String name, int remain, double pr, LocalDate date) {
		servicesKey = new SubscribedServicesKey(email, name);
		remainContent = remain;
		price = pr;
		startDate = date;
	}
}
