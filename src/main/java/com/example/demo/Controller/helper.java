package com.example.demo.Controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;


public class helper {

    public static void createResponse(HttpServletResponse response, Collection<String> itemList, int start, int[] count) {
        List<JSONObject> list = new ArrayList<JSONObject>();
        JSONObject obj = new JSONObject();
        obj.put("total", count[0]);
        list.add(obj);
        try {
            for (String item : itemList) {
                obj = new JSONObject();
                obj.put(Integer.toString(start++), item);
                list.add(obj);
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