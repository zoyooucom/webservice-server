/**
 * 
 */
package com.zoyoou.model;

/**
 * @author kaiwu
 *
 */
public enum ModelType {
	USER(1),
	CONTACTINFO(2),
	USER_COMMUNITY(3),
	COMMUNITY(4),
	MAP(5);
	
	public  final int Value;
	private ModelType(int value){
		this.Value = value; 
	}
	
}
