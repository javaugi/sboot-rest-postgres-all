/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.graphql;

/**
 *
 * @author javaugi
 */
public class DockerNeo4jComposer {
    
}
/*
Installing Neo4j Using docker-compose
For this example, we are installing Neo4j in Docker, but you can also install it on a physical machine. To install it on a physical machine please click here.

Create a docker-compose file: docker-compose.yml
version: "3.8"
services:
  neo4j:
    image: neo4j:4.4.20-community
    ports:
      - 7474:7474
      - 7687:7687
    restart: unless-stopped
    environment:
      - NEO4J_AUTH=neo4j/password
    volumes:
      - ./db/data:/data
      - ./db/conf:/conf
      - ./db/logs:/logs
      - ./db/plugins:/plugins
2. Execute the docker-compose file

    docker-compose -f  docker-compose.yml up

3. Connect a Neo4j database to the workbench application.

Visit http://localhost:7474/browser
*/