package com.example.gowheely.bleservice;

import java.util.HashMap;


public class SampleGattAttributes {
  //  public static final String UUID_BATTERY_SERVICE = "0000180f-0000-1000-8000-00805f9b34fb";
   // public static final String UUID_BATTERY_LEVEL_UUID = "00002a19-0000-1000-8000-00805f9b34fb";

    public static final String UUID_CART_INFO_SERVICE = "00000001-710e-4a5b-8d75-3e5b444bc3cf";
    public static final String UUID_CART_INFO = "CART_INFO";

    private static HashMap<String, String> attributes = new HashMap();

    static {
      //  attributes.put(UUID_BATTERY_LEVEL_UUID, "Battery Level");
       // attributes.put(UUID_BATTERY_SERVICE, "Battery Service");
        attributes.put(UUID_CART_INFO_SERVICE,"CART_INFO");
    }

    public static String lookup(String uuid) {
        String name = attributes.get(uuid);
        return name;
    }
}