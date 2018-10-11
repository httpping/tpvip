//import com.tp.api.Application;
//import com.tp.api.entity.TbString;
//import com.tp.api.service.TbStringService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.w3c.dom.*;
//import org.xml.sax.SAXException;
//import org.xmlunit.util.Nodes;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import java.io.IOException;
//
//
///**
// *
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = Application.class)
//public class LangGenTest {
//
//
//    @Autowired
//    TbStringService tbStringService;
//
//    @Test
//    public void test1() throws ParserConfigurationException, IOException, SAXException {
//        System.out.print("sss");
//        DocumentBuilderFactory docbf  = DocumentBuilderFactory.newInstance();
//        DocumentBuilder docb = docbf.newDocumentBuilder();
//        Document doc = docb.parse("D:/zf-string/values.xml");
//
//        NodeList nodes = doc.getChildNodes();
//
//        int length = nodes.getLength();
//
//        Element element =  doc.getDocumentElement();
//        Node first = element.getFirstChild();
//        nodes = element.getChildNodes();
//        System.out.println("sss" + length + " " + nodes.item(0).getNodeName());
//        parseXml(doc,null);
//    }
//
//
//    @Test
//    public void test2() throws ParserConfigurationException, IOException, SAXException {
//        DocumentBuilderFactory docbf  = DocumentBuilderFactory.newInstance();
//        DocumentBuilder docb = docbf.newDocumentBuilder();
//        Document doc = docb.parse("D:/zf-string/values-ar.xml");
//        parseXml(doc,"ar");
//    }
//
//
//
//
//    public void parseXml(Document document,String lang){
//
//        NodeList strings = document.getElementsByTagName("string");
//        System.out.println(":" +strings.getLength() );
//
//        for (int i =0;i<strings.getLength();i++){
//            Element node = (Element) strings.item(i);
//            System.out.println(node.toString());
//            if (node != null && node.getNodeName().equals("string")){
//                Node  name = node.getAttributes().getNamedItem("name");
//                String key = name.getNodeValue();
//                String value = node.getTextContent();
//                System.out.println(key + ":" +value + " " + node.getFirstChild().getNodeType());
//
//
//                //元素 cdata
//                if (node.getFirstChild().getNodeType() == 1){
//                    value = "<Data><![CDATA[" + value + "]]></Data>";
//                }
//
//
//                TbString tbString = new TbString();
//                tbString.setName(key);
//                if ("ar".equals(lang)){
//                    tbString.setValueAr(value);
//                }else {
//                    tbString.setValueEn(value);
//                }
//                tbStringService.saveOrUpdate(tbString);
//            }
//        }
//    }
//
//
//
//
//
//}
