package com.sisllc.mathformulas.impl;

import com.spring5.ProjectTest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class UnionIntersDiffCompleTest extends ProjectTest {

    String[] arr1 = {"1", "3", "5", "7", "9"};
    String[] arr2 = {"2", "3", "4", "5", "6", "7", "8"};
    Set<String> list1 = new HashSet(Arrays.asList(arr1));
    Set<String> list2 = new HashSet(Arrays.asList(arr2));
    UnionIntersDiffComple uidc = null;

    @Before
    public void setup() {
        uidc = new UnionIntersDiffComple();
    }

    @Test
    public void whenUnionThenContainsAll() {
        String[] list = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        System.out.println("union=" + uidc.union(list1, list2));
        Assert.assertTrue(uidc.union(list1, list2).containsAll(Arrays.asList(list)));
        Assert.assertTrue(Objects.equals(uidc.union(list1, list2), new TreeSet(Arrays.asList(list))));
    }

    @Test
    public void whenIntersectionThenContainsInBoth() {
        String[] list = {"3", "5", "7"};
        System.out.println("intersect=" + uidc.intersect(list1, list2));
        Assert.assertTrue(uidc.intersect(list1, list2).containsAll(Arrays.asList(list)));
        Assert.assertTrue(Objects.equals(uidc.intersect(list1, list2), new TreeSet(Arrays.asList(list))));
    }

    @Test
    public void whenDiffThenContainsInFirst() {
        String[] list = {"1", "9"};
        System.out.println("diff=" + uidc.diff(list1, list2));
        Assert.assertTrue(uidc.diff(list1, list2).containsAll(Arrays.asList(list)));
        Assert.assertTrue(Objects.equals(uidc.diff(list1, list2), new TreeSet(Arrays.asList(list))));
    }

    @Test
    public void whenComplementThenContainsInSecond() {
        String[] list = {"2", "4", "6", "8"};
        System.out.println("complement=" + uidc.complement(list1, list2));
        Assert.assertTrue(uidc.complement(list1, list2).containsAll(Arrays.asList(list)));
        Assert.assertTrue(Objects.equals(uidc.complement(list1, list2), new TreeSet(Arrays.asList(list))));
    }
}
