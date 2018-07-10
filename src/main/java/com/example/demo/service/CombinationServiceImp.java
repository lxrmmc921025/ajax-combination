package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class CombinationServiceImp implements CombinationService {
    int pagesize, count;
    String digits;
    String[] mapping = new String[] {"0", "1", "2abc", "3def", "4ghi", "5jkl", "6mno", "7pqrs", "8tuv", "9wxyz"};

    public List<String> letterCombinations(String digits, int pagenum, int size) {
        this.digits = digits;
        pagesize = size;
        count = 1;
        int start = pagenum * size;

        //count index map
        int[] index = new int[digits.length()];
        StringBuilder sb = new StringBuilder();
        for (int i = digits.length() - 1; i >= 0; i--) {
            int x = Character.getNumericValue(digits.charAt(i));
            int divider = mapping[x].length();
            count *= divider;
            index[i] = start % divider;
            sb.insert(0, mapping[x].charAt(index[i]));
            start /= divider;
        }

        List<String> res = combination(index, sb.toString());
        res.add(Integer.toString(count));
        return res;
    }

    private List<String> combination(int[] index, String str) {
        List<String> res = new ArrayList<>();
        for (int i = digits.length() - 1; i >= 0; i--) {
            String tmp = str.substring(0, i);
            dfs(res, index, i, new StringBuilder(tmp));
            if (res.size() >= pagesize) break;
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
        return res;
    }

    private void dfs(List<String> list, int[] index, int level, StringBuilder tmp) {
        if (tmp.length() == digits.length()) {
            list.add(tmp.toString());
        }
        if (tmp.length() >= digits.length() || list.size() >= pagesize) {
            return;
        }
        int x = Character.getNumericValue(digits.charAt(level));
        for (int i = index[level]; i < mapping[x].length(); i++) {
            tmp.append(mapping[x].charAt(i));
            if (list.size() < pagesize) {
                dfs(list, index, level + 1, tmp);
            }
            tmp.deleteCharAt(tmp.length() - 1);
        }
        if (index[level] > 0) index[level] = 0;
    }
}


