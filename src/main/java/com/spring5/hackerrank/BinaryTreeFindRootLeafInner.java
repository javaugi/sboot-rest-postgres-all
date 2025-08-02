/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.hackerrank;

import com.spring5.MyApplication;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class BinaryTreeFindRootLeafInner implements CommandLineRunner{
    private static final Logger log = LoggerFactory.getLogger(BinaryTreeFindRootLeafInner.class);

    public static void main(String[] args) {
        //testDataPopulation();        
        printQueryResults(args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        log.info("BinaryTreeFindRootLeafInner with args {}", Arrays.toString(args)); 
    }
    
    private static void printQueryResults(String[] args) throws BeansException {
        // Start the Spring application and get the application context
        ConfigurableApplicationContext context = SpringApplication.run(MyApplication.class, args);
        log.info("BinaryTreeFindRootLeafInner started with context {}", context);

        // Application logic can be placed here, after the context is initialized
        BSTNativeQueryService myService = context.getBean(BSTNativeQueryService.class);
        log.info("StationNativeQueryService {}", myService);
        List<String> results = myService.doQuery(getQuery());
        log.info("StationNativeQueryService results {}", results);
        results.stream().forEach(System.out::println);

        // Close the application context when done
        context.close();
    }
    
    private static String getQuery() {
        String q = " select b.N, "
            + "         case when b.P is null then 'Root' "
            + "             when b.N in (select distinct b0.P from BST b0 where b0.P is not null) then 'Inner' "
            + "             else 'Leaf' "
            + "         end as p  "
            + "     from BST b "
            + "     order by b.N ";

        return q;
    }   
}

/*
Julia just finished conducting a coding contest, and she needs your help assembling the leaderboard! Write a query to print the 
    respective hacker_id and name of hackers who achieved full scores for more than one challenge. Order your output in descending 
    order by the total number of challenges in which the hacker earned a full score. If more than one hacker received full scores 
    in same number of challenges, then sort them by ascending hacker_id.
Input Format
The following tables contain contest data:
    Hackers: The hacker_id is the id of the hacker, and name is the name of the hacker. 
    Difficulty: The difficult_level is the level of difficulty of the challenge, and score is the maximum score that can be achieved for
        a challenge at that difficulty level. 
    Challenges: The challenge_id is the id of the challenge, the hacker_id is the id of the hacker who created the challenge, and 
        difficulty_level is the level of difficulty of the challenge. 
    Submissions: The submission_id is the id of the submission, hacker_id is the id of the hacker who made the submission, 
        challenge_id is the id of the challenge that the submission belongs to, and score is the score of the submission. 
Sample Input
    Hackers Table:  Difficulty Table:  Challenges Table:  Submissions Table: 
Sample Output
    90411 Joe

SQL Solution for Assembling the Leaderboard
To solve this problem, I need to:
    Identify hackers who achieved full scores (maximum possible score for a challenge)
    Count how many challenges each hacker got full scores on
    Filter for hackers with more than one full score challenge
    Sort by the count in descending order, then by hacker_id ascending
Here's the SQL query:
        SELECT 
            s.hacker_id, 
            h.name
        FROM 
            Submissions s
        JOIN 
            Challenges c ON s.challenge_id = c.challenge_id  -- Match submission to challenge
        JOIN 
            Difficulty d ON c.difficulty_level = d.difficulty_level -- Get max score for challenge difficulty
        JOIN 
            Hackers h ON s.hacker_id = h.hacker_id   -- Get hacker name
        WHERE 
            s.score = d.score  -- Only full scores
        GROUP BY 
            s.hacker_id, h.name  
        HAVING 
            COUNT(s.challenge_id) > 1   -- More than one full-score challenge
        ORDER BY 
            COUNT(s.challenge_id) DESC,  -- Order by most full scores
            s.hacker_id ASC;  -- Then by hacker_id

    this wouble be wrong 
        JOIN Challenges c ON s.challenge_id = c.challenge_id AND s.hacker_id = c.hacker_id
    But this would only find submissions where the hacker submitted a solution to their own challenge, which is likely 
        not the intended logic for a leaderboard.

Explanation:
Joining Tables:
    Connect Submissions with Challenges to get challenge details
    Connect with Difficulty to know the maximum possible score for each challenge
    Connect with Hackers to get the hacker names
Filtering Full Scores:
    WHERE s.score = d.score ensures we only count submissions where the hacker got the maximum possible score for that challenge's difficulty level
Grouping and Counting:
    Group by hacker_id and name
    HAVING COUNT(s.challenge_id) > 1 filters for hackers with more than one full score challenge
Sorting:
    Primary sort: Number of full score challenges in descending order
    Secondary sort: hacker_id in ascending order for ties

This query will return the hacker_id and name of all hackers who achieved full scores on more than one challenge,
    ordered as specified in the problem statement.
*/


/*
SQL Solution for Student Grades Report
To solve this problem, I'll need to:
    Join the Students and Grades tables
    Apply the specific filtering and sorting rules
    Handle the NULL name requirement for grades below 8

Here's the SQL query:
    SELECT 
        CASE 
            WHEN g.Grade >= 8 THEN s.Name 
            ELSE NULL 
        END AS Name,
        g.Grade,
        s.Marks
    FROM 
        Students s
    JOIN 
        Grades g ON s.Marks BETWEEN g.Min_Mark AND g.Max_Mark
    ORDER BY 
        g.Grade DESC,
        CASE WHEN g.Grade >= 8 THEN s.Name END ASC,
        CASE WHEN g.Grade < 8 THEN s.Marks END ASC;
Explanation:
    Joining Tables: We join Students with Grades where the student's mark falls between the Min_Mark and Max_Mark for each grade.

Name Handling:
    For grades 8-10, we display the student's name
    For grades below 8, we display NULL as the name

Sorting:
    Primary sort: Grade in descending order (highest first)
    For grades 8-10: Secondary sort by name alphabetically
    For grades below 8: Secondary sort by marks in ascending order
Note: The Grades table structure isn't fully shown in the problem, but I'm assuming it has columns Grade, Min_Mark, 
    and Max_Mark based on standard grading problems like this. If the column names are different, you would need to adjust the query accordingly.
*/

/*
You are given a table, BST, containing two columns: N and P, where N represents the value of a node in Binary Tree, and P is the parent of N.
Column  Type
N       Integer
P       Integer

Write a query to find the node type of Binary Tree ordered by the value of the node. Output one of the following for each node:

Root: If node is root node.
Leaf: If node is leaf node.
Inner: If node is neither root nor leaf node.

Sample Input
N       P
1       2
3       2
6       8
9       8
2       5
8       5
5       null

Sample Output

1 Leaf
2 Inner
3 Leaf
5 Root
6 Leaf
8 Inner
9 Leaf
*/