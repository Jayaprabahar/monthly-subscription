package com.jayaprabahar.filmland.monthlysubs.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jayaprabahar.filmland.monthlysubs.beans.AvailableServicesDO;

/**
 * <p> Project : monthlysubs </p>
 * <p> Title : AvailableServicesRepository.java </p>
 * <p> Description: Repository interface for Available Services</p>
 * <p> Created: Jan 22, 2019</p>
 * 
 * @version 1.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */

public interface AvailableServicesRepository extends JpaRepository<AvailableServicesDO, String> {

}
