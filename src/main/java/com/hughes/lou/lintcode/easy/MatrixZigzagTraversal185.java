/*
 * Copyright (C) 2018 Hughes Lou, Inc. All Rights Reserved.
 */

package com.hughes.lou.lintcode.easy;

/**
 * Created by Hughes on 2018/1/19 22:04.
 */
public class MatrixZigzagTraversal185 {
    /*
     * @param matrix: An array of integers
     * @return: An array of integers
     */
    public int[] printZMatrix(int[][] matrix) {
        // write your code here
        int x, y, dx, dy, count, m, n;
        x = y = 0;
        count = 1;
        dx = -1;
        dy = 1;
        m = matrix.length;
        n = matrix[0].length;
        int[] result = new int[m * n];
        result[0] = matrix[0][0];
        while (count < m * n) {
            if (x + dx >= 0 && y + dy >= 0 && x + dx < m && y + dy < n) {
                x += dx;
                y += dy;
            } else if (dx == -1 && dy == 1) {
                if (y + 1 < n) {
                    ++y;
                } else {
                    ++x;
                }
                dx = 1;
                dy = -1;
            } else {
                if (x + 1 < m) {
                    ++x;
                } else {
                    ++y;
                }
                dx = -1;
                dy = 1;
            }
            result[count] = matrix[x][y];
            ++count;
        }
        return result;
    }
}
