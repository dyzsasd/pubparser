package com.multiposting.pubparser.test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class JsoupStudy {
    private static List<Element> divList=new ArrayList<Element>();
    public static void main(String[] args) {
        JsoupStudy js = new JsoupStudy();
        js.parseUrl();
    }
    public void parseUrl() {
        try {
            Document doc=Jsoup.parse(new File("C:\\Users\\s-zhang\\Desktop\\test.html"),"UTF-8");
            wash(doc);
            washAll(doc);
            //System.out.println(doc);
            Elements bodychildren=doc.body().children();
            for(Element element : bodychildren)
            {

                if(isDiv(element))
                {
                    addList(element);
                }
            }
            Iterator<Element> it=divList.iterator();
            while(it.hasNext())
            {
                int linkcount=0;
                int contentcount=0;
                double LC=0;
                Element candidate=it.next();
                contentcount=candidate.text().length();  //统计该候选快的文本字符个数
                Elements links=candidate.select("a[href]");
                linkcount=links.size();                  //统计该候选快的链接数
                if(contentcount>50)                      //经过处理后清理后，候选快中任然可能有干扰项，最后一次处理掉噪声。
                {
                    LC=(double)linkcount/(double)contentcount;
                    if(LC<0.03)
                    {
                        System.out.println(candidate.text());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addList(Element element)
    {
        Elements children=element.children();
        if(children==null)
        {
            divList.add(element);         //该元素没有子元素，则添加该元素到列表
            return;
        }
        else
        {
            boolean havediv=false;
            for(Element child:children)
            {
                if(isDiv(child))  //该元素子元素为div元素，则递归调用函数
                {
                    havediv=true;
                    addList(child);
                }
            }
            if(!havediv)
                divList.add(element);     //如果有子元素，且子元素全部为非div标签的话，也添加该元素到列表。
        }
        return;
    }
    public boolean isDiv(Element element)   //判断元素对应标签是不是分块标签
    {
        if(element.tagName().equals("div"))
            return true;
        else
            return false;
    }

    public void washAll(Document doc)
    {
        Elements es=doc.getAllElements();
        for(Element element : es)
        {

            if(isDiv(element))     //分块元素，包含文本太少则不太可能为正文，删除
            {
                //if(element.text().length()<50)
                  //  element.remove();
            }
            else if(element.text().equals(""))  //删除所有文本节点为空的元素
            {
                element.remove();
            }
        }
    }
    public void wash(Document doc)
    {
        Elements script=doc.select("script");
        for(Element element : script)
        {
            element.remove();
        }
        Elements form=doc.select("form");
        for(Element element : form)
        {
            element.remove();
        }
        Elements meta=doc.select("meta");
        for(Element element : meta)
        {
            element.remove();
        }
        Elements style=doc.select("style");
        for(Element element : style)
        {
            element.remove();
        }
        Elements iframe=doc.select("iframe");
        for(Element element : iframe)
        {
            element.remove();
        }
        Elements font=doc.select("font");
        for(Element element : font)
        {
            element.remove();
        }
    }
}