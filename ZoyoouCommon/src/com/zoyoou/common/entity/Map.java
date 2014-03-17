/**
 * 
 */
package com.zoyoou.common.entity;

/**
 * @author kaiwu
 *
 */
public class Map extends AbstractEntity {
	private long mapid;
	private String mapInfo;
	private String description;
	private String province;
	private String city;
	private String disctrict;
	
	
	/**
	 * @return the mapid
	 */
	public long getMapid() {
		return mapid;
	}
	/**
	 * @param mapid the mapid to set
	 */
	public void setMapid(long mapid) {
		this.mapid = mapid;
	}
	/**
	 * @return the mapInfo
	 */
	public String getMapInfo() {
		return mapInfo;
	}
	/**
	 * @param mapInfo the mapInfo to set
	 */
	public void setMapInfo(String mapInfo) {
		this.mapInfo = mapInfo;
	}
	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the disctrict
	 */
	public String getDisctrict() {
		return disctrict;
	}
	/**
	 * @param disctrict the disctrict to set
	 */
	public void setDisctrict(String disctrict) {
		this.disctrict = disctrict;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	

}
