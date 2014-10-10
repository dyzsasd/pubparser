package com.multiposting.pubparser.test;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.*;

/**
 * Created by S-ZHANG on 07/10/2014.
 */
public class test {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\s-zhang\\workspace\\pubparser\\src\\main\\java\\com\\multiposting\\pubparser\\test\\temp"));
        String line,newline;
        line = br.readLine();
        String res="";
        while((newline=line)!=null){
            line=br.readLine();
            System.out.print("    <action name=\"" + newline+
                    "\" cred=\"\">\n" +
                    "        <fs>\n" +
                    "              <move source='${nameNode}/group/pisquare/usecase/transcoCRDBtemp/DER/output/");
            System.out.print(newline);
            System.out.print("/part-r-00000' target='${nameNode}/group/pisquare/raw/crdb/");
            System.out.print(newline);
            System.out.print(".csv'/>\n");
            System.out.print("        </fs>\n" +"        <ok to=\"" + (line==null?"end":line) + "\"/>\n" +"        <error to=\"kill\"/>\n" +"    </action>\n");
        }
        System.out.println(res);

        final DateTimeFormatter hiveDateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

        final DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyyMMdd HHmmss");

        dateFormatter.parseDateTime("20140104");
    }
}
