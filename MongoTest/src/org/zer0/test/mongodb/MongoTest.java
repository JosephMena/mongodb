package org.zer0.test.mongodb;

import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class MongoTest {
	
	private MongoDatabase database;
	
	public static void main(String[] args) {
		MongoTest testMongoDB=new MongoTest();
		testMongoDB.conectar();
		//testMongoDB.testInsert1Documento();
		testMongoDB.actualizarDocumentoPersona();
	}
	
	private void conectar() {
		MongoClient cliente=new  com.mongodb.MongoClient("localhost",27017);
		database=cliente.getDatabase("testMongo");//Si la bd no existe entonces la crea
	}
	
	private void insertar1DocumentoSimple() {
		Document doc=new Document("dni","42569901").append("edad", 33)
				.append("apellidos", "Mena Sihuacollo")
				.append("nombres", "Joseph Cesar");
		inserTar1Documento(getCollection("persona"),doc);
	}
	
	private void insertar1DocumentoComplejo() {
		Document doc=new Document("dni","42569901").append("edad", 33)
				.append("apellidos", "Mena Sihuacollo")
				.append("nombres", "Joseph Cesar");
	}
	
	private void actualizarDocumentoPersona() {
		actualizarDocumento(getCollection("persona"),"5b37f13902cc1d18f08615a9",
				Updates.set("edad", 35),Updates.set("nacimiento", "Callao"));
	}
	
	private void inserTar1Documento(MongoCollection<Document> collection,Document doc) {
		//database.createCollection("persona"); -- si ya esta creado sale un error 
		collection.insertOne(doc);
	}

	private MongoCollection<Document> getCollection(String nombre) {
		return database.getCollection(nombre);
	}
	
	
	
	private void insertarVariosDocumentos(List<Document> documentos) {
		MongoCollection<Document> collection = getCollectionPersona();
		collection.insertMany(documentos);
	}
	
	private void actualizarDocumento(MongoCollection<Document> collection,String id,Bson... campos) {
		collection.updateOne(Filters.eq("_id", new ObjectId(id)),Updates.combine(campos));	
	}
	
	
	
}
