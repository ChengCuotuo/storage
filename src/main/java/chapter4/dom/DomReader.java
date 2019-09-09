package chapter4;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * @Auth: chunlei.wang
 * @Date: 2019/09/09
 * @Desc:  Dom 解析 xml 文件
 */
public class DomReader {
    public static void main(String[] args) {
        recursiveTraverse();
        System.out.println("=============== 分割线 =================");
        trverseBySearch(); // 根据名称进行搜索
    }

    /**
     * xml 文件的格式要很清楚，在这里就是有三层结构，需要三层循环
     */
    public static void recursiveTraverse() {
        try {
            // 采用 Dom 解析 xml 文件
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse("user.xml");

            // 获取所有的一级子节点
            NodeList userLists = document.getChildNodes();
            System.out.println(userLists.getLength());  // 1

            for (int i = 0; i < userLists.getLength(); i++) {
                Node users = userLists.item(i);
                // 获取耳机子节点 user 的列表
                NodeList userList = users.getChildNodes();
                // 空白也算作是一个子元素
                System.out.println("==" + userList.getLength()); // 9
                for (int j = 0; j < userList.getLength(); j++) {
                    Node user = userList.item(j);
                    // 排除 空白 元素
                    if (user.getNodeType() == Node.ELEMENT_NODE) {
                        NodeList metaList = user.getChildNodes();
                        System.out.println("===" + metaList.getLength()); // 7

                        for (int k = 0; k < metaList.getLength(); k++) {
                            Node meta = metaList.item(k);
                            if (meta.getNodeType() == Node.ELEMENT_NODE) {
                                System.out.println(metaList.item(k).getNodeName() + ": " +
                                        metaList.item(k).getTextContent());
                            }
                        }
                        System.out.println();
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过直接查找元素的名称来查找元素，忽略元素树
     */
    public static void trverseBySearch() {
        try {
            // 采用 Dom 解析 xml 文件
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse("user.xml");

            // 获取根节点元素
            Element rootElement = document.getDocumentElement();
            // 获取所有的 name 节点
            NodeList nodeList = rootElement.getElementsByTagName("name");
            if (nodeList != null) {
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);
                    System.out.println(element.getNodeName() + "=" + element.getTextContent());
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
