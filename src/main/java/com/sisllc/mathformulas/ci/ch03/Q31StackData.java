package com.sisllc.mathformulas.ci.ch03;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class Q31StackData {

    public int start;
    public int pointer;
    public int size = 0;
    public int capacity;

    public Q31StackData(int _start, int _capacity) {
        start = _start;
        pointer = _start - 1;
        capacity = _capacity;
    }

    public boolean isWithinStack(int index, int total_size) {
        // Note: if stack wraps, the head (right side) wraps around to the left.
        if (start <= index && index < start + capacity) {
            // non-wrapping, or "head" (right side) of wrapping case
            return true;
        } else if (start + capacity > total_size
                && index < (start + capacity) % total_size) {
            // tail (left side) of wrapping case
            return true;
        }
        return false;
    }
}
