/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.mongodb;

import com.api.jsonata4java.expressions.functions.MapFunction;
import java.util.List;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mysql {

    //@Autowired
    private SparkSession sparkSession;

    //@Autowired
    private JavaSparkContext jsc;

    public List<String> getTableNames(String JDBC_URI, String JDBC_username, String JDBC_password) {
        List<String> TablesList = mysql_getTablesName(JDBC_URI, JDBC_username, JDBC_password);
        return TablesList;
    }

    public List<String> mysql_getTablesName(String uri, String username, String password) {
        String DB = uri.substring(uri.lastIndexOf("/") + 1);

        String dbQuery = "(SELECT table_name FROM information_schema.tables WHERE table_schema = '" + DB + "') tmp";
        Dataset<Row> mysql
                = sparkSession.read().format("jdbc")
                        .option("url", uri)
                        .option("user", username)
                        .option("password", password)
                        .option("dbtable", dbQuery)
                        .load();

        System.out.println("Total Tables " + mysql.count());
        /*
        List<String> list_tables_o = mysql.select("table_name")
                .map(row -> row.mkString(), Encoders.STRING())
                .collectAsList();
        List<String> list_tables = mysql.select("table_name")
                .map((MapFunction<Row, String>) row -> row.getString(0), Encoders.STRING())
                .collectAsList();
        // */

        List<String> list_tables = mysql.select("table_name")
                .as(Encoders.STRING())
                .collectAsList();

        return list_tables;
    }
}

/*
The error "referencing map is ambiguous" in the line:

java
Copy
Edit
List<String> list_tables = mysql.select("table_name")
    .map(row -> row.mkString(), Encoders.STRING())
    .collectAsList();
usually means that the compiler can't resolve which map() function to use, because multiple overloaded versions may exist in the context (e.g., from Java streams, Spark, or Dataset API).

✅ Solution
You need to explicitly qualify the map function from Spark's Dataset API and avoid lambda ambiguity. Here's how you can fix it:

✅ Option 1: Use MapFunction interface explicitly
java
Copy
Edit
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Encoders;

List<String> list_tables = mysql.select("table_name")
    .map((MapFunction<Row, String>) row -> row.getString(0), Encoders.STRING())
    .collectAsList();
🔍 Explanation:
mysql is a Dataset<Row>.

.select("table_name") returns another Dataset<Row>.

row.getString(0) accesses the first (and only) column.

Using (MapFunction<Row, String>) resolves ambiguity with Java's map() overloads.

🛠 Alternative (safer & clearer)
You can also use .as(Encoders.STRING()) if the selected column is already a String:

java
Copy
Edit
List<String> list_tables = mysql.select("table_name")
    .as(Encoders.STRING())
    .collectAsList();
This avoids needing a custom map function entirely.

Would you like help printing these values or integrating them into a Spark job?







You said:
error message - type map does not take parameters List<String> list_tables = mysql.select("table_name")
                .map((MapFunction<Row, String>) row -> row.getString(0), Encoders.STRING())
                .collectAsList();


ChatGPT said:
Ah, thanks for the clarification. The error "type map does not take parameters" suggests that you're calling .map() on an object that the compiler does not recognize as a Spark Dataset. Most likely, you're using Java Spark API and the object mysql is of type Dataset<Row>, but there may be an import or context mismatch.

Let’s walk through how to resolve this step by step.

✅ Correct Usage of .map() with Spark's Dataset in Java
Make sure the following imports are in place:

java
Copy
Edit
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.Encoders;
Then, assuming mysql is a Dataset<Row>:

java
Copy
Edit
List<String> list_tables = mysql.select("table_name")
    .map((MapFunction<Row, String>) row -> row.getString(0), Encoders.STRING())
    .collectAsList();
❗ Common Mistakes That Cause This Error
Wrong type on mysql – If mysql is not a Dataset<Row> but a JavaRDD<Row> or something else, then .map() means something entirely different.

Missing or incorrect Spark version imports – Ensure you're using org.apache.spark.sql.Dataset, not JavaRDD.

Mixing JavaRDD and Dataset APIs – If mysql was created with .javaRDD() or is a result of an RDD transformation, it doesn’t support the Dataset .map() with encoder.

✅ Alternate Fix Using .as() (If Column Is a String)
If the column table_name is already a String, and you're using the Spark SQL Dataset API, the simplest and safest way is:

java
Copy
Edit
List<String> list_tables = mysql.select("table_name")
    .as(Encoders.STRING())
    .collectAsList();
This is more idiomatic and avoids casting or defining a map function manually.

🧪 Confirm the Type
To verify what mysql.select("table_name") returns, you can do:

java
Copy
Edit
System.out.println(mysql.select("table_name").getClass());
It should be org.apache.spark.sql.Dataset.
*/