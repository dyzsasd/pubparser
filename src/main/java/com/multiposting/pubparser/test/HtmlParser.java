package com.multiposting.pubparser.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;

/**
 * Created by S-ZHANG on 06/10/2014.
 */
public class HtmlParser {
    private HtmlNode htmlTree;

    private HtmlParser(Element element){
        htmlTree = new HtmlNode(element,null);
    }

    private static HtmlParser getInstance(String filePath){
        try {
            Document doc= Jsoup.parse(new File(filePath), "UTF-8");
            return new HtmlParser(doc.body());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void printStructures(){
        this.htmlTree.print("");
    }

    public static void main(String[] args){
        HtmlParser htmlParser = HtmlParser.getInstance("C:\\Users\\s-zhang\\Desktop\\test2.htm");
        System.out.println("tree has been created");
        htmlParser.printStructures();
    }
}
