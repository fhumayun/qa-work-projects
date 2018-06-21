package com.eagleeye.utils;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
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
	String mongoDBServer = "msg"; //prreader.getPropertyvalues("mongoDBServer");
	String user =   "strax"; //prreader.getPropertyvalues("mongoDBUserName");// the user name
	String database = "admin"; // the name of the database in which the user is defined
	String password = "strax";//prreader.getPropertyvalues("mongoDBPassword"); // the password as a character array

	public String getAccount() {
		String deletedLoginId = "";

		try {

			MongoCredential credential = MongoCredential.createScramSha1Credential(user, database,
					password.toCharArray());
			MongoClient mongoClient = new MongoClient(new ServerAddress(mongoDBServer), Arrays.asList(credential));
			MongoDatabase db = mongoClient.getDatabase("sproutdb");
			BasicDBObject query = new BasicDBObject();
			FindIterable<Document> cursor;
			MongoCollection<Document> coll = db.getCollection("accounts");
			//DBCollection coll = db.getCollection("accounts");
			//Document deleted = coll.findOneAndDelete(query);
			//deletedLoginId = deleted.getString("loginId");
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
