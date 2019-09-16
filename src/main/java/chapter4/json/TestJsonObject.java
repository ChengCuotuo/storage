package chapter4.json;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auth: chunlei.wang
 * @Date: 2019/09/09
 * @Desc:  采用 org.json 包来解析 JSON
 */
public class TestJsonObject {
    public static void main(String[] args) {
        testJsonObject();
        System.out.println("======分割线=====");
        testJsonFile();
    }

    // 生成 JSON 数据格式
    public static void testJsonObject() {
        // 构造对象
        Person p = new Person();
        p.setName("Tom");
        p.setAge(20);
        p.setScores(Arrays.asList(60, 70, 80));

        // 构造 JSONObject 对象
        JSONObject object = new JSONObject();
        // String
        object.put("name", p.getName());
        // int
        object.put("age", p.getAge());
        // array
        object.put("scores", p.getScores());

        System.out.println(object);

        System.out.println("name: " + object.getString("name"));
        System.out.println("age: " + object.getInt("age"));
        System.out.println("scores: " + object.getJSONArray("scores"));

        /**
         * {"scores":[60,70,80],"name":"Tom","age":20}
         * name: Tom
         * age: 20
         * scores: [60,70,80]
         */
    }

    // 解析 JSON 文件
    public static void testJsonFile() {
        File file = new File("book.json");
        try (FileReader reader = new FileReader(file)) {
            int fileLen = (int)file.length();
            char[] chars = new char[fileLen];
            reader.read(chars);
            String s = String.valueOf(chars);
            JSONObject jsonObject = new JSONObject(s);

            // 开始解析 JSONObject 对象
            JSONArray books = jsonObject.getJSONArray("books");
            List<Book> bookList = new ArrayList<>();
            for (Object book : books) {
                JSONObject bookObject = (JSONObject)book;
                Book book1 = new chapter4.json.Book();
                book1.setAuthor(bookObject.getString("author"));
                book1.setCategory(bookObject.getString("category"));
                book1.setTitle(bookObject.getString("title"));
                book1.setPrice(bookObject.getInt("price"));
                bookList.add(book1);
            }

            for (Book book : bookList) {
                System.out.println(book);
            }
            /**
             * Book{category='COOKING', author='Giada De Laurentis', title='Everyday Italian', price=30}
             * Book{category='CHILDREN', author='J K. Rowling', title='Harry Potter', price=29}
             * Book{category='WEB', author='Erik T. Ray', title='Learning URL', price=30}
             */

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
