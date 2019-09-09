package chapter4;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * @Auth: chunlei.wang
 * @Date: 2019/09/09
 * @Desc:  通过 Dom 向 xml 文件中写入数据
 */
public class DomWriter {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            // 新创建一个 Document 节点
            Document document = db.newDocument();
            if (document != null) {
                Element docx = document.createElement("document");
                Element element = document.createElement("element");
                element.setAttribute("type", "paragraph");
                element.setAttribute("alignment", "left");  // element 增加 2 个属性

                Element object = document.createElement("object");
                object.setAttribute("type", "text");

                Element text = document.createElement("text");
                text.appendChild(document.createTextNode("acdefg"));
                Element bold = document.createElement("bold");
                bold.appendChild(document.createTextNode("true"));

                // 完成树形结构
                object.appendChild(text);
                object.appendChild(bold);
                element.appendChild(object);
                docx.appendChild(element);
                document.appendChild(docx);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(document);

                // 定义目标文件
                File file = new File("dom_result.xml");
                StreamResult result = new StreamResult(file);

                // 将 xml 内容写入到文件中
                transformer.transform(source, result);
                System.out.println("write xml file successfully.");
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
