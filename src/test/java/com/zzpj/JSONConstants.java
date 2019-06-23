package com.zzpj;

public interface JSONConstants {

    String BOOK = " {\n" +
            "    \"title\": \"test\",\n" +
            "    \"author\": \"test\",\n" +
            "    \"price\": 10.00,\n" +
            "    \"category\": {\n" +
            "    \"id\": 1\n" +
            "    },\n" +
            "    \"isbn\": \"2233\",\n" +
            "    \"numberOfPages\": 100,\n" +
            "    \"description\": \"test\",\n" +
            "    \"publisher\":\n" +
            "{\n" +
            "\"id\": 1\n" +
            "}\n" +
            " }";

    String BOOK2 = "{\n" +
            "    \"title\": \"test2\",\n" +
            "    \"author\": \"test2\",\n" +
            "    \"price\": 20.00,\n" +
            "    \"category\": \n" +
            "    {\n" +
            "    \"id\": 1\n" +
            "    },\n" +
            "    \"isbn\": \"2235\",\n" +
            "    \"numberOfPages\": 100,\n" +
            "    \"description\": \"test2\"\n" +
            " }";

    String CATEGORY = "{\n" +
            "\"name\": \"test\"\n" +
            "}\n";

    String CATEGORY2 = "{\n" +
            "\"name\": \"horror\"\n" +
            "}\n";

    String PAYMENTSTATUS = "{\n" +
            "    \"name\": \"WAITING_FOR_PAYMENT\"\n" +
            "}";

    String ROLE_CLIENT = "{ \n" +
            "\"name\": \"CLIENT\"\n" +
            "}";

    String ROLE_ADMINISTRATOR = "{ \n" +
            "\"name\": \"ADMINISTRATOR\"\n" +
            "}";

    String USER_CLIENT =  "{\n" +
            "\"login\": \"test\",\n" +
            "\"email\": \"test@test.test\",\n" +
            "\"passwordHash\": \"test\",\n" +
            "\"role\": {\n" +
            "\"id\": 1\n" +
            "},\n" +
            "\"userDetails\": {\n" +
            "\t\"street\": \"tets\",\n" +
            "\t\"streetNumber\": \"tets\",\n" +
            "\t\"flatNumber\": \"tets\",\n" +
            "\t\"phoneNumber\": \"tets\",\n" +
            "\t\"city\": \"tets\",\n" +
            "\t\"country\": \"tets\",\n" +
            "\t\"firstName\": \"tets\",\n" +
            "\t\"lastName\": \"tets\"\n" +
            "\t}\n" +
            "}\n";

    String USER_ADMINISTRATOR = "{\n" +
            "\"login\": \"test2\",\n" +
            "\"email\": \"test@test.test2\",\n" +
            "\"passwordHash\": \"test\",\n" +
            "\"role\": {\n" +
            "\"id\": 2\n" +
            "},\n" +
            "\"userDetails\": {\n" +
            "\t\"street\": \"tets\",\n" +
            "\t\"streetNumber\": \"tets\",\n" +
            "\t\"flatNumber\": \"tets\",\n" +
            "\t\"phoneNumber\": \"tets\",\n" +
            "\t\"city\": \"tets\",\n" +
            "\t\"country\": \"tets\",\n" +
            "\t\"firstName\": \"tets\",\n" +
            "\t\"lastName\": \"tets\"\n" +
            "\t}\n" +
            "}\n";

    String PUBLISHER = "{\n" +
            "\"name\": \"test publisher\"\n" +
            "}";
}
