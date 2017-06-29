package utils;

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

public class DatabaseConnection { 
	
	String user="strax";
			       // the user name
	String database="admin";    // the name of the database in which the user is defined
	String password="strax";    // the password as a character array
	
	public String mongodbOperation(String loginId)
	{
		String deletedLoginId="";
	
	try{

       MongoCredential credential = MongoCredential.createScramSha1Credential(user, database, password.toCharArray());
       MongoClient mongoClient = new MongoClient(new ServerAddress("qa-msg.strax.co"), Arrays.asList(credential));
       MongoDatabase db =  mongoClient.getDatabase("sproutdb");
       BasicDBObject query = new BasicDBObject("loginId", loginId);
       FindIterable<Document> cursor;
       MongoCollection<Document> coll = db.getCollection("participants");
       Document deleted = coll.findOneAndDelete(query);
      deletedLoginId = deleted.getString("loginId");
       System.out.println(deletedLoginId);
       
       mongoClient.close();
       
			
     }catch(Exception e){
        System.err.println( e );
     }
	return deletedLoginId;
	
	
	}
		
	

}
