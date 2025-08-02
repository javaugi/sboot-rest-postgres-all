package com.sisllc.mathformulas.ci.lib;

import java.util.HashSet;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class LinkedListNode {

    public LinkedListNode next;
    public LinkedListNode prev;
    public LinkedListNode last;
    public int data;

    public LinkedListNode(int d, LinkedListNode n, LinkedListNode p) {
        data = d;
        setNext(n);
        setPrevious(p);
    }

    public LinkedListNode() {
    }

    public void setNext(LinkedListNode n) {
        next = n;
        if (this == last) {
            last = n;
        }
        if (n != null && n.prev != this) {
            n.setPrevious(this);
        }
    }

    public void setPrevious(LinkedListNode p) {
        prev = p;
        if (p != null && p.next != this) {
            p.setNext(this);
        }
    }

    public String printForward() {
        if (next != null) {
            return data + "->" + next.printForward();
        } else {
            return ((Integer) data).toString();
        }
    }

    public LinkedListNode clone() {
        LinkedListNode next2 = null;
        if (next != null) {
            next2 = next.clone();
        }
        LinkedListNode head2 = new LinkedListNode(data, next2, null);
        return head2;
    }

    public static void main(String[] args) {
        LinkedListNode node = new LinkedListNode();
        LinkedListNode listNode = node.createPrintForward();
        listNode = node.deleteDups(listNode);
        System.out.println(listNode.printForward());
        //node.createPrintBackward();
    }

    public String printbackward() {
        if (prev != null) {
            return data + "->" + prev.printbackward();
        } else {
            return ((Integer) data).toString();
        }
    }

    public LinkedListNode deleteDups(LinkedListNode node) {
        LinkedListNode orig = node;
        HashSet<Integer> set = new HashSet();
        if (node != null && node.next != null) {
            LinkedListNode prev = null;
            while (node != null) {
                if (set.contains(node.data)) {
                    prev.next = node.next;
                } else {
                    set.add(node.data);
                    prev = node;
                }
                node = node.next;
            }

        }

        System.out.println(orig.printForward());
        return orig;
    }

    public LinkedListNode createPrintForward() {
        LinkedListNode first = new LinkedListNode(0, null, null);
        LinkedListNode head = first;
        LinkedListNode second = first;

        for (int i = 0; i < 8; i++) {
            second = new LinkedListNode(i / 2, null, null);
            first.next = second;
            second.prev = first;
            first = second;
        }

        System.out.println(head.printForward());

        return head;
    }

    public LinkedListNode createPrintBackward() {
        LinkedListNode last = new LinkedListNode(8, null, null);
        LinkedListNode end = last;
        LinkedListNode prev = last;

        for (int i = 8; i > 0; i--) {
            prev = new LinkedListNode(i / 2, null, null);
            last.prev = prev;
            prev.next = last;
            last = prev;
        }

        System.out.println(end.printbackward());

        return end;
    }

}
