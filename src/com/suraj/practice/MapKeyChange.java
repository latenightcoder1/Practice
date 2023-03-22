package com.suraj.practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class MapKeyChange {

  public static void main(String[] args) {
    Map<String,Object> map=new HashMap<>();
    map.put("system/dns/servers/server[address=8/8/8/8]/config/address","XYZ");
    map.put("radios/global/config/cell-size","ram");
    Map<String,Object> treeMap=new TreeMap<>();
    for (Entry<String, Object> entry : map.entrySet()) {
      String key = entry.getKey();
      if (key.contains("server[address=")) {
        try {
          key = key.substring(0, key.indexOf("server[address=") + "server[address=".length())
              + key.substring(key.indexOf("server[address="), key.indexOf("]/"))
              .replaceAll("/", ".")
              + key.substring(key.indexOf("]/"));
        } catch (Exception e) {
          /*log.error(
              "key {} couldn't be processed (key will be returned the way it was) to replace / by . with an error. ",
              key, e);*/
        }
      }
      treeMap.put(key.startsWith("/") ? key : "/" + key,
          entry.getValue());

    }
    System.out.println(treeMap);
  }

}
