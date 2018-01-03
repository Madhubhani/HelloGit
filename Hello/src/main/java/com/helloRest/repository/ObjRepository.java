package com.helloRest.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.helloRest.domain.Student;

@Repository("Repo")
public class ObjRepository extends HelloRepository<Student, String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ObjRepository.class);

	/**
	 * Set class type to the
	 */
	public ObjRepository() {
		super(Student.class);
	}

	public Student saveObj(Student student) {

		LOGGER.info("Save object");

		save(student);

		return student;

	}

	//
	public Student getObjById(String name, String country) {

		LOGGER.info("Get student with name and country");

		Query query = new Query();

		query.addCriteria(Criteria.where("zxywv").is(name).and("sri_lanka").is(country));

		return findOne(query);

	}
	
	
	public List<Student> findAll() {

		LOGGER.info("Get all students");

		Query query = new Query();

		return findAll(query);
		
	}
}
