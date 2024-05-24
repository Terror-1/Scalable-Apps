//
//import com.github.javafaker.Faker;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.stream.Stream;
//
//public class Main {
//
//    private static Iterator<Map<String, Object>> usersBuilder() {
//        Faker faker = new Faker();
//        return Stream.generate(() -> {
//            String fname = faker.name().firstName();
//            String lname = faker.name().lastName();
//            Map<String, Object> objectMap = new HashMap<>();
//            objectMap.put("firstName", fname);
//            objectMap.put("lastName", lname);
//            objectMap.put("email",String.format("%s.%s@example.com", fname.toLowerCase(), lname.toLowerCase()));
//            objectMap.put("password", faker.internet().password());
//            objectMap.put("city",faker.address().city());
//            objectMap.put("line1",faker.address().streetAddress());
//            objectMap.put("country",faker.address().country());
//            objectMap.put("postalCode",faker.address().zipCode());
//            return objectMap;
//        }).iterator();
//    }
//    public static int randNum(int min, int max) {
//        return (int) ((Math.random() * (max - min)) + min);
//    }
//    private static Iterator<Map<String,Object>> reviewBuilder(){
//        Faker faker = new Faker();
//        return Stream.generate(() -> {
//            Map<String, Object> objectMap = new HashMap<>();
//            objectMap.put("productId",randNum(1,50));
//            objectMap.put("rating",randNum(0,5));
//            objectMap.put("review", faker.lorem().paragraph());
//
//            return objectMap;
//        }).iterator();
//    }
//
//    public static void main(String[] args) {
//        Iterator<Map<String, Object>> usersFeeder = usersBuilder();
//        Gson gson = new GsonBuilder().create();
//
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users4.json"))) {
//            writer.write("[");
//            for (int i = 0; i < 10000; i++) {
//                if (i > 0) {
//                    writer.write(",\n");
//                }
//                Map<String, Object> map = usersFeeder.next();
//                String json = gson.toJson(map);
//                writer.write(json);
//            }
//            writer.write("]");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
