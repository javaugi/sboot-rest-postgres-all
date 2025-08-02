/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author javaugi
 */
@SpringBootTest
public class DummyToSkipUnitTests {
    @Test
    public void dummy() {
    }    
}

/*
No tests matching pattern "DummyToSkipUnitTests" were executed! 
(Set -Dsurefire.failIfNoSpecifiedTests=false to ignore this error.)
means that you told Maven Surefire to run tests matching a specific pattern (DummyToSkipUnitTests), but no such tests exist, so the build fails.

✅ What It Means
You're likely running Maven with:

bash
Copy
Edit
mvn test -Dtest=DummyToSkipUnitTests
But there's no class named DummyToSkipUnitTestsTest.java, or it exists but:

It's not a real test (maybe empty or doesn't have @Test annotations)

It's not in the right directory (src/test/java)

It's not public or doesn't follow the test naming convention

💡 How to Fix or Bypass
✅ Option 1: Actually Create That Dummy Test
If you're using a placeholder like DummyToSkipUnitTests, just create the file:

java
Copy
Edit
// src/test/java/com/your/package/DummyToSkipUnitTests.java
public class DummyToSkipUnitTests {
    @org.junit.jupiter.api.Test
    public void dummy() {
        // Do nothing
    }
}
This will prevent the error when you specifically run with -Dtest=DummyToSkipUnitTests.

✅ Option 2: Tell Maven Not to Fail If No Test Found
You can suppress the error using this flag:

bash
Copy
Edit
mvn test -Dtest=DummyToSkipUnitTests -Dsurefire.failIfNoSpecifiedTests=false
Or if it's in your pom.xml, configure it like this:

xml
Copy
Edit
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-surefire-plugin</artifactId>
  <version>3.2.2</version>
  <configuration>
    <failIfNoSpecifiedTests>false</failIfNoSpecifiedTests>
  </configuration>
</plugin>
✅ Option 3: Don't Pass -Dtest at All
If you're not trying to run a specific test, just run:

bash
Copy
Edit
mvn test
and Maven will run all available tests without needing to specify one.
*/