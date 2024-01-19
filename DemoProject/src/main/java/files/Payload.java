package files;

public class Payload {
    public static String AddPlace(){
        return "{\n" +
            "    \"location\": {\n" +
            "        \"lat\": -38.383494,\n" +
            "        \"lng\": 33.427362\n" +
            "    },\n" +
            "    \"accuracy\": 50,\n" +
            "    \"name\": \"Frontline house\",\n" +
            "    \"phone_number\": \"(+91) 983 893 3937\",\n" +
            "    \"address\": \"29, side layout, cohen 09\",\n" +
            "    \"types\": [\n" +
            "        \"shoe park\",\n" +
            "        \"shop\"\n" +
            "    ],\n" +
            "    \"website\": \"http://google.com\",\n" +
            "    \"language\": \"French-IN\"\n" +
            "}";
    }

    public static String coursePrice(){
        return "{\n" +
            "    \"dashboard\": {\n" +
            "        \"purchaseAmount\": 2080,\n" +
            "        \"website\": \"rahulshettyacademy.com\"\n" +
            "    },\n" +
            "    \"courses\": [\n" +
            "        {\n" +
            "            \"title\": \"Selenium Python\",\n" +
            "            \"price\": 50,\n" +
            "            \"copies\": 4\n" +
            "        },\n" +
            "        {\n" +
            "            \"title\": \"Cypress TS\",\n" +
            "            \"price\": 75,\n" +
            "            \"copies\": 5\n" +
            "        },\n" +
            "        {\n" +
            "            \"title\": \"RPA\",\n" +
            "            \"price\": 45,\n" +
            "            \"copies\": 10\n" +
            "        },\n" +
            "        {\n" +
            "            \"title\": \"Docker\",\n" +
            "            \"price\": 55,\n" +
            "            \"copies\": 11\n" +
            "        },\n" +
            "        {\n" +
            "            \"title\": \"Java\",\n" +
            "            \"price\": 90,\n" +
            "            \"copies\": 5\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    }

    public static String addBook(String isbn, String aisle){
        String payload = "{\n" +
            "\n" +
            "\"name\":\"Learn Appium Automation with Java\",\n" +
            "\"isbn\":\""+ isbn + "\",\n" +
            "\"aisle\":\""+ aisle + "\",\n" +
            "\"author\":\"John foe\"\n" +
            "}\n";
        return payload;
    }
}
