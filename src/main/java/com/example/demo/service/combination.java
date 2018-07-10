package com.example.demo.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class combination {

    public static List<String> letterCombinations(String digits, int start, int end, int[] count) {

        LinkedList<String> ans = new LinkedList<String>();
        if(digits.isEmpty()) return ans;
        String[] mapping = new String[] {"0", "1", "2abc", "3def", "4ghi", "5jkl", "6mno", "7pqrs", "8tuv", "9wxyz"};
        StringBuilder sb = new StringBuilder();
        int[] index = new int[digits.length()];
        int i;
        for (i = digits.length() - 1; i >= 0 && start != 0; i--) {
            int x = Character.getNumericValue(digits.charAt(i));
            int divider = mapping[x].length();
            int remainder = start % divider;
            sb.insert(0, mapping[x].charAt(remainder));
            index[i] = remainder;
            start /= divider;
        }

        List<String> res = new ArrayList<>();
        String str = convert(mapping, index, digits, count);
        for (i = digits.length() - 1; i >= 0; i--) {
            String tmp = str.substring(0, i);
            dfs(res, index, mapping, digits, i, new StringBuilder(tmp));
            if (res.size() >= 10) break;
            boolean flag = true;
            while (i > 0 && flag) {
                int x = Character.getNumericValue(digits.charAt(i - 1));
                int uf = mapping[x].length();
                if (index[i - 1] < uf) {
                    index[i - 1]++;
                    flag = false;
                } else {
                    index[i - 1] = 0;
                    i--;
                }
            }
        }
        res.add(Integer.toString(count[0]));
        return res;
    }

    private static void dfs(List<String> list, int[] index, String[] mapping, String digits, int level, StringBuilder tmp) {
        if (tmp.length() == digits.length()) {
            list.add(tmp.toString());
        }
        if (tmp.length() >= digits.length() || list.size() >= 10) {
            return;
        }
        int x = Character.getNumericValue(digits.charAt(level));
        for (int i = index[level]; i < mapping[x].length(); i++) {
            tmp.append(mapping[x].charAt(i));
            if (list.size() < 10) {
                dfs(list, index, mapping, digits, level + 1, tmp);
            }
            tmp.deleteCharAt(tmp.length() - 1);
        }
        if (index[level] > 0) index[level] = 0;
    }


    private static String convert(String[] mapping, int[] index, String digits, int[] count) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < digits.length(); i++) {
            int x = Character.getNumericValue(digits.charAt(i));
            count[0] *= mapping[x].length();
            res.append(mapping[x].charAt(index[i]));
        }
        return res.toString();
    }
}


