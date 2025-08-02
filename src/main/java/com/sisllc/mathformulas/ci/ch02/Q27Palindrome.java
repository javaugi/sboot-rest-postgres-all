package com.sisllc.mathformulas.ci.ch02;

import com.sisllc.mathformulas.ci.lib.LinkedListNode;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class Q27Palindrome {

    public class Result {

        public LinkedListNode node;
        public boolean result;

        public Result(LinkedListNode n, boolean res) {
            node = n;
            result = res;
        }
    }

    public Q27Palindrome.Result isPalindromeRecurse(LinkedListNode head, int length) {
        if (head == null || length == 0) {
            return new Q27Palindrome.Result(null, true);
        } else if (length == 1) {
            return new Q27Palindrome.Result(head.next, true);
        } else if (length == 2) {
            return new Q27Palindrome.Result(head.next.next, head.data == head.next.data);
        }
        Q27Palindrome.Result res = isPalindromeRecurse(head.next, length - 2);
        if (!res.result || res.node == null) {
            return res; // Only "result" member is actually used in the call stack.
        } else {
            res.result = head.data == res.node.data;
            res.node = res.node.next;
            return res;
        }
    }

    public boolean isPalindrome(LinkedListNode head) {
        int size = 0;
        LinkedListNode n = head;
        while (n != null) {
            size++;
            n = n.next;
        }
        Result p = isPalindromeRecurse(head, size);
        return p.result;
    }

    public static void main(String[] args) {
        int length = 10;
        LinkedListNode[] nodes = new LinkedListNode[length];
        for (int i = 0; i < length; i++) {
            nodes[i] = new LinkedListNode(i >= length / 2 ? length - i - 1 : i, null, null);
        }

        for (int i = 0; i < length; i++) {
            if (i < length - 1) {
                nodes[i].setNext(nodes[i + 1]);
            }
            if (i > 0) {
                nodes[i].setPrevious(nodes[i - 1]);
            }
        }
        // nodes[length - 2].data = 9; // Uncomment to ruin palindrome

        LinkedListNode head = nodes[0];
        System.out.println(head.printForward());
        Q27Palindrome q = new Q27Palindrome();
        System.out.println(q.isPalindrome(head));
    }
}
