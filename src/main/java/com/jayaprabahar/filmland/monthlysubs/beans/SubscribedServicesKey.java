/**
 * 
 */
package com.jayaprabahar.filmland.monthlysubs.beans;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> Project : monthlysubs </p>
 * <p> Title : SubscribedServicesKey.java </p>
 * <p> Description: Key for the SubscribedServicesDO entity</p>
 * <p> Created: Jan 22, 2019</p>
 * 
 * @version 1.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscribedServicesKey implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5585192421493029631L;
	private String email;
	private String name;
}
