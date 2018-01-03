package com.helloRest.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;

import com.helloRest.domain.BaseDomain;
import com.helloRest.exception.InternalErrorException;
import com.helloRest.exception.NotFoundException;

public abstract class HelloRepository<T extends BaseDomain, I extends Serializable> {

	@Autowired
	private MongoOperations mongoOperations;

	private MongoEntityInformation<T, I> typeInformation;

	private Class<T> clazz;

	protected static final String DELETE_STATUS_FIELD = "isDeleted";

	/**
	 * Constructor Method.
	 * 
	 * @param <T>
	 *            : Model Class
	 * 
	 * @param <I>
	 *            : The type of the Identifier used.
	 * 
	 * @param metadatas
	 *            : {@link MongoEntityInformation}
	 * 
	 * @param mongoOperations
	 *            : {@link MongoOperations}
	 * 
	 * @param type
	 *            : {@link BaseDomain}
	 */
	public HelloRepository(Class<T> clazz) {
		this.clazz = clazz;
	}

	/**
	 * Access the mongo operations object from child classes.
	 * 
	 * @return {@link MongoOperations}
	 */
	protected MongoOperations getMongoOperations() {
		return mongoOperations;
	}

	/**
	 * Find One records for the query.
	 * 
	 * @param query
	 *            : The Query to Execute.
	 * 
	 * @return T.
	 */
	public T findOne(Query query) {

		T value = null;

		try {

			query.addCriteria(Criteria.where(DELETE_STATUS_FIELD).is(false));

			value = mongoOperations.findOne(query, clazz);

		} catch (Exception ex) {
			throw new InternalErrorException(ex);
		}

		return value;
	}

	/**
	 * Find List of records for the query.
	 * 
	 * @param query
	 *            : The Query to Execute.
	 * 
	 * @return List<T>.
	 */
	public List<T> findAll(Query query) {

		List<T> list = null;

		try {

			query.addCriteria(Criteria.where(DELETE_STATUS_FIELD).is(false));

			list = this.mongoOperations.find(query, typeInformation.getJavaType());

		} catch (Exception ex) {
			throw new InternalErrorException(ex);
		}

		if (list != null && !list.isEmpty()) {
			return list;
		} else {
			throw new NotFoundException();
		}
	}

	/**
	 * Page for search results.
	 * 
	 * @param query
	 *            : The Query to Execute.
	 * @param page
	 *            : Page Details.
	 * @return Page.
	 */
//	public Page<T> findAll(Query query, Pageable page) {
//
//		try {
//
//			query.addCriteria(Criteria.where(DELETE_STATUS_FIELD).is(false));
//			query.with(page);
//
//			long total = this.mongoOperations.count(query, clazz);
//
//			return new PageImpl<>(this.mongoOperations.find(query, clazz), page, total);
//
//		} catch (Exception ex) {
//			throw new InternalErrorException(ex);
//		}
//
//	}

	/**
	 * Save the object to the database.
	 * 
	 * @param object
	 *            : The object to save.
	 * 
	 * @throws Exception
	 */
	public void save(T object) {

		try {

			mongoOperations.save(object);

		} catch (Exception ex) {

			throw new InternalErrorException(ex);
		}
	}

	/**
	 * Delete object from the DB.
	 * 
	 * @param id
	 *            : Identifier.
	 * 
	 * @throws Exception
	 */
	public void remove(Query query) {

		try {
			mongoOperations.remove(query, typeInformation.getJavaType());

		} catch (Exception ex) {
			throw new InternalErrorException(ex);
		}
	}

	/**
	 * Remove all objects from the collection
	 * 
	 */
	public void clearCollection() {
		remove(new Query());
	}

	/**
	 * Remove the object by it Identifier.
	 * 
	 * @param id
	 *            : The identifier.
	 * 
	 */
	public void removeById(I id) {

		Query query = new Query();
		query.addCriteria(Criteria.where(typeInformation.getIdAttribute()).is(id));

		remove(query);
	}

	/**
	 * Get item using the Identifier.
	 * 
	 * @param id
	 *            : The identifier.
	 * 
	 * @return T
	 * 
	 */
	public T findById(I id) {

		Query query = new Query();
		query.addCriteria(Criteria.where(typeInformation.getIdAttribute()).is(id));

		return findOne(query);

	}

	/**
	 * Get all records from the Table.
	 *
	 * @return List of T.
	 *
	 * @throws Exception
	 *             : Not Found if List Empty
	 */
	public List<T> findAll() {

		return findAll(new Query());

	}

	/**
	 * Get All with pagination.
	 * 
	 * @param pageable
	 *            : Pagination details.
	 * 
	 * @return Page with Object content.
	 * 
	 * @throws Exception
	 */
//	public Page<T> findAll(Pageable pageable) {
//
//		return findAll(new Query(), pageable);
//
//	}

	/**
	 * Verify if the Identifier exists and the object is not flagged as deleted.
	 * 
	 * @param id
	 *            : The objects identifier.
	 * 
	 * @return boolean
	 */
	public boolean isExists(Query query) {

		query.addCriteria(Criteria.where(DELETE_STATUS_FIELD).is(false));

		return mongoOperations.exists(query, clazz);

	}

}
