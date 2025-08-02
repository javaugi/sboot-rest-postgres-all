/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

//https://github.com/javaugi/event-driven-microservices-docker-example
public class DockerConfig {
    
}

/*
✅ Step 1: Create a Simple Spring Boot App
You can use Spring Initializr to generate a basic app or create manually.

Example: HelloController.java
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Docker!";
    }
}
Make sure your app has the Spring Web dependency in pom.xml.

✅ Step 2: Package the App with Maven
Add spring-boot-maven-plugin to pom.xml (if not already present):
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
Then build your app:
mvn clean package
This creates a JAR file in the target/ directory.

✅ Step 3: Create a Dockerfile
In the root of your project, create a file named Dockerfile:

Dockerfile
# Use a lightweight Java image
FROM openjdk:17-jdk-slim

# Set environment variables
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME

# Copy the built jar into the container
COPY target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
✅ Step 4: Create a .dockerignore file (do not include target/ )
Add this file in the root directory to avoid copying unnecessary files into the image:
.git/
*.iml
*.log
✅ Step 5: Build and Run the Docker Image
1. Build the Docker image:
docker build -t springboot-docker-demo .
2. Run the container:
docker run -p 8080:8080 springboot-docker-demo
Now visit http://localhost:8080/hello and you should see:
Hello from Docker!
✅ Bonus: Docker Compose Example (optional)
If your app needs external services (e.g., a DB), create a docker-compose.yml:
version: '3.8'

services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=docker
Then run:
docker-compose up --build
*/


/*
Here’s a step-by-step guide for pushing a Spring Boot Docker image to Docker Hub, and deploying it to AWS, GCP, or Kubernetes.

✅ 1. Push Docker Image to Docker Hub
Step 1: Login to Docker Hub
docker login
Step 2: Tag the image for your Docker Hub repo
Format: docker tag <local-image> <dockerhub-username>/<repo-name>:<tag>
docker tag springboot-docker-demo your-dockerhub-username/springboot-docker-demo:latest
Step 3: Push to Docker Hub
docker push your-dockerhub-username/springboot-docker-demo:latest
✅ You can now pull and deploy this image from anywhere.

✅ 2. Deploy to AWS (Elastic Beanstalk with Docker)
Step 1: Create a Dockerrun.aws.json file
{
  "AWSEBDockerrunVersion": 1,
  "Image": {
    "Name": "your-dockerhub-username/springboot-docker-demo:latest",
    "Update": "true"
  },
  "Ports": [
    {
      "ContainerPort": "8080"
    }
  ]
}
Step 2: Deploy via AWS Elastic Beanstalk CLI
eb init -p docker springboot-docker-demo
eb create springboot-docker-env
eb open
✅ 3. Deploy to GCP (Cloud Run)
Step 1: Enable required APIs
gcloud services enable run containerregistry
Step 2: Push the image to Google Container Registry (GCR)
docker tag springboot-docker-demo gcr.io/your-project-id/springboot-docker-demo
docker push gcr.io/your-project-id/springboot-docker-demo
Step 3: Deploy to Cloud Run
gcloud run deploy springboot-docker-demo \
  --image gcr.io/your-project-id/springboot-docker-demo \
  --platform managed \
  --region us-central1 \
  --allow-unauthenticated
✅ 4. Deploy to Kubernetes (Generic or GKE/EKS)
Step 1: Create a Deployment YAML (springboot-deployment.yaml)
apiVersion: apps/v1
kind: Deployment
metadata:
  name: springboot-app
spec:
  replicas: 2
  selector:
    matchLabels:
      app: springboot-app
  template:
    metadata:
      labels:
        app: springboot-app
    spec:
      containers:
        - name: app
          image: your-dockerhub-username/springboot-docker-demo:latest
          ports:
            - containerPort: 8080
---
apiVersion: v1
kind: Service
metadata:
  name: springboot-service
spec:
  selector:
    app: springboot-app
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
  type: LoadBalancer
Step 2: Apply the deployment
kubectl apply -f springboot-deployment.yaml
This creates a public service using LoadBalancer.

Would you like Helm charts or Terraform examples to automate deployment?

Here’s a Terraform example to automate deployment of your Spring Boot Docker container to AWS using ECS Fargate. This setup includes:
ECR to store the Docker image
ECS Fargate to run the container
IAM roles, VPC, ALB, and security groups

✅ Prerequisites
Docker image pushed to AWS ECR
AWS CLI configured (aws configure)
Terraform installed

✅ Project Structure
css
terraform/
├── main.tf
├── variables.tf
├── outputs.tf
└── ecs.tf
📄 variables.tf
hcl
variable "region" {
  default = "us-east-1"
}
variable "app_name" {
  default = "springboot-app"
}
variable "docker_image" {
  description = "Full ECR Docker image URL"
}
📄 main.tf
hcl
provider "aws" {
  region = var.region
}
resource "aws_vpc" "main" {
  cidr_block = "10.0.0.0/16"
}
resource "aws_subnet" "subnet" {
  count             = 2
  vpc_id            = aws_vpc.main.id
  cidr_block        = cidrsubnet(aws_vpc.main.cidr_block, 8, count.index)
  availability_zone = data.aws_availability_zones.available.names[count.index]
}
data "aws_availability_zones" "available" {}
resource "aws_security_group" "ecs_sg" {
  name        = "ecs-sg"
  description = "Allow HTTP"
  vpc_id      = aws_vpc.main.id

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}
📄 ecs.tf
hcl
resource "aws_ecs_cluster" "cluster" {
  name = "${var.app_name}-cluster"
}

resource "aws_iam_role" "ecs_task_execution_role" {
  name = "${var.app_name}-execution-role"
  assume_role_policy = jsonencode({
    Version = "2012-10-17",
    Statement = [{
      Action = "sts:AssumeRole",
      Effect = "Allow",
      Principal = {
        Service = "ecs-tasks.amazonaws.com"
      }
    }]
  })
}

resource "aws_iam_role_policy_attachment" "ecs_task_execution_policy" {
  role       = aws_iam_role.ecs_task_execution_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonECSTaskExecutionRolePolicy"
}

resource "aws_ecs_task_definition" "task" {
  family                   = "${var.app_name}-task"
  requires_compatibilities = ["FARGATE"]
  network_mode             = "awsvpc"
  cpu                      = "256"
  memory                   = "512"
  execution_role_arn       = aws_iam_role.ecs_task_execution_role.arn

  container_definitions = jsonencode([
    {
      name      = var.app_name
      image     = var.docker_image
      essential = true
      portMappings = [{
        containerPort = 8080
        hostPort      = 8080
      }]
    }
  ])
}

resource "aws_lb" "alb" {
  name               = "${var.app_name}-alb"
  internal           = false
  load_balancer_type = "application"
  subnets            = aws_subnet.subnet[*].id
  security_groups    = [aws_security_group.ecs_sg.id]
}

resource "aws_lb_target_group" "tg" {
  name     = "${var.app_name}-tg"
  port     = 8080
  protocol = "HTTP"
  vpc_id   = aws_vpc.main.id
  target_type = "ip"
  health_check {
    path = "/"
  }
}

resource "aws_lb_listener" "listener" {
  load_balancer_arn = aws_lb.alb.arn
  port              = 80
  protocol          = "HTTP"

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.tg.arn
  }
}

resource "aws_ecs_service" "service" {
  name            = "${var.app_name}-service"
  cluster         = aws_ecs_cluster.cluster.id
  task_definition = aws_ecs_task_definition.task.arn
  desired_count   = 1
  launch_type     = "FARGATE"

  network_configuration {
    subnets          = aws_subnet.subnet[*].id
    security_groups  = [aws_security_group.ecs_sg.id]
    assign_public_ip = true
  }

  load_balancer {
    target_group_arn = aws_lb_target_group.tg.arn
    container_name   = var.app_name
    container_port   = 8080
  }

  depends_on = [aws_lb_listener.listener]
}
📄 outputs.tf
hcl
output "load_balancer_dns" {
  value = aws_lb.alb.dns_name
}
✅ Deploy with Terraform
# 1. Initialize
terraform init
# 2. Review
terraform plan -var="docker_image=your-aws-account-id.dkr.ecr.us-east-1.amazonaws.com/your-repo:latest"
# 3. Apply
terraform apply -var="docker_image=your-aws-account-id.dkr.ecr.us-east-1.amazonaws.com/your-repo:latest"
Once complete, you’ll get the load balancer DNS to access your deployed app.


*/