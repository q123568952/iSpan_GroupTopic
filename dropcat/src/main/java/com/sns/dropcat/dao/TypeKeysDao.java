package com.sns.dropcat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sns.dropcat.model.typeKeys;
@Repository
public interface TypeKeysDao extends JpaRepository<typeKeys, Integer>{

}
