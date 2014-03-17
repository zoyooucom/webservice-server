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
import com.zoyoou.common.entity.Role;
import com.zoyoou.common.entity.User;
import com.zoyoou.common.entity.UserCommunityRelationship;

public class TestUserCommunityService {
	
	
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
			
//			UserCommunityRelationship ucr = getUserCommunityForCreate(4, 9);
//			testCreate(ucr);
//			ucr.getUser().setUserId(10);
//			testCreate(ucr);
//			ucr.getUser().setUserId(11);
//			testCreate(ucr);
			
			List<User> users = testFindUsers(1);
			
			System.out.println(users.size());
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	
	private static List<User> testFindUsers(long uid) throws Exception{
		HttpClient  httpClient = new DefaultHttpClient();		
		
		String uri = getUri(SERVICE + "communityuser/users/"  +uid);
		System.out.println("URI: " + uri);
		HttpGet httpGet = new HttpGet(uri);
		HttpResponse httpResponse = httpClient.execute(httpGet);
		return TestUserService.displayResponse(httpResponse);
	}
	
	
		
	
//	private static List<Community> testDelete(long id) throws Exception{
//		HttpClient  httpClient = new DefaultHttpClient();
//		String uri = getUri(SERVICE + "community/" +id);
//		
//		System.out.println("URI: " + uri);
//	
//		
//		//create HTTP DELETE
//		HttpDelete delete = new HttpDelete(uri);
//		
//		HttpResponse httpResponse = httpClient.execute(delete);
//		return displayResponse(httpResponse);
//	}

	
	
//	private static List<Community> testFindById(long uid) throws Exception{
//		HttpClient  httpClient = new DefaultHttpClient();		
//		
//		String uri = getUri(SERVICE + "community/"  +uid);
//		System.out.println("URI: " + uri);
//		HttpGet httpGet = new HttpGet(uri);
//		HttpResponse httpResponse = httpClient.execute(httpGet);
//		return displayResponse(httpResponse);
//	}
//	
//	
//	
	
//	private static List<Community> testUpdate(Community community) throws Exception{
//		HttpClient  httpClient = new DefaultHttpClient();
//		String uri = getUri(SERVICE + "community/" );
//		System.out.println("URI: " + uri);
//		
//		ObjectMapper mapper = new ObjectMapper();
//		String reqStr = mapper.writeValueAsString(community);
//		System.out.println(reqStr);
//		
//		//create HTTP PUT
//		HttpPut put = new HttpPut(uri);
//		//add JSON String to post 
//		StringEntity entity = new StringEntity(reqStr);
//		entity.setContentType(ENTITY_CONTENT_TYPE); //set content type as JSON
//		put.setEntity(entity); //set 
//		
//		HttpResponse httpResponse = httpClient.execute(put);
//		return displayResponse(httpResponse);
//		
//	}
	
	
	
	private static List<UserCommunityRelationship> testCreate(UserCommunityRelationship ucr) throws Exception{
		HttpClient  httpClient = new DefaultHttpClient();
		String uri = getUri(SERVICE + "communityuser/" );
		System.out.println("URI: " + uri);
		
		ObjectMapper mapper = new ObjectMapper();
		String reqStr = mapper.writeValueAsString(ucr);
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
	
	
	
	static UserCommunityRelationship getUserCommunityForCreate(long cid, long uid){
		UserCommunityRelationship ucr = new UserCommunityRelationship();
		
		Community c = new Community();
		c.setCommunityId(cid);
		User u = new User();
		u.setUserId(uid);
		Role r = new Role();
		r.setRoleId((short)4);
		ucr.setCommunity(c);
		ucr.setUser(u);
		ucr.setRole(r);
		return ucr;
	}
	
	
	
	private static List<UserCommunityRelationship> displayResponse(HttpResponse response) throws Exception{
		String respStr = formatResponseString(response);
		System.out.println("Resposne: " + respStr);
			
		if(200==response.getStatusLine().getStatusCode()){
			ObjectMapper om = new ObjectMapper();
			List<UserCommunityRelationship> u = om.readValue(respStr,new TypeReference<List<UserCommunityRelationship>>() {});
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
