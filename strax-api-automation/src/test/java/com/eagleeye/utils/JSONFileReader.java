package com.eagleeye.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONFileReader
{

    @SuppressWarnings("unchecked")
    public JSONObject jsonReader(String path)
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        JSONObject obj= null;
         
        try
        {
        	obj = (JSONObject) jsonParser.parse(new FileReader(path));        

 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return obj;
    }
}