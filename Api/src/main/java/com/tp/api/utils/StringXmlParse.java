package com.tp.api.utils;

import com.tp.api.entity.TbString;
import com.tp.api.entity.enums.LangEnum;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class StringXmlParse {


    public static List<TbString> genData(LangEnum lang ,String xmlPath) throws ParserConfigurationException, IOException, SAXException {


        DocumentBuilderFactory docbf  = DocumentBuilderFactory.newInstance();
        DocumentBuilder docb = docbf.newDocumentBuilder();

        String path;

        if (lang == null){
            path = "values/values.xml";
        }else {
            path = "values-"+lang.getValue().toString()+"/values-"+lang.getValue().toString()+".xml";
        }
        Document doc = docb.parse(xmlPath);

        NodeList nodes = doc.getChildNodes();

        int length = nodes.getLength();

        Element element =  doc.getDocumentElement();
        System.out.println("sss" + length + " " + nodes.item(0).getNodeName());
       return parseXml(doc,lang);

    }



    public  static List<TbString> parseXml(Document document,LangEnum lang){

        NodeList strings = document.getElementsByTagName("string");
        System.out.println(":" +strings.getLength() );


        List<TbString> tbStrings = new LinkedList<>();

        for (int i =0;i<strings.getLength();i++){
            Element node = (Element) strings.item(i);
            System.out.println(node.toString());
            if (node != null && node.getFirstChild() !=null && node.getNodeName().equals("string")){
                Node name = node.getAttributes().getNamedItem("name");
                String key = name.getNodeValue();
                String value = node.getTextContent();
//                System.out.println(key + ":" +value + " " + node.getFirstChild().getNodeType());

                //元素 cdata
                if (node.getFirstChild().getNodeType() == 1){
                    value = "<Data><![CDATA[" + value + "]]></Data>";
                }


                TbString tbString = new TbString();
                tbString.setName(key);
                if (lang == LangEnum.AR){
                    tbString.setValueAr(value);
                }else if (lang == LangEnum.DE){
                    tbString.setValueDe(value);
                }else if (lang == LangEnum.FR){
                    tbString.setValueFr(value);
                }else if (lang == LangEnum.ES){
                    tbString.setValueEs(value);
                }else {
                    tbString.setValueEn(value);
                }

                tbStrings.add(tbString);
            }


        }

        return  tbStrings;
    }

}
