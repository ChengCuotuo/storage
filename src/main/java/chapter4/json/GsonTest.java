package chapter4.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 采用 Google GSON 来处理 Json
 * @Auth: chunlei.wang
 * @Date: 2019/09/9
 */
public class GsonTest {
    public static void main(String[] args) {
        testJsonObject();
        System.out.println("======分割线=====");
        testJsonFile();
    }

    public static void testJsonObject() {
        // 构造对象
        Person p = new Person();
        p.setName("Tom");
        p.setAge(20);
        p.setScores(Arrays.asList(60, 70, 80));

        // 从 java 对象到 json 字符串
        Gson gson = new Gson();
        String s = gson.toJson(p);
        System.out.println(s);
        //{"name":"Tom","age":20,"scores":[60,70,80]}

        // 从 json 字符串到 java 对象
        Person person = gson.fromJson(s, Person.class);
        System.out.println(person.getName()); // Tom
        System.out.println(person.getAge()); // 20
        System.out.println(person.getScores()); // [60, 70, 80]

        // 调用 gson 的 JsonObject
        // 将整个 json 解析为一颗树
        JsonObject json = gson.toJsonTree(p).getAsJsonObject();
        System.out.println(json.get("name")); // Tom
        System.out.println(json.get("age")); // 20
        System.out.println(json.get("scores")); // [60, 70, 80]
    }

    public static void testJsonFile() {
        Gson gson = new Gson();
        File file = new File("book2.json");
        try (FileReader reader = new FileReader(file)) {
            List<Book> books = gson.fromJson(reader, new TypeToken<List<Book>>(){}.getType());
            for (Book book : books) {
                System.out.println(book);
            }
            /**
             * Book{category='COOKING', author='Giada De Laurentis', title='Everyday Italian', price=30.0}
             * Book{category='CHILDREN', author='J K. Rowling', title='Harry Potter', price=29.99}
             * Book{category='WEB', author='Erik T. Ray', title='Learning URL', price=30.0}
             */
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
