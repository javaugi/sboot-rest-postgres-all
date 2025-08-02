/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.hackerrank;

import static com.abc.utils.RegexConstant.REGEX_CSV_FULL;
import com.spring5.MyApplication;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author javaugi
 */
public class FirstShortestLongestCityNames {
    private static final Logger log = LoggerFactory.getLogger(FirstShortestLongestCityNames.class);

    public static void main(String[] args) {
        //testDataPopulation();        
        doQueryFromFileData(args);
    }

    private static void testDataPopulation() {
        String[] strArr = {"Alaska,	AK,	Juneau,	Anchorage\n", "Arizona,	AZ,	Phoenix,	Phoenix"};
        for (String line: strArr) {
            String[] tokens = line.split(REGEX_CSV_FULL);
            System.out.println("line=" + line + "\n" + Arrays.toString(tokens));
            for (String str: tokens) {
                System.out.println("1 token=" + str);
                str = str.replaceAll(REGEX_CSV_FULL, "");
                System.out.println("2 token=" + str);
            }
        }
    }

    private static void doQueryFromFileData(String[] args) throws BeansException {
        // Start the Spring application and get the application context
        ConfigurableApplicationContext context = SpringApplication.run(MyApplication.class, args);
        log.info("FirstShortestLongestCityNames started with context {}", context);

        // Application logic can be placed here, after the context is initialized
        StationNativeQueryService myService = context.getBean(StationNativeQueryService.class);
        log.info("StationNativeQueryService {}", myService);
        List<String> results = myService.doQuery(getQuery());
        log.info("StationNativeQueryService results {}", results);
        results.stream().forEach(System.out::println);

        // Close the application context when done
        context.close();
    }

    private static String getQuery() {
        String q = "(select city, length(city) as city_len  from station order by length(city) asc, city asc limit 1) "
                + "union all "
                + "(select city, length(city) as city_len from station order by length(city) desc, city asc "
                + "limit 1)";

        return q;
    }    
    
    //Q1 is for Oracle or Standard
    private static final String Q1 = "select distinct city from station where lower(SUBSTR(city, 1, 1)) in ('a', 'e', 'i', 'o', 'u')";
    private static final String Q2 = "SELECT DISTINCT CITY FROM STATION WHERE REGEXP_LIKE(CITY, '^[aeiouAEIOU]', 'i');";
}

/*
select c.company_code, c.founder, lm.cnt, sm.cnt, m.cnt, e.cnt
from Company c, 
(select count(*) as cnt from Lead_Manager lm where lm.company_code  = c.company_code) lm,
(select count(*) as cnt from Senior_Manager sm where sm.company_code  = c.company_code) sm,
(select count(*) as cnt from Manager m where m.company_code  = c.company_code) m,
(select count(*) as cnt from Employee e where e.company_code  = c.company_code) e
order by c.company_code

There are several issues with your SQL query. Let me explain the problems and provide a corrected version:

Problems in Your Query:
    Correlated Subquery Syntax Error:
        You're trying to reference c.company_code inside subqueries, but this isn't allowed in the FROM clause the way you've structured it
        The subqueries in your FROM clause can't reference the outer table (c) directly
JOIN Logic Missing:
    You're creating Cartesian products between your subqueries and the Company table
    There's no explicit JOIN condition between the subqueries and the main table
Subquery Structure:
    Each subquery is returning just a count with no connection to the company
Corrected Query:
    SELECT 
        c.company_code, 
        c.founder, 
        COUNT(DISTINCT lm.lead_manager_code) AS lead_manager_count,
        COUNT(DISTINCT sm.senior_manager_code) AS senior_manager_count,
        COUNT(DISTINCT m.manager_code) AS manager_count,
        COUNT(DISTINCT e.employee_code) AS employee_count
    FROM 
        Company c
    LEFT JOIN 
        Lead_Manager lm ON c.company_code = lm.company_code
    LEFT JOIN 
        Senior_Manager sm ON c.company_code = sm.company_code
    LEFT JOIN 
        Manager m ON c.company_code = m.company_code
    LEFT JOIN 
        Employee e ON c.company_code = e.company_code
    GROUP BY 
        c.company_code, c.founder
    ORDER BY 
        c.company_code;
Alternative Solution (Using Subqueries Properly):
    If you prefer using subqueries (though joins are generally more efficient):

    SELECT 
        c.company_code, 
        c.founder,
        (SELECT COUNT(*) FROM Lead_Manager lm WHERE lm.company_code = c.company_code) AS lead_manager_count,
        (SELECT COUNT(*) FROM Senior_Manager sm WHERE sm.company_code = c.company_code) AS senior_manager_count,
        (SELECT COUNT(*) FROM Manager m WHERE m.company_code = c.company_code) AS manager_count,
        (SELECT COUNT(*) FROM Employee e WHERE e.company_code = c.company_code) AS employee_count
    FROM 
        Company c
    ORDER BY 
        c.company_code;
Key Improvements:
    Proper JOIN syntax connects tables correctly
    COUNT(DISTINCT ...) ensures you're counting unique codes (adjust if your schema differs)
    LEFT JOIN ensures all companies appear even if they have no managers/employees
    GROUP BY properly aggregates the counts by company

Note: If your tables don't have the exact column names I used (like lead_manager_code), adjust them to match your 
    actual schema. The DISTINCT keyword might not be needed if there's no possibility of duplicate counts in your data.

*/