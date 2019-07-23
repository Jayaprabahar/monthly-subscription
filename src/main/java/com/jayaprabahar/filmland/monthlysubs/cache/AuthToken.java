package com.jayaprabahar.filmland.monthlysubs.cache;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p> Project : monthlysubs </p>
 * <p> Title : AuthToken.java </p>
 * <p> Description: </p>
 * <p> Created: Jan 30, 2019</p>
 * 
 * @version 1.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@Data
@AllArgsConstructor
public class AuthToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 29994249269484047L;

	private final String token;
	private final String name;

	@Override
	public boolean equals(Object o) {
		return Objects.deepEquals(o, this);
	}

	@Override
	public int hashCode() {
		return Objects.hash(token, name);
	}

}
