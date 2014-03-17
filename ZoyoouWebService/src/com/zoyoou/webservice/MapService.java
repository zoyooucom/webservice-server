package com.zoyoou.webservice;


import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;

import com.zoyoou.common.entity.Map;
import com.zoyoou.model.MapModel;
import com.zoyoou.model.ModelType;

@Provider
@Path(Constants.ServicePath.MAP_SERVICE_PATH)
public class MapService extends AbstractZuoyouWebservice<MapModel, Map> {

	public MapService() {
		super(ModelType.MAP);
	}

}
