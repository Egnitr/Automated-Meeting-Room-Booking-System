package com.hsbc.system.entity;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateUserJson {
	public static void main(String[] args) throws JSONException {
		//creating JSon Object
		JSONObject userJson=new JSONObject();	
		
		//putting data to json object
		userJson.put("User", new Users("Megha","megha@gmail.com","7665754905","Admin").toString());
		
		//writing json object to json file
		try {
			FileWriter fw=new FileWriter("user.json",true);		
			fw.write(userJson.toString());
			fw.close();
		}
		catch(IOException ie) {
			System.out.println(ie);
		}
		
	}
}
