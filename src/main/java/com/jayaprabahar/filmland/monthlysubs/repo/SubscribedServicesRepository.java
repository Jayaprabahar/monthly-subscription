package com.jayaprabahar.filmland.monthlysubs.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jayaprabahar.filmland.monthlysubs.beans.SubscribedServicesDO;
import com.jayaprabahar.filmland.monthlysubs.beans.SubscribedServicesKey;

/**
 * <p> Project : monthlysubs </p>
 * <p> Title : SubscribedServicesRepository.java </p>
 * <p> Description: </p>
 * <p> Created: Jan 22, 2019</p>
 * 
 * @version 1.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */

public interface SubscribedServicesRepository extends JpaRepository<SubscribedServicesDO, SubscribedServicesKey> {

	List<SubscribedServicesDO> findAllByServicesKey_Email(String email);

}
