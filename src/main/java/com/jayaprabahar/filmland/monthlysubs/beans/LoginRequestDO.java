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
 * <p> Title : LoginRequestDO.java </p>
 * <p> Description: </p>
 * <p> Created: Jan 21, 2019</p>
 * 
 * @version 1.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LoginRequestDO {

	private @Id String email;
	private String password;

}
