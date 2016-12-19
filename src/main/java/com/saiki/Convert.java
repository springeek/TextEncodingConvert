package com.saiki;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
import org.mozilla.universalchardet.UniversalDetector;

import java.io.*;

public class Convert {
    public static void main(String[] args) throws IOException {
        String source = "/disk/data2/baidu/txt/utf8";
        String target = "/disk/data2/baidu/txt/md";
        File[] files = new File(source).listFiles();

        if (files != null) {
            for (File f : files) {
                String filename = f.getName()
                        .replace(".txt", "")
                        .replace("txt", "")
                        .replace("作者", "")
                        .replace("：", "")
                        .replace("·", "")
                        .replace("，", "")
                        .replace("+", "")
                        .replace("-", "")
                        .replace("《", "")
                        .replace("》", "")
                        .replace("【美】", "")
                        .replace("【", "")
                        .replace("】", "")
                        .replace("【TXT】", "")
                        .replace("精校版", "")
                        .replace("全本", "")
                        .replace("（", "")
                        .replace("）", "")
                        .replace("(", "")
                        .replace(" ", "")
                        .replace(")", "");
//                if (filename.contains("网游"))System.out.println("todo:"+filename+" "+getEncoding(f)+"  "+f.getName());
                System.out.println("todo:" + filename);
//                f.renameTo(new File(target + "/" + filename + ".txt"));
                BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f), getEncoding(f)));
                OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(target + "/" + filename));
                String line;
                while ((line = in.readLine()) != null) {
                    if (line.length() > 0) {
                        osw.write(line.trim());
                        osw.write("\r\n");
                    }
                }
                System.out.println("success:" + filename);
                osw.close();
                in.close();
            }
        }
    }

    private static String getEncoding(File f) throws IOException {
        long count = 0;
        int n, EOF = -1;
        byte[] buffer = new byte[4096];
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputStream input;

        input = new FileInputStream(f);

        while ((EOF != (n = input.read(buffer))) && (count <= Integer.MAX_VALUE)) {
            output.write(buffer, 0, n);
            count += n;
        }

        byte[] data = output.toByteArray();
/*        UniversalDetector universalDetector = new UniversalDetector(null);
        universalDetector.handleData(data, 0, data.length);
        universalDetector.dataEnd();
        return universalDetector.getDetectedCharset();*/


        // * ICU4j
        CharsetDetector charsetDetector = new CharsetDetector();
        charsetDetector.setText(data);
        charsetDetector.enableInputFilter(true);
        CharsetMatch cm = charsetDetector.detect();
        return cm.getName();
    }


}
