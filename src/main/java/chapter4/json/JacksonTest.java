package chapter4.json;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 采用 Jackson 来处理 json
 * @Auth: chunlei.wang
 * @Date: 2019/09/9
 */
public class JacksonTest {
    public static void main(String[] args) throws IOException {
        testJsonObject();
        System.out.println("======分割线=====");
        testJsonFile();
    }

    public static void testJsonObject() throws IOException {
        ObjectMapper om = new ObjectMapper();
        Person p = new Person();
        p.setName("Tom");
        p.setAge(20);
        p.setScores(Arrays.asList(60, 70, 80));

        // 将对象解析为 json 字符串
        String jsonStr = om.writeValueAsString(p);
        System.out.println(jsonStr);
        // {"name":"Tom","age":20,"scores":[60,70,80]}

        // 从 json 字符串重构为 JsonNode 对象
        JsonNode node = om.readTree(jsonStr);
        System.out.println(node.get("name").asText()); // Tom
        System.out.println(node.get("age").asText()); // 20
        System.out.println(node.get("scores")); // [60,70,80]
    }

    public static void testJsonFile() throws IOException {
        ObjectMapper om = new ObjectMapper();

        // 从 json 文件中加载，并重构 java 对象
        File jsonFile = new File("book2.json");
        List<Book> books = om.readValue(jsonFile, new TypeReference<List<Book>>(){});
        for (Book book : books) {
            System.out.println(book);
        }
        /**
         * Book{category='COOKING', author='Giada De Laurentis', title='Everyday Italian', price=30.0}
         * Book{category='CHILDREN', author='J K. Rowling', title='Harry Potter', price=29.99}
         * Book{category='WEB', author='Erik T. Ray', title='Learning URL', price=30.0}
         */
    }
}
