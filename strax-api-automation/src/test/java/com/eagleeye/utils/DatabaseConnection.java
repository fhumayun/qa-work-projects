package com.eagleeye.utils;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

public class DatabaseConnection {

	//PropertiesFileReader prreader = new PropertiesFileReader();
	public static String mongoDBServer = "msg"; //prreader.getPropertyvalues("mongoDBServer");
	public static	String user =   "strax"; //prreader.getPropertyvalues("mongoDBUserName");// the user name
	public static String database = "admin"; // the name of the database in which the user is defined
	public static String password = "strax";//prreader.getPropertyvalues("mongoDBPassword"); // the password as a character array
	public static MongoDatabase db;
	public static MongoClient mongoClient;
	
	public DatabaseConnection()
	{
		MongoCredential credential = MongoCredential.createScramSha1Credential(user, database,
				password.toCharArray());
		mongoClient = new MongoClient(new ServerAddress(mongoDBServer), Arrays.asList(credential));
		 db = mongoClient.getDatabase("sproutdb");
	}

	public String getAccount() {
		String deletedLoginId = "";

		try {

		    MongoCollection<Document> coll = db.getCollection("accounts");
			FindIterable<Document> cursor1 = coll.find();
	         JSON json =new JSON();
	        String serialize = json.serialize(cursor1);
	        System.out.println(serialize);
			//System.out.println(coll);

			mongoClient.close();

		} catch (Exception e) {
			System.err.println(e);
		}
		return deletedLoginId;

	}
	
	public String getParticipantDocId(String loginId)
	{
		BasicDBObject query = new BasicDBObject();
		query.put("loginId", "yogiraj.ghumade@msystechnologies.com");
		query.put("_id", "1");
		MongoCollection<Document>  coll = db.getCollection("participants");
				//db.getCollection("participants");
		FindIterable<Document> cursor = coll.find(query);

			System.out.println(cursor);

		//deletedIncident = deleted.getString("incident");
		return null;
		
	}
	
	public String getCluster() {
		String deletedLoginId = "";

		try {

		    MongoCollection<Document> coll = db.getCollection("clusters");
			FindIterable<Document> cursor1 = coll.find();
	         JSON json =new JSON();
	        String serialize = json.serialize(cursor1);
	        System.out.println(serialize);
			//System.out.println(coll);

			mongoClient.close();

		} catch (Exception e) {
			System.err.println(e);
		}
		return deletedLoginId;

	}

	public String deleteEvent(String incident) {
		String deletedIncident = "";
		try {

			MongoCredential credential = MongoCredential.createScramSha1Credential(user, database,
					password.toCharArray());
			MongoClient mongoClient = new MongoClient(new ServerAddress(mongoDBServer), Arrays.asList(credential));
			MongoDatabase db = mongoClient.getDatabase("sproutdb");
			BasicDBObject query = new BasicDBObject("incident", incident);
			FindIterable<Document> cursor;
			MongoCollection<Document> coll = db.getCollection("clusters");
			Document deleted = coll.findOneAndDelete(query);
			deletedIncident = deleted.getString("incident");
			System.out.println(deletedIncident);

			mongoClient.close();

		} catch (Exception e) {
			System.err.println(e);
		}
		return deletedIncident;

	}

	public String deleteEventPlan(String eventPlan) {
		String deletedEventPlan = "";
		try {

			MongoCredential credential = MongoCredential.createScramSha1Credential(user, database,
					password.toCharArray());
			MongoClient mongoClient = new MongoClient(new ServerAddress(mongoDBServer), Arrays.asList(credential));
			MongoDatabase db = mongoClient.getDatabase("sproutdb");
			BasicDBObject query = new BasicDBObject("planName", eventPlan);
			FindIterable<Document> cursor;
			MongoCollection<Document> coll = db.getCollection("eventplans");
			Document deleted = coll.findOneAndDelete(query);
			deletedEventPlan = deleted.getString("planName");
			System.out.println(deletedEventPlan);

			mongoClient.close();

		} catch (Exception e) {
			System.err.println(e);
		}
		return deletedEventPlan;

	}

	public String deleteUAS(String uas) {
		String deletedEventPlan = "";
		try {

			MongoCredential credential = MongoCredential.createScramSha1Credential(user, database,
					password.toCharArray());
			MongoClient mongoClient = new MongoClient(new ServerAddress(mongoDBServer), Arrays.asList(credential));
			MongoDatabase db = mongoClient.getDatabase("sproutdb");
			BasicDBObject query = new BasicDBObject("name", uas);
			FindIterable<Document> cursor;
			MongoCollection<Document> coll = db.getCollection("fidgets");
			Document deleted = coll.findOneAndDelete(query);
			deletedEventPlan = deleted.getString("name");
			System.out.println(deletedEventPlan);

			mongoClient.close();

		} catch (Exception e) {
			System.err.println(e);
		}
		return deletedEventPlan;

	}

}
