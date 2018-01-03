package com.helloRest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import com.helloRest.controller.HelloController;
import com.helloRest.domain.Student;
import com.helloRest.exception.InternalErrorException;
import com.helloRest.repository.ObjRepository;
import com.helloRest.repository.TestRepository;

@Component
public class HelloService {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@Autowired
	//private ObjRepository repo;
	private TestRepository<Student, String> repo;
	
	public String displayContent() {
		return "Hello World";
	}
	
	public Student save(Student student) {
		logger.info("Creating a new entry with information: {}", student);
		
		Student stu = new Student();
		
		//stu.setsId(student.getsId());
		stu.setsName(student.getsName());
		stu.setAge(student.getAge());
		stu.setDistrict(student.getDistrict());
		stu.setCountry(student.getCountry());
		
		repo.save(stu);
		
		return student;
	}
	
	public List<Student> findAll() {
		logger.info("Finding all entries.");

        List<Student> stu = repo.findAll();

        logger.info("Found {} entries", stu.size());

        return stu;
	}
	
	public Student findById(String sId) {
		logger.info("Finding entry with id: {}", sId);

		Student found = repo.findById(sId);

        logger.info("Found entry: {}", found);

        return found;
	}
	
	public List<Student> deleteAll() {
		logger.info("Finding all entries.");

        List<Student> stu = repo.removeAll();//

        return stu;
	}
	
	public Student removeOne(String id) {
		logger.info("Removing one entry.");

        Student stu = repo.removeById(id);//

        return stu;
	}

	public Student updateOne(Student student, String id) {
		
		logger.info("Updating an entry (service)");
		
        Student stu = repo.updateById(student, id);
        
//        id = stu.getsId();
//        logger.info("Stu.id = ", id);
        
        
        return stu;
	}
	
}
