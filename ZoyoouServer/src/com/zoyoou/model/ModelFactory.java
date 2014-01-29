/**
 * 
 */
package com.zoyoou.model;

import com.zoyoou.common.entity.IEntity;

/**
 * @author kaiwu
 *
 */
public class ModelFactory {
	
	@SuppressWarnings("unchecked")
	public static <T extends IModel<? extends IEntity> > T  getModel(ModelType type){
		switch(type){
		case USER:
			return (T) new UserModel();
		case CONTACTINFO:
			return (T) new ContactInfoModel();
		case USER_COMMUNITY:
			return (T) new UserCommunityModel();
		default: 
			return null; 
		}
	}

}
