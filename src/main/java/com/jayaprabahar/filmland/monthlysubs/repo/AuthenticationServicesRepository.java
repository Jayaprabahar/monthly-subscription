package com.jayaprabahar.filmland.monthlysubs.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jayaprabahar.filmland.monthlysubs.beans.LoginRequestDO;

/**
 * <p> Project : monthlysubs </p>
 * <p> Title : AuthenticationServicesRepository.java </p>
 * <p> Description: Repository class for Login</p>
 * <p> Created: Jan 21, 2019</p>
 * 
 * @version 1.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 */

public interface AuthenticationServicesRepository extends JpaRepository<LoginRequestDO, String> {

}
