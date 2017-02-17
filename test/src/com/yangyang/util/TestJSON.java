package com.yangyang.util;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class TestJSON {

	public static void main(String[] args){
		ParseJSON("test.json");
		//CreateJSON();
	}
	
	public static void ParseJSON(String path)
	{
		JsonParser parser = new JsonParser();
		JsonObject obj;
		try {
			obj = (JsonObject) parser.parse(new FileReader(path));
			System.out.println(obj);
			System.out.println("cat=" + obj.get("cat").getAsString());
			System.out.println("pop=" + obj.get("pop").getAsBoolean());
		
			JsonArray array = obj.get("languages").getAsJsonArray();
		for (int i = 0; i < array.size(); i++) 
		{
			JsonObject subObj = array.get(i).getAsJsonObject();
			System.out.println("id=" + subObj.get("id").getAsInt());
			System.out.println("name=" + subObj.get("name").getAsString());
			System.out.println("ide=" + subObj.get("ide").getAsString());
		}
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void CreateJSON()
	{
		JsonObject obj = new JsonObject();
		obj.addProperty("cat", "it");
		
		JsonArray array = new JsonArray();
		JsonObject lan[] = new JsonObject[3];
		lan[0] = new JsonObject();
		lan[1] = new JsonObject();
		lan[2] = new JsonObject();
		lan[0].addProperty("id", 1);
		lan[0].addProperty("ide", "Eclipse");
		lan[0].addProperty("name", "Java");
		lan[1].addProperty("id", 2);
		lan[1].addProperty("ide", "Xcode");
		lan[1].addProperty("name", "Swift");
		lan[2].addProperty("id", 3);
		lan[2].addProperty("ide", "Visual Studio");
		lan[2].addProperty("name", "C#");
		array.add(lan[0]);
		array.add(lan[1]);
		array.add(lan[2]);
		
		obj.add("languages", array);
		obj.addProperty("pop", true);
		
		System.out.println(obj.toString());
	}
}
