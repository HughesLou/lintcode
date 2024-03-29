/*
 * Copyright (C) 2017 Hughes Lou, Inc. All Rights Reserved.
 */

package com.hughes.lou.lintcode;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.tree.TreeNode;

import org.apache.commons.io.FileUtils;

/**
 * Created by hughes on 2017/12/17 00:02.
 */
public class Main {

    //    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) throws Exception {

        try {
            byte[] pcm16k = FileUtils.readFileToByteArray(new File(
                    "/Users/hugheslou/Downloads/lsm_9164676264_1645148284687_1645148334605_src-16k-pcm_align"));

            byte[] vocal = FileUtils.readFileToByteArray(new File(
                    "/Users/hugheslou/Downloads/lsm_9164676264_1645148284687_1645148334605_src-vocal-16k-pcm_align"));

            int size = Math.min(pcm16k.length, vocal.length);
            byte[] music = new byte[size];
            for (int i = 0; i < size; ) {
                int a = (pcm16k[i] & 0xFF) | ((pcm16k[i + 1] & 0xFF) << 8);
                int b = (vocal[i] & 0xFF) | ((vocal[i + 1] & 0xFF) << 8);
                int delta = a - b;

                music[i] = (byte) (delta & 0xFF);
                music[i + 1] = (byte) (delta >> 8);
                i += 2;
            }
            FileUtils.writeByteArrayToFile(
                    new File("/Users/hugheslou/Downloads/lsm_9164676264_1645148284687_1645148334605_src-music-16k-pcm"),
                    music);
            System.out.println("done");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int[] array1 =
                new int[] {0, 40, 44, 48, 53, 58, 64, 70, 77, 93, 112, 134, 161, 210, 273, 355, 461, 623, 841, 1135,
                        1533};
        int[] array2 =
                new int[] {0, 20, 22, 24, 26, 29, 32, 35, 38, 46, 56, 67, 80, 105, 136, 177, 230, 311, 420, 567, 766};

        String v0 = "4.3";
        String v1 = "4.1.3.5";
        String v2 = "4.1.3";
        String v3 = "4.2";
        String v4 = "3.1.3.5";
        String v5 = "6.1";
        String v6 = "14.1.3.5";

        int initialCapacity = 100;
        float loadFactor = 0.75f;

        long size = (long) (1.0 + (long) initialCapacity / loadFactor);
        int cap = (size >= (long) MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : tableSizeFor((int) size);

        System.out.println(cap);

        System.out.println(compareVersion(v0, v1));
        System.out.println(compareVersion(v0, v2));
        System.out.println(compareVersion(v0, v3));
        System.out.println(compareVersion(v0, v4));
        System.out.println(compareVersion(v0, v5));
        System.out.println(compareVersion(v0, v6));
    }

    public static int convertByteArrayToInt(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getInt();
    }

    // method 2, bitwise again, 0xff for sign extension
    public static int convertByteArrayToInt2(byte[] bytes) {
        return ((bytes[0] & 0xFF) << 24) |
                ((bytes[1] & 0xFF) << 16) |
                ((bytes[2] & 0xFF) << 8) |
                ((bytes[3] & 0xFF));
    }

    public static String convertBytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte temp : bytes) {
            result.append(String.format("%02x", temp));
        }
        return result.toString();
    }


    public static final int tableSizeFor(int c) {
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static int compareVersion(String version1, String version2) throws Exception {
        if (version1 == null || version2 == null) {
            throw new Exception("compareVersion error:illegal params.");
        }
        String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
            ++idx;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        java.util.Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();
                //                level.add(head.val);
                //                if (head.left != null) {
                //                    queue.offer(head.left);
                //                }
                //                if (head.right != null) {
                //                    queue.offer(head.right);
                //                }
            }
            result.add(level);
        }

        return result;
    }
}
