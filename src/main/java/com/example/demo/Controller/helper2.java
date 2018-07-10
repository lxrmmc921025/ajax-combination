package com.example.demo.Controller;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class helper2 {

    public static void createResponse(HttpServletResponse response, Collection<String> itemList, int start, int end) {
        List<JSONObject> list = new ArrayList<JSONObject>();
        Integer count = 1;
        try {
            for (String item : itemList) {
                if (count > start && count < end) {
                    JSONObject obj = new JSONObject();
                    obj.put(Integer.toString(count), item);
                    list.add(obj);
                }
                count++;
            }
            //System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONArray array = new JSONArray(list);
        try {
            response.setContentType("application/json");
            response.addHeader("Access-Control-Allow-Origin", "*");
            PrintWriter out = response.getWriter();
            out.print(array);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}