package com.vypeensoft.util;

import java.io.*;
import java.util.*;

public class MapKeyValueArray  {
    private Map<String,List<String>> map = new HashMap<String,List<String>>();

	/******************************************************************************************/
	public MapKeyValueArray() {
	}
	/******************************************************************************************/
    public void add(String key, String value) {
      if(map.containsKey(key)) {
          map.get(key).add(value);
      } else {
        List<String> list = new ArrayList<String>();
        list.add(value);
        map.put(key, list);
      }
    }
	/******************************************************************************************/
    public List<String> getMultiValueKeys(int count) {
        List<String> returnKeys = new ArrayList<String>();
        Set<String> keys = map.keySet();
        Iterator<String> iter = keys.iterator();
        while(iter.hasNext()) {
          String key = iter.next();
          List<String> list = map.get(key);
          if(list.size() >= count ) {
              returnKeys.add(key);
          }
        }
        return returnKeys;
    }
	/******************************************************************************************/

	/******************************************************************************************/

	/******************************************************************************************/
}

