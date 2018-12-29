package com.sampleReport.DAO;

import org.apache.commons.digester.annotations.CreationRule;
import org.springframework.data.repository.CrudRepository;

import com.sampleReport.model.User;

public interface UserRepo extends CrudRepository<User, Integer>{

}
