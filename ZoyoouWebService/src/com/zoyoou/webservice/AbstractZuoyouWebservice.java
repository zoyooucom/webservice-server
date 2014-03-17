/**
 * 
 */
package com.zoyoou.webservice;

import java.util.ArrayList;
import java.util.List;

import com.zoyoou.common.entity.IEntity;
import com.zoyoou.model.IModel;
import com.zoyoou.model.ModelFactory;
import com.zoyoou.model.ModelType;

/**
 * @author kaiwu
 *
 */
public abstract class AbstractZuoyouWebservice <T extends IModel<E>,E extends IEntity> implements IZuoyouWebService <E> {

	protected T model;

	protected AbstractZuoyouWebservice(ModelType type){
		this.model = ModelFactory.getModel(type);
	}
	
	/* (non-Javadoc)
	 * @see com.abc.webservice.IZuoyouWebService#findById(long)
	 */
	@Override
	public List<E> findById(long id) throws Exception {
		E en = model.findById(id);
		List<E> ens = new ArrayList<E>(1);
		ens.add(en);
		
		
		return ens;
	}

	/* (non-Javadoc)
	 * @see com.abc.webservice.IZuoyouWebService#findAll()
	 */
	@Override
	public List<E> findAll() throws Exception {
		return model.findAll();
	}

	/* (non-Javadoc)
	 * @see com.abc.webservice.IZuoyouWebService#create(com.zouyou.entity.IEntity)
	 */
	@Override
	public List<E> create(E entity) throws Exception {
		E en = model.create(entity);
		List<E> ens = new ArrayList<E>(1);
		ens.add(en);
		return ens;
	}

	/* (non-Javadoc)
	 * @see com.abc.webservice.IZuoyouWebService#update(com.zouyou.entity.IEntity)
	 */
	@Override
	public List<E> update(E entity) throws Exception {
		E en = model.update(entity);
		List<E> ens = new ArrayList<E>(1);
		ens.add(en);
		return ens;
	}

	/* (non-Javadoc)
	 * @see com.abc.webservice.IZuoyouWebService#remove(long)
	 */
	@Override
	public List<E> remove(long id) throws Exception {
		E en = model.remove(id);
		List<E> ens = new ArrayList<E>(1);
		ens.add(en);
		return ens;
	}
	
	
	

}
