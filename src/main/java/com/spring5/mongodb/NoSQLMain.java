/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.mongodb;

/**
 *
 * @author javaugi
 */
public class NoSQLMain {
    
}
/*
NoSQL, which stands for "not only SQL," represents a category of database management systems that diverge from the traditional relational database model. These databases are designed to handle large volumes of unstructured or semi-structured data, offering flexibility, scalability, and high performance, particularly in distributed environments. NoSQL databases utilize various data models, including document, key-value, graph, and column-family stores.
Examples of NoSQL Databases and Java Code Snippets:

1. MongoDB (Document Database)
MongoDB stores data in flexible, JSON-like documents.
Java

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.Arrays;

public class MongoDBExample {
    public static void main(String[] args) {
        String uri = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("mydatabase");
            MongoCollection<Document> collection = database.getCollection("mycollection");

            Document doc = new Document("name", "John Doe")
                    .append("age", 30)
                    .append("city", "New York")
                    .append("hobbies", Arrays.asList("reading", "hiking"));

            collection.insertOne(doc);
            System.out.println("Document inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
2. Redis (Key-Value Store)
Redis stores data as key-value pairs and is known for its speed and versatility.
Java

import redis.clients.jedis.Jedis;

public class RedisExample {
    public static void main(String[] args) {
        try (Jedis jedis = new Jedis("localhost", 6379)) {
            jedis.set("name", "Jane Smith");
            String name = jedis.get("name");
            System.out.println("Name: " + name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
3. Cassandra (Column-Family Store)
Cassandra is designed for high availability and scalability, using a column-family data model.
Java

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

public class CassandraExample {
    public static void main(String[] args) {
        try (CqlSession session = CqlSession.builder().build()) {
            session.execute("CREATE KEYSPACE IF NOT EXISTS mykeyspace WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1}");
            session.execute("USE mykeyspace");
            session.execute("CREATE TABLE IF NOT EXISTS users (id int PRIMARY KEY, name text, city text)");
            session.execute("INSERT INTO users (id, name, city) VALUES (1, 'Alice', 'Los Angeles')");

            ResultSet rs = session.execute("SELECT * FROM users WHERE id = 1");
            Row row = rs.one();
            if (row != null) {
                System.out.println("User: " + row.getString("name") + ", City: " + row.getString("city"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
4. Neo4j (Graph Database)
Neo4j is a graph database that focuses on relationships between data entities.
Java

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org pragati.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;

import static org.neo4j.driver.Values.parameters;

public class Neo4jExample {
    private static Driver driver;

    public static void main(String[] args) {
        driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "password"));

        try (Session session = driver.session()) {
            String greeting = session.writeTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction tx) {
                    Result result = tx.run("CREATE (a:Greeting) " +
                                    "SET a.message = $message " +
                                    "RETURN a.message + ', from node ' + id(a)",
                            parameters("message", "Hello, world"));
                    return result.single().get(0).asString();
                }
            });
            System.out.println(greeting);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }
    }
}

*/