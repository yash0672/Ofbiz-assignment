public static Map<String, Object> jsonReader(DispatchContext dctx, Map<String, Object> context) {
  
  try {
         Delegator delegator = dctx.getDelegator();
         LocalDispatcher dispatcher = dctx.getDispatcher();
         BufferedReader input = new BufferedReader(new FileReader("/home/yash/Downloads/Assignment.json"));
         JSON jsonObject = JSON.from(input);
         JSONToMap jsonMap = new JSONToMap();
         Map<String, Object> userMap = jsonMap.convert(jsonObject);
         userMap.put("userLogin",(GenericValue) context.get("userLogin"));
         Map<String, Object> map = new HashMap<>();
         map.put("userLogin", context.get("userLogin"));
         map.put("firstName", userMap.get("firstName"));
         map.put("lastName",userMap.get("lastName"));
         Map<String, Object> result1 = dispatcher.runSync("createPerson", map);
         map.put("roleTypeId",userMap.get("roleTypeId"));
         map.put("partyId",result1.get("partyId"));
         map.put("toName",userMap.get("toName"));
         map.put("address1",userMap.get("address1"));
         map.put("city",userMap.get("city"));
         map.put("postalCode",userMap.get("postalCode"));
         map.put("countryGeoId",userMap.get("countryGeoId"));
         map.put("stateProvinceGeoId",userMap.get("stateProvinceGeoId"));
         Map<String, Object> result2 = dispatcher.runSync("createPartyPostalAddress", map);
       }
       
       catch (Exception e)
       {
          Debug.log("json reader");
       }
       
       return ServiceUtil.returnSuccess();
        }
