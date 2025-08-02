/*
 * Copyright (C) 2018 Strategic Information Systems, LLC.
 *
 */
package com.sisllc.mathformulas.ci.ch04;

import com.spring5.ProjectTest;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class GraphSearchTest extends ProjectTest {

    private static final Logger log = LoggerFactory.getLogger(GraphSearchTest.class);

    @Test
    public void whenCreateThenCount5() {
        Q42Graph g = Q42GraphSearch.createNewGraph();
        System.out.println("count=" + g.count);
        log.debug("count=" + g.count);
        Assert.assertTrue(6 == g.count);

        Q42Node[] nodes = g.getNodes();
        for (Q42Node node : nodes) {
            System.out.println("node=" + node + "\n adjacent=" + Arrays.toString(node.getAdjacent()));
        }
    }
}
