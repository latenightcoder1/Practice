package com.work.practice;

import com.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.yaml.snakeyaml.Yaml;

public class PropertiesReading {

  public static void main(String[] args) {
    Map<String, String> eventAdditionalDetails = readYmlFile();
    Set<String> internalEvents=getInternalEvents();
    Map<String,String> eventsCategoryMap=getEventsCategoryMap();
    File directory = new File("C:\\Users\\suraj kumar\\Downloads\\properties");
    File[] files = directory.listFiles();
    Map<String, Map<String, String>> eventsDetails = new HashMap<>();
    for (File file : files) {
      List<String> allLines = readAllLines(file);
      String serviceName = file.getName().split("_")[0];
      Map<String, List<String>> eventsRawDetails = allLines.stream()
          .filter(line -> line.startsWith("event.config.types."))
          .map(line -> line.substring(19)).collect(Collectors.groupingBy(a -> a.split("\\.")[0]));
      for (Map.Entry<String, List<String>> entry : eventsRawDetails.entrySet()) {
        Map<String, String> map = entry.getValue().stream().map(a -> a.split("="))
            .collect(Collectors.toMap(a -> a[0].split("\\.")[1], a -> a[1]));
        map.put("serviceName", serviceName);
        map.put("additionalDetails", eventAdditionalDetails.get(map.get("name")));
        map.put("category",eventsCategoryMap.getOrDefault(map.get("name"),"OTHER"));
        map.put("isInternal",String.valueOf(internalEvents.contains(map.get("name"))));
        eventsDetails.put(entry.getKey(), map);
      }
    }

    writeToCsv(eventsDetails);
  }

  private static Map<String, String> getEventsCategoryMap() {
    File file = new File("C:\\Users\\suraj kumar\\Downloads\\category.properties");
    List<String> allLines = readAllLines(file);
    Map<String,String> eventsCategoryDetails=new HashMap<>();
    for(String s:allLines){
      String[] tokens=s.split("=");
      String[] events=tokens[1].split(",");
      for(String event:events){
        eventsCategoryDetails.put(event,tokens[0].split("\\.")[4]);
      }
    }
return eventsCategoryDetails;
  }

  private static Set<String> getInternalEvents() {
    Set<String> internalEvents= new HashSet<>(Arrays.asList(("events.query.visibility.internal=DEVICE_REGISTRATION_STOPPED,DEVICE_ENDPOINT_NOT_REACHABLE,"
        + "DIALOUT_SUCCESS,DIALOUT_FAILURE,SNAPSHOT_REQUEST_COMPLETED,DEVICE_SOFTWARE_DOWNLOAD_INITIATED,"
        + "DEVICE_SOFTWARE_DOWNLOAD_SUCCESS,DEVICE_OPENVPN_TUNNEL_CONNECT,DEVICE_OPENVPN_TUNNEL_DISCONNECT,"
        + "DEVICE_EXPIRED_CERT_NOT_WHITELISTED,DEVICE_WITH_VALID_CERT_WHITELISTED,DEVICE_EXPIRED_CERT_WHITELISTED,"
        + "SOFTWARE_UPLOAD_SUCCESS,SOFTWARE_UPLOAD_FAILURE,SOFTWARE_REMOVAL_SUCCESS,SOFTWARE_REMOVAL_FAILURE,RADIO_PARAMETERS_SET_FAILED,"
        + "SET_CONFIG_SKIPPED").split(",")));
    return internalEvents;
  }

  private static Map<String, String> readYmlFile() {
    String filename = "C:\\Users\\suraj kumar\\Downloads\\template.yml";
    try (InputStream inputStream = new FileInputStream(filename)) {
      Yaml yaml = new Yaml();
      List<Map<String, Object>> data = ((Map<String, Map<String, Map<String, List<Map<String, Object>>>>>) yaml.load(
          inputStream)).get("events").get("details").get("templates");
      Map<String, String> additionalData = data.stream().collect(
          Collectors.toMap(a -> (String) a.get("eventName"),
              a -> (String) ((Map<String, String>) ((List<Object>) a.get("additionalDetails")).get(
                  0)).get("templateString")));
      return additionalData;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static void writeToCsv(Map<String, Map<String, String>> values) {
    String[] header = {"displayName", "description", "additionalDetails","category","isInternal"};

    try (CSVWriter writer = new CSVWriter(new FileWriter("C:\\Users\\suraj kumar\\Downloads\\events.csv"))) {
      writer.writeNext(header);
      for (Map.Entry<String, Map<String, String>> entry : values.entrySet()) {
        Map<String, String> row = entry.getValue();
        String[] rowData = {row.get("displayName"), row.get("description"),
            row.get("additionalDetails"),row.get("category"),row.get("isInternal")};
        writer.writeNext(rowData);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private static List<String> readAllLines(File file) {
    List<String> lines = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = br.readLine()) != null) {
        lines.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return lines;
  }

}
