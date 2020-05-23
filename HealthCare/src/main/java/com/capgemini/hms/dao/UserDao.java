package com.capgemini.hms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.hms.entity.User;

@Repository
public interface UserDao extends JpaRepository<User,Long>{

}
