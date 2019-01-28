/**
 * 
 */
package com.jayaprabahar.filmland.monthlysubs.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> Project : monthlysubs </p>
 * <p> Title : GenericResponseDO.java </p>
 * <p> Description: Domain Object for generic response </p>
 * <p> Created: Jan 21, 2019</p>
 * 
 * @version 1.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponseDO {

	public static GenericResponseDO failedLogin = new GenericResponseDO("failed", "Sorry! You are not authorized to consume this subscription");
	public static GenericResponseDO sucessfulLogin = new GenericResponseDO("loggedin", "Welcome! You successfully logged in");

	public static GenericResponseDO failedSubscribe = new GenericResponseDO("Subscription failed", "Sorry! You can not subscribe a same service again");
	public static GenericResponseDO failedSubscribeUnavailable = new GenericResponseDO("Subscription failed", "Sorry! You can not subscribe an unavailable service");
	public static GenericResponseDO sucessfulSubscribe = new GenericResponseDO("Subscription successful", "Thank you subscribing. First month is free! You need to pay from the second month");
	
	public static GenericResponseDO failedShare = new GenericResponseDO("Sharing Service failed", "Sorry! You can not share a service which you are not subscribed");
	public static GenericResponseDO sucessfulShare = new GenericResponseDO("Sharing Service successful", "Great! Thank you for sharing the service. Your friend can immediately access the service");

	private String status;
	private String message;

}
