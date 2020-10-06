package com.hsbc.system.jsonFile;

/**
 * 
 */

/**
 * @author Kartikeya
 *
 */

import java.io.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Pattern;

import org.json.simple.*;
import org.json.simple.parser.*;

import com.hsbc.system.invalid.InvalidEmail;
import com.hsbc.system.invalid.InvalidPhoneNumber;
import com.hsbc.system.invalid.InvalidRole;
import com.hsbc.system.invalid.InvalidUser;



public class JsonFileReader {
	
	private int credits;
	
	public JsonFileReader(int credits) {
		this.credits = credits;
	}
	
	public static boolean VerifyEmail(String mail) {
	    final Pattern REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
	    return REGEX.matcher(mail).matches();
	}
	
	public static boolean VerifyName(String name) {
		
		for(int i =0; i< name.length();i++) {
			char s = name.charAt(i);
			if(!((s>=65 && s<=90) || (s>=97 && s<=122))) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean VerifyPhone(String phone) {
		
		for(int i =0; i< phone.length();i++) {
			char s = phone.charAt(i);
			if(!(s>=48 && s<=57)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean VerifyRole(String role) {
		
		if(role.equalsIgnoreCase("admin")) {
			
			return true;
		}
		if(role.equalsIgnoreCase("member")) {
			return true;
		}
		if(role.equalsIgnoreCase("manager")) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		JSONParser parser = new JSONParser();
		
		JsonFileReader ObjCredit = new JsonFileReader(0);
		
		Logger logger = Logger.getLogger("JsonLog");  
	    FileHandler f;  
		
		try {

			f = new FileHandler("S://JsonLogFile.log");
			logger.addHandler(f);
			SimpleFormatter formatter = new SimpleFormatter();
			f.setFormatter(formatter);
			
			
			Object obj = parser.parse(new FileReader("S://sample1.json"));

			JSONObject jsonObject = (JSONObject) obj;
			
			logger.info("Json File parsed successfully");
			
			JSONArray arr = (JSONArray) jsonObject.get("Users");
			
			logger.info("Object Array created successfully");
			
			int i =0;
			try {
				while(true) {
					JSONObject o1 = (JSONObject) arr.get(i++);
					String name = (String) o1.get("name");
					String email = (String) o1.get("email");
					String phone = (String) o1.get("phone");
					String role = (String) o1.get("role");
					
					if(name==null || name == "" || !VerifyName(name)) {
						logger.info("Name of user :  "+name + "\nReason: ");
						logger.info("Invalid name in the Json Field");
						throw new InvalidUser();
					}
					if(email==null || email == "" || !VerifyEmail(email)) {
						logger.info("Name of user :  "+name + "\nReason: ");
						logger.info("Invalid Email in the Json Field");
						throw new InvalidEmail();
					}
					if(phone==null || phone.length() !=10 || !VerifyPhone(phone) ) {
						logger.info("Name of user :  "+name + "\nReason: ");
						logger.info("Invalid phone in the Json Field");
						throw new InvalidPhoneNumber();
					}
					if(role==null || role == "" || !VerifyRole(role)) {
						logger.info("Name of user :  "+name + "\nReason: ");
						logger.info("Invalid role in the Json Field");
						throw new InvalidRole();
					}
					
					System.out.println(name);
					System.out.println(email);
					System.out.println(phone);
					System.out.println(role);
					if(role.equalsIgnoreCase("manager"))
					{
						ObjCredit.credits = 2000;
					}
					else {
						ObjCredit.credits = 0;
					}
					System.out.println("Credits : "+ObjCredit.credits);
					System.out.println("-----------------------------------------");
					
					
			}
			}catch (Exception e) {
				logger.info("End of parsing Json file");
			
			}
			f.close();
		} catch (NullPointerException e) {
			logger.info("Caught Unwanted Null Pointer Exception");
		}
	}
}