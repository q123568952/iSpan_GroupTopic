package com.sns.dropcat.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sns.dropcat.dao.PosttestDao;
import com.sns.dropcat.model.Posttest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class PosttestDaoImpl implements PosttestDao {
	@PersistenceContext
	EntityManager entityManager;

	

	@Override
	public List<Posttest> findAll() {
		String hql = "FROM exploretest";
		List<Posttest> postlist = entityManager.createQuery(hql, Posttest.class).getResultList();
		System.out.println(postlist.toString());
		return postlist;
	}

}
