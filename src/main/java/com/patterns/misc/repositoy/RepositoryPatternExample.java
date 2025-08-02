/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.patterns.misc.repositoy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
Explanation of the Components:
    User Entity: This is a simple POJO (Plain Old Java Object) representing the data for a user. It has an id, username, and email.
    UserRepository Interface: This interface defines the contract for interacting with the User data. It specifies the common data access operations like findById, findAll, save, and deleteById. This interface acts as an abstraction, allowing you to easily switch between different data source implementations (e.g., a relational database, a NoSQL database, or an in-memory store) without modifying the service layer.
    InMemoryUserRepository Implementation: This is a concrete implementation of the UserRepository interface. In this example, it uses a HashMap to store the users in memory. In a real-world application, you would typically use a framework like Spring Data JPA to interact with a database.
    UserService: This class represents the business logic related to users. It depends on the UserRepository interface to perform data access operations. The service layer orchestrates the data retrieval and manipulation.
    RepositoryPatternExample: This is a simple class with a main method to demonstrate how to use the repository and service. It creates instances of the repository and service, performs some operations (creating, retrieving, listing, and deleting users), and prints the results.

Benefits of the Repository Pattern:
    Separation of Concerns: It clearly separates the data access logic from the business logic, making your code more organized and easier to understand and maintain.
    Testability: You can easily test the service layer by providing a mock implementation of the UserRepository.
    Maintainability: Changes to the data source (e.g., switching from one database to another) only require modifying the repository implementation, not the service layer.
    Code Reusability: The repository interface and its implementations can be reused across different parts of your application.

    In a real Spring application, you would typically use Spring Data JPA, which significantly simplifies the implementation of the repository pattern. 
    Spring Data JPA automatically generates the repository implementation based on the interface you define. Here's an example of how it would 
    look with Spring Data JPA:
 */
public class RepositoryPatternExample {

    private static final RepositoryPatternExample main = new RepositoryPatternExample();

    public static void main(String[] args) {
        main.run();
    }

    private void run() {
// Initialize the repository implementation
        UserRepository userRepository = new InMemoryUserRepository();

        // Initialize the service, injecting the repository
        UserService userService = new UserService(userRepository);

        // Create some users
        User user1 = userService.createUser("john.doe", "john.doe@example.com");
        User user2 = userService.createUser("jane.smith", "jane.smith@example.com");

        System.out.println("All users:");
        userService.getAllUsers().forEach(System.out::println);

        Long userIdToFind = user1.getId();
        Optional<User> foundUser = userService.getUserById(userIdToFind);
        if (foundUser.isPresent()) {
            System.out.println("\nFound user with ID " + userIdToFind + ": " + foundUser.get());
        } else {
            System.out.println("\nUser with ID " + userIdToFind + " not found.");
        }

        Long userIdToDelete = user2.getId();
        userService.deleteUser(userIdToDelete);
        System.out.println("\nUsers after deleting user with ID " + userIdToDelete + ":");
        userService.getAllUsers().forEach(System.out::println);
    }

// 1. Entity Class
    class User {

        private Long id;
        private String username;
        private String email;

        public User(Long id, String username, String email) {
            this.id = id;
            this.username = username;
            this.email = email;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "User{"
                    + "id=" + id
                    + ", username='" + username + '\''
                    + ", email='" + email + '\''
                    + '}';
        }
    }

// 2. Repository Interface
    interface UserRepository {

        Optional<User> findById(Long id);

        List<User> findAll();

        User save(User user);

        void deleteById(Long id);
    }

// 3. Repository Implementation (using an in-memory Map for simplicity)
    class InMemoryUserRepository implements UserRepository {

        private final Map<Long, User> users = new HashMap<>();
        private Long nextId = 1L;

        @Override
        public Optional<User> findById(Long id) {
            return Optional.ofNullable(users.get(id));
        }

        @Override
        public List<User> findAll() {
            return new ArrayList<>(users.values());
        }

        @Override
        public User save(User user) {
            if (user.getId() == null) {
                user.setId(nextId++);
            }
            users.put(user.getId(), user);
            return user;
        }

        @Override
        public void deleteById(Long id) {
            users.remove(id);
        }
    }

// 4. Service Class (using the Repository)
    class UserService {

        private final UserRepository userRepository;

        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        public Optional<User> getUserById(Long id) {
            return userRepository.findById(id);
        }

        public List<User> getAllUsers() {
            return userRepository.findAll();
        }

        public User createUser(String username, String email) {
            User newUser = new User(null, username, email);
            return userRepository.save(newUser);
        }

        public void deleteUser(Long id) {
            userRepository.deleteById(id);
        }
    }

}
