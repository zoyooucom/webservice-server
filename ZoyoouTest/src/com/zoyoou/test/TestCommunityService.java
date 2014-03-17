package com.zoyoou.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.zoyoou.common.entity.ActiveStatus;
import com.zoyoou.common.entity.Community;
import com.zoyoou.common.entity.Map;
import com.zoyoou.common.entity.User;

public class TestCommunityService {
	
	
	private static final String SERVICE="ZoyoouWebService/";
//	private static final String SERVICE="ZoyoouService/";
	private static final String HOST = "localhost";
	private static final int PORT = 8080;
	private static final String ENTITY_CONTENT_TYPE="application/json";

	private static  String getUri(String service){
		return String.format("http://%s:%d/%s", HOST, PORT, service);
	}
	public static void main(String[] args) {
		String commname = "community " +6;
	
		try{
//			List<Community> comms = testCreate(getCommunityForCreate(commname));
//			if(comms!=null)
//				System.out.println("created");
//			else{
//				System.out.println("create failed");
//				return;
//			}
//			Map map = new Map();
//			map.setMapid(1);
//			
//			Community comm = comms.get(0);
//			
//			comm.setMap(map);
//			
//			comms = testUpdate(comm);
//			if(comms!=null){
//				System.out.println("updated");
//			}else{
//				System.out.println("Update failed");
//				return;
//			}
//			
//
			List<Community> comms = testFindById(8);
			System.out.println("Found " + comms.get(0).getCommunityId());
			Community comm = comms.get(0);
			
			Map map = new Map();
			map.setMapid(1);
			comm.setMap(map);
			
			comm.getActiveStatus().setActiveId((short)1);
			comms = testUpdate(comm);
			if(comms!=null){
				System.out.println("updated");
			}else{
				System.out.println("Update failed");
				return;
			}
			
			comms = testDelete(8);
			System.out.println("Deleted " + comms.get(0).getCommunityId());
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	
	
	private static List<Community> testDelete(long id) throws Exception{
		HttpClient  httpClient = new DefaultHttpClient();
		String uri = getUri(SERVICE + "community/" +id);
		
		System.out.println("URI: " + uri);
	
		
		//create HTTP DELETE
		HttpDelete delete = new HttpDelete(uri);
		
		HttpResponse httpResponse = httpClient.execute(delete);
		return displayResponse(httpResponse);
	}

	
	
	private static List<Community> testFindById(long uid) throws Exception{
		HttpClient  httpClient = new DefaultHttpClient();		
		
		String uri = getUri(SERVICE + "community/"  +uid);
		System.out.println("URI: " + uri);
		HttpGet httpGet = new HttpGet(uri);
		HttpResponse httpResponse = httpClient.execute(httpGet);
		return displayResponse(httpResponse);
	}
	
	
	
	
	private static List<Community> testUpdate(Community community) throws Exception{
		HttpClient  httpClient = new DefaultHttpClient();
		String uri = getUri(SERVICE + "community/" );
		System.out.println("URI: " + uri);
		
		ObjectMapper mapper = new ObjectMapper();
		String reqStr = mapper.writeValueAsString(community);
		System.out.println(reqStr);
		
		//create HTTP PUT
		HttpPut put = new HttpPut(uri);
		//add JSON String to post 
		StringEntity entity = new StringEntity(reqStr);
		entity.setContentType(ENTITY_CONTENT_TYPE); //set content type as JSON
		put.setEntity(entity); //set 
		
		HttpResponse httpResponse = httpClient.execute(put);
		return displayResponse(httpResponse);
		
	}
	
	private static List<Community> testCreate(Community community) throws Exception{
		HttpClient  httpClient = new DefaultHttpClient();
		String uri = getUri(SERVICE + "community/" );
		System.out.println("URI: " + uri);
		
		ObjectMapper mapper = new ObjectMapper();
		String reqStr = mapper.writeValueAsString(community);
		System.out.println(reqStr);
		
		//create HTTP Post
		HttpPost post = new HttpPost(uri);
		//add JSON String to post 
		StringEntity entity = new StringEntity(reqStr);
		entity.setContentType(ENTITY_CONTENT_TYPE); //set content type as JSON
		post.setEntity(entity); //set 
		
		HttpResponse httpResponse = httpClient.execute(post);
		return displayResponse(httpResponse);
	}
	
	
	
	static Community getCommunityForCreate(String name){
		Community community = new Community();
		community.setName(name);
		ActiveStatus status = new ActiveStatus();
		status.setActiveId((short)3);
		
		
		community.setActiveStatus(status);
		return community;
	}
	
	
	
	private static List<Community> displayResponse(HttpResponse response) throws Exception{
		String respStr = formatResponseString(response);
		System.out.println("Resposne: " + respStr);
			
		if(200==response.getStatusLine().getStatusCode()){
			ObjectMapper om = new ObjectMapper();
			List<Community> u = om.readValue(respStr,new TypeReference<List<Community>>() {});
			return u;
			
		}else{
			return null;
		}
			
		
	}
	
	private static String formatResponseString(HttpResponse response) throws IllegalStateException, IOException{

		//return null if there is no response
		if(null == response )return null;
	
		
		//read response and format to return string.
		StringBuilder responseStringBuilder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line;
		while((line = reader.readLine())!=null){
			responseStringBuilder.append(line);
		}
		reader.close();
		return responseStringBuilder.toString();
		
	}

}
