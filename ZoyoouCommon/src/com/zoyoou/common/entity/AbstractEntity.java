/**
 * 
 */
package com.zoyoou.common.entity;

import java.util.List;










import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.joda.time.DateTime;


/**
 * @author kaiwu
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL )
public abstract class AbstractEntity implements IEntity {
	private List<String> errorList;
    private DateTime createDate;
    private String createBy;
    private DateTime lastModifiedDate;
    private String lastModifiedBy;

	
	/* (non-Javadoc)
	 * @see com.zouyou.entity.IEntity#getCreateDate()
	 */
	@Override
	@JsonIgnore
	public DateTime getCreateDate() {
		return createDate;
	}

	/* (non-Javadoc)
	 * @see com.zouyou.entity.IEntity#setCreateDate()
	 */
	@Override
	public void setCreateDate(DateTime createDate) {
		this.createDate = createDate;
	}

	/* (non-Javadoc)
	 * @see com.zouyou.entity.IEntity#getCreateBy()
	 */
	@Override
	@JsonIgnore
	public String getCreateBy() {
		return createBy;
	}

	/* (non-Javadoc)
	 * @see com.zouyou.entity.IEntity#setCreateBy()
	 */
	@Override
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/* (non-Javadoc)
	 * @see com.zouyou.entity.IEntity#getLastModifiedDate()
	 */
	@Override
	@JsonIgnore
	public DateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	/* (non-Javadoc)
	 * @see com.zouyou.entity.IEntity#setLastModifiedDate()
	 */
	@Override
	public void setLastModifiedDate(DateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/* (non-Javadoc)
	 * @see com.zouyou.entity.IEntity#getLastModifiedBy()
	 */
	@Override
	@JsonIgnore
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	/* (non-Javadoc)
	 * @see com.zouyou.entity.IEntity#setLastModifiedBy()
	 */
	@Override
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	/* (non-Javadoc)
	 * @see com.zouyou.entity.IEntity#getErrorList()
	 */
	@Override
	public List<String> getErrorList() {
		return this.errorList;
	}

	/* (non-Javadoc)
	 * @see com.zouyou.entity.IEntity#setErrorList()
	 */
	@Override
	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;

	}

}
