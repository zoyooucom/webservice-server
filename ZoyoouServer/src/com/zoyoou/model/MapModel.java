/**
 * 
 */
package com.zoyoou.model;

import com.zoyoou.common.entity.Map;
import com.zoyoou.dao.DataAccessType;
import com.zoyoou.dao.MapDao;

/**
 * @author kaiwu
 *
 */
public class MapModel extends AbstractModel<MapDao, Map> {

	protected MapModel() {
		super(DataAccessType.MAP);
	}

}
