package com.helloRest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.helloRest.controller.HelloController;
import com.helloRest.domain.Student;
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
	
//	@Cacheable(value = "Student", key = "#student")
	public Student save(Student student) {
		logger.info("Creating a new entry with information: {}", student);
		
		Student stu = new Student();
		
		stu.setsId(student.getsId());
		stu.setsName(student.getsName());
		stu.setAge(student.getAge());
		stu.setDistrict(student.getDistrict());
		stu.setCountry(student.getCountry());
		
		repo.save(stu);
		repo.saveStudent(stu);
		
		return student;
	}
	
	public List<Student> findAll() {
		logger.info("Finding all entries.");

        List<Student> stu = repo.findAll();

        logger.info("Found {} entries", stu.size());

        return stu;
	}
	

//	@CacheEvict(value="Student", allEntries=true)	
	public Student findById(String sId) {
		logger.info("Finding entry with id: {}", sId);

		Student idFromCache = repo.findFromCache(sId);

		if (idFromCache != null) {

			logger.info("Getting from cache");
			return idFromCache;
			
		} else {

			logger.info("Getting from DB");
			Student found = repo.findById(sId);
			return found;
			
		}
	}
	
	public List<Student> deleteAll() {
		logger.info("Finding all entries.");

        List<Student> stu = repo.removeAll();

        return stu;
	}
	
	public Student removeOne(String id) {
		logger.info("Removing one entry.");

        Student stu = repo.removeById(id);

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
