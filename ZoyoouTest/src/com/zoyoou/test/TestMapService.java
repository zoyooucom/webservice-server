package com.zoyoou.test;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.zoyoou.common.entity.Map;

public class TestMapService {
	
	
	private static final String SERVICE="ZoyoouWebService/";
//	private static final String SERVICE="ZoyoouService/";
	private static final String HOST = "localhost";
	private static final int PORT = 8080;
	private static final String ENTITY_CONTENT_TYPE="application/json";

	private static  String getUri(String service){
		return String.format("http://%s:%d/%s", HOST, PORT, service);
	}
	public static void main(String[] args) {
	
		try{
			List<Map> comms = testCreate(getMapForCreate());
			if(comms!=null)
				System.out.println("created");
			else{
				System.out.println("create failed");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	private static List<Map> testCreate(Map map) throws Exception{
		HttpClient  httpClient = new DefaultHttpClient();
		String uri = getUri(SERVICE + "mapinfo/" );
		System.out.println("URI: " + uri);
		
		ObjectMapper mapper = new ObjectMapper();
		String reqStr = mapper.writeValueAsString(map);
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
	
	
	
	static Map getMapForCreate(){
		Map map = new Map();
		map.setMapInfo("[{1,2}, {1,3}]");
		return map;
	}
	
	
	
	private static List<Map> displayResponse(HttpResponse response) throws Exception{
		String respStr = formatResponseString(response);
		System.out.println("Resposne: " + respStr);
			
		if(200==response.getStatusLine().getStatusCode()){
			ObjectMapper om = new ObjectMapper();
			List<Map> u = om.readValue(respStr,new TypeReference<List<Map>>() {});
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
