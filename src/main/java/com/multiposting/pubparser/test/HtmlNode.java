package com.multiposting.pubparser.test;

import com.google.common.collect.Lists;
import org.apache.commons.collections.list.TreeList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class HtmlNode {
    private Element element;
    private List<HtmlNode> childrenNodes;
    private HtmlNode parentHtmlNode;
    private boolean isLeaf=false;
    private long nbchars;

    public HtmlNode(Element element, HtmlNode parentHtmlNode){
        this.element = element;
        this.parentHtmlNode = parentHtmlNode;
        this.childrenNodes = getChildrenNodes();
        nbchars = element.text().length();
        //System.out.println(childrenNodes==null?"null children "+this.element.tag():"has "+childrenNodes.size()+"children nodes "+this.element.tag());
    }

    public void print(String offset){
        if(isLeaf){
            System.out.println(offset+element.text());
        }
        else{
            System.out.println(offset+element.tag()+":"+element.text());
            for (HtmlNode htmlNode:childrenNodes){
                htmlNode.print(offset+"++");
            }
        }
    }

    private List<HtmlNode> getChildrenNodes() {
        if(element.children()==null) {
            this.isLeaf=true;
            return null;
        }
        else{
            List<HtmlNode> children = Lists.newArrayList();
            for(Element ele:element.children()){
                if(isDiv(ele)) {
                    children.add(new HtmlNode(ele, this));
                }
            }
            if(children.size()==0){
                this.isLeaf=true;
                return null;
            }
            return children;
        }
    }

    private boolean isDiv(Element element)   //判断元素对应标签是不是分块标签
    {
        if(element.tagName().equals("div"))
            return true;
        else
            return false;
    }
}
