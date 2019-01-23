/**
 * 
 */
package com.jayaprabahar.filmland.monthlysubs.beans;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p> Project : monthlysubs </p>
 * <p> Title : GetServicesResponseDO.java </p>
 * <p> Description: </p>
 * <p> Created: Jan 22, 2019</p>
 * <p> Copyright: KLM Royal Dutch Airlines. All Rights Reserved. (c) 2018 </p>
 * <p> Company: AIRFRANCE-KLM </p>
 * 
 * @version 1.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */
@Data
@AllArgsConstructor
public class GetServicesResponseDO {

	private List<AvailableServicesDO> availableServices;
	private List<SubscribedServicesDO> subscribedServices;

}
