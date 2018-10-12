//import com.tp.api.Application;
//import com.tp.api.entity.TbString;
//import com.tp.api.entity.enums.LangEnum;
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
//import java.util.LinkedList;
//import java.util.List;
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
//    public static final String rootPath ;
//
//
//    @Autowired
//    TbStringService tbStringService;
//
//    @Test
//    public void en() throws ParserConfigurationException, IOException, SAXException {
//        System.out.print("sss");
//        genData(null);
//    }
//
//    @Test
//    public void ar() throws ParserConfigurationException, IOException, SAXException {
//       genData(LangEnum.DE);
//    }
//
//    @Test
//    public void de() throws ParserConfigurationException, IOException, SAXException {
//        genData(LangEnum.AR);
//    }
//    @Test
//    public void fr() throws ParserConfigurationException, IOException, SAXException {
//        genData(LangEnum.FR);
//    }
//
//    @Test
//    public void es() throws ParserConfigurationException, IOException, SAXException {
//        genData(LangEnum.ES);
//    }
//
//
//    public void genData(LangEnum lang) throws ParserConfigurationException, IOException, SAXException {
//
//
//        DocumentBuilderFactory docbf  = DocumentBuilderFactory.newInstance();
//        DocumentBuilder docb = docbf.newDocumentBuilder();
//
//        String path;
//
//        if (lang == null){
//            path = "values/values.xml";
//        }else {
//            path = "values-"+lang.getValue().toString()+"/values-"+lang.getValue().toString()+".xml";
//        }
//        Document doc = docb.parse(rootPath+path);
//
//        NodeList nodes = doc.getChildNodes();
//
//        int length = nodes.getLength();
//
//        Element element =  doc.getDocumentElement();
//        System.out.println("sss" + length + " " + nodes.item(0).getNodeName());
//        parseXml(doc,lang);
//
//    }
//
//
//
//    public void parseXml(Document document,LangEnum lang){
//
//        NodeList strings = document.getElementsByTagName("string");
//        System.out.println(":" +strings.getLength() );
//
//
//        List<TbString> tbStrings = new LinkedList<>();
//
//        for (int i =0;i<strings.getLength();i++){
//            Element node = (Element) strings.item(i);
//            System.out.println(node.toString());
//            if (node != null && node.getFirstChild() !=null && node.getNodeName().equals("string")){
//                Node  name = node.getAttributes().getNamedItem("name");
//                String key = name.getNodeValue();
//                String value = node.getTextContent();
////                System.out.println(key + ":" +value + " " + node.getFirstChild().getNodeType());
//
//                //元素 cdata
//                if (node.getFirstChild().getNodeType() == 1){
//                    value = "<Data><![CDATA[" + value + "]]></Data>";
//                }
//
//
//                TbString tbString = new TbString();
//                tbString.setName(key);
//                if (lang == LangEnum.AR){
//                    tbString.setValueAr(value);
//                }if (lang == LangEnum.DE){
//                    tbString.setValueDe(value);
//                }if (lang == LangEnum.FR){
//                    tbString.setValueFr(value);
//                }if (lang == LangEnum.ES){
//                    tbString.setValueEs(value);
//                }else {
//                    tbString.setValueEn(value);
//                }
//
//                tbStrings.add(tbString);
//            }
//
//
//        }
//
//        tbStringService.saveOrUpdateList(tbStrings);
//
//    }
//
//
//
//
//
//}
