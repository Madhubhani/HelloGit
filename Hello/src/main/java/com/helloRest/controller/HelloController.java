package com.helloRest.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.helloRest.constant.HeaderParameters;
import com.helloRest.domain.Student;
import com.helloRest.service.HelloService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/hello")
public class HelloController {

	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@Autowired
	private HelloService hs;
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create a record", notes = "Create a record")
    @ApiResponses({ @ApiResponse(code = 201, message = "Successful creation of record"),
	    @ApiResponse(code = 400, message = "Bad Request, validation error"),
	    @ApiResponse(code = 401, message = "Cannot find a valid token in X-Authorization header"),
	    @ApiResponse(code = 404, message = "Resource not Found"),
	    @ApiResponse(code = 409, message = "Trying to add duplicate") })
	@ApiImplicitParam(name = HeaderParameters.AUTHENTICATION, value = "PI Token for authentication", required = true, dataType = "string", paramType = "header")
	
    @ResponseStatus(HttpStatus.CREATED)
    Student create(@RequestBody @Valid Student student) {
    	logger.info("Creating a new entry with information: {}", student);

        Student saved = hs.save(student);
        logger.info("Created a new entry with information: {}", saved);

        return saved;
    }
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Display all the records", notes = "Get the records")
    @ApiResponses({ @ApiResponse(code = 200, message = "Successful display the records"),
	    @ApiResponse(code = 400, message = "Bad Request, validation error"),
	    @ApiResponse(code = 401, message = "Cannot find a valid token in X-Authorization header"),
	    @ApiResponse(code = 404, message = "Resource not Found")})
	@ApiImplicitParam(name = HeaderParameters.AUTHENTICATION, value = "PI Token for authentication", required = true, dataType = "string", paramType = "header")
    List<Student> findAll() {
    	logger.info("Finding all entries");

        List<Student> stu = hs.findAll();
        logger.info("Found {} entries", stu.size());

        return stu;
    }
//	public String displayMsg() {
//		return hs.displayContent();
//	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Display a record", notes = "Get a record")
    @ApiResponses({ @ApiResponse(code = 200, message = "Successful display of a record"),
	    @ApiResponse(code = 400, message = "Bad Request, validation error"),
	    @ApiResponse(code = 401, message = "Cannot find a valid token in X-Authorization header"),
	    @ApiResponse(code = 404, message = "Resource not Found")})
	@ApiImplicitParam(name = HeaderParameters.AUTHENTICATION, value = "PI Token for authentication", required = true, dataType = "string", paramType = "header")
	@ResponseStatus(HttpStatus.OK)
    public Student getStudentById(@PathVariable("id") String sId) {
	
	logger.info("Get by id");
	
	Student st = hs.findById(sId);
	
	return st;

    }
	
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Delete a record", notes = "Delete a student")
    @ApiResponses({ @ApiResponse(code = 204, message = "Successful deletion of record"),
	    @ApiResponse(code = 400, message = "Bad Request, validation error"),
	    @ApiResponse(code = 401, message = "Cannot find a valid token in X-Authorization header"),
	    @ApiResponse(code = 404, message = "Resource not Found")})
	@ApiImplicitParam(name = HeaderParameters.AUTHENTICATION, value = "PI Token for authentication", required = true, dataType = "string", paramType = "header")
	
	public Student deleteOne(@PathVariable("id") String sId) {
    	logger.info("Deleting one entry");

    	Student stu = hs.removeOne(sId);

        return stu;
    }
	
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update a record", notes = "Update a student")
    @ApiResponses({ @ApiResponse(code = 200, message = "Successfully updated the record"),
	    @ApiResponse(code = 400, message = "Bad Request, validation error"),
	    @ApiResponse(code = 401, message = "Cannot find a valid token in X-Authorization header"),
	    @ApiResponse(code = 404, message = "Resource not Found")})
	@ApiImplicitParam(name = HeaderParameters.AUTHENTICATION, value = "PI Token for authentication", required = true, dataType = "string", paramType = "header")
	public Student updateOne(@RequestBody @Valid Student student, @PathVariable("id") String sId) {
		
    	logger.info("Updating an entry (controller)");
    	logger.info("Student = {}", student);
    	
    	Student stu = hs.updateOne(student, sId);
    	
        return stu;
        
    }
	
}
