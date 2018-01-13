package com.commodity.purchase.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 自定义的json解析
 */

public class CommonJSONParser {

    /**
     * 将json字符串解析成map<String,object>
     * @param jsonStr 要解析的json字符串
     * @return 返回的数据对象
     */
	 public Map<String, Object> parse(String jsonStr) {
		        Map<String, Object> result = null; 
		  
		       if (null != jsonStr) { 
		             try { 
		  
		                JSONObject jsonObject = new JSONObject(jsonStr); 
		                 result = parseJSONObject(jsonObject); 
		  
		              } catch (JSONException e) {
		                  e.printStackTrace(); 
		             } 
		          }
		   
		          return result; 
		     }

    /**
     * 判断字的类型，做不同的解析
     * @param inputObject 要解析的数据
     * @return  返回对应的值
     * @throws JSONException 异常
     */
		      public Object parseValue(Object inputObject) throws JSONException {
		          Object outputObject = null; 
		  
		         if (null != inputObject) {
		              if (inputObject instanceof JSONArray) { //集合类型
		                 outputObject = parseJSONArray((JSONArray) inputObject); 
		             } else if (inputObject instanceof JSONObject) { //map对象类型
		                  outputObject = parseJSONObject((JSONObject) inputObject); 
		             } else if (inputObject instanceof String || inputObject instanceof Boolean || inputObject instanceof Integer|| inputObject instanceof Float||inputObject instanceof Double) {//基本类型
		                  outputObject = inputObject; 
		              } 
		   
		          } 
		   
		          return outputObject; 
		      }

    /**
     * 解析集合数据
     * @param jsonArray 要解析的 集合
     * @return  集合对象
     * @throws JSONException
     */
		      public List<Object> parseJSONArray(JSONArray jsonArray) throws JSONException {
		  
		         List<Object> valueList = null; 
		  
		         if (null != jsonArray) { 
		             valueList = new ArrayList<Object>(); 
		   
		              for (int i = 0; i < jsonArray.length(); i++) { 
		                  Object itemObject = jsonArray.get(i); 
		                  if (null != itemObject) { 
		                     valueList.add(parseValue(itemObject)); //先解析填充集合里面的数据
		                 } 
		              } // for (int i = 0; i < jsonArray.length(); i++) 
		          } // if (null != valueStr) 
		  
		         return valueList; 
		      }

    /**
     *  循环解析 外层的数据
     * @param jsonObject 要解析的对象
     * @return 返回的数据对象
     * @throws JSONException 解析错误会抛一个错误
     */
    public Map<String, Object> parseJSONObject(JSONObject jsonObject) throws JSONException {
		   
		         Map<String, Object> valueObject = null; 
		         if (null != jsonObject) { 
		             valueObject = new HashMap<String, Object>(); 
		  
		             Iterator<String> keyIter = jsonObject.keys(); 
		              while (keyIter.hasNext()) { 
		                  String keyStr = keyIter.next(); 
		                  Object itemObject = jsonObject.opt(keyStr); 
		                 if (null != itemObject) { 
		                     valueObject.put(keyStr, parseValue(itemObject)); //解析数据对应的值，并将它填充
		                  } // if (null != itemValueStr) 
		   
		              } // while (keyIter.hasNext()) 
		          } // if (null != valueStr) 
		   
		          return valueObject; 
		      } 
		  } 

