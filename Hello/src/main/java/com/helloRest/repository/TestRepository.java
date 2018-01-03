package com.helloRest.repository;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.stereotype.Repository;

import com.helloRest.constant.Constant;
import com.helloRest.controller.HelloController;
import com.helloRest.domain.Student;
import com.helloRest.exception.InternalErrorException;
import com.helloRest.exception.NotFoundException;

@Repository("repo")
public class TestRepository <T extends Student, I extends Serializable> {
	
	@Autowired
	private MongoOperations mongoOperations;

	private MongoEntityInformation<T, I> typeInformation;
	
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	public TestRepository(Student student) {
	}	
	
	public Student save(T student) {

		try {

			logger.info("Save object");
			mongoOperations.save(student);

		} catch (Exception ex) {

			throw new InternalErrorException(ex);
		}
		return student;
	}
	
	public List<T> findAll() {

//		Query query = new Query();
//		List<Student> list = null;
//		
//		logger.info("Get all students");
//		list = this.mongoOperations.find(query, Student.class);

		logger.info("Finding all the entries");

        List<T> found = (List<T>) mongoOperations.find(new Query(), Student.class);

        logger.info("found the entries: ", found);

        return found;
	}
	
	public Student findById(String id) {

		Query query = new Query();
		Student result = null;

		try {

			logger.info("Get student with the id: {}",id);
			result = mongoOperations.findOne(query.addCriteria(Criteria.where("sId").is(id)), Student.class);
			
		} catch (Exception ex) {
			throw new InternalErrorException(ex);
		}
		return result;
		
	}
	
//	public Student removeAll() {
//
//		Student stu = new Student();
//
//		logger.info("Delete all students");
//		Student deleted = mongoOperations.remove(stu);
//		
//		return null;
//		
//		//Employee deletedEmployee = mongoOperation.findAndRemove(new Query(Criteria.where("empId").is(10003)), Employee.class,"dojCollection");
//	}
	
	public List<T> removeAll() {
        logger.info("Deleting all the entries");

        List<T> deleted = findAll();
        mongoOperations.remove(deleted);

        logger.info("Deleted the entries: ", deleted);

        return deleted;
    }
	
	public Student removeById(I id) {

		Query query = new Query();
		
		Student deletedStudent = mongoOperations.findAndRemove(query.addCriteria(Criteria.where("sId").is(id)), Student.class);
		return deletedStudent;
		
	}

	public Student updateById(Student student, String id) {
		
		Student stu= this.findById(id);
		logger.info("id = {}", stu.getsId());
		
		logger.info("information: {}", student);
		
		if (student == null) {
		    throw new NotFoundException("No such student for id: {}" + stu.getsId());
		}
		
		stu.setsName(student.getsName());
		stu.setAge(student.getAge());
		stu.setDistrict(student.getDistrict());
		stu.setCountry(student.getCountry());
		
		logger.info("Updated the entry: {}", stu);
		
		Student updatedStudent = this.save((T) stu);
		return updatedStudent;
		
	}
	
}
