package com.it18zhang.archiver;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 *  Java 文件归档器制作
 */


public class Archiver {

    public static void main(String[] args) throws Exception{

        FileOutputStream fos = new FileOutputStream("/Users/lxp/eclipse-workspace/Archiver/source/arch/result.xar");  // 文件路径
        fos.write(addFile("/Users/lxp/eclipse-workspace/Archiver/source/a.png"));
        fos.write(addFile("/Users/lxp/eclipse-workspace/Archiver/source/b.csv"));
        fos.write(addFile("/Users/lxp/eclipse-workspace/Archiver/source/c.txt"));
        // 关闭文件流
        fos.close();
        System.out.println("Over");
    }

    /**
     *  向归档中添加文件
     */

    public static byte[] addFile(String path) throws Exception {
        //文件
        File f = new File(path);
        //文件名
        String fname = f.getName();
        //文件名数组
        byte[] fnameBytes = fname.getBytes();
        //文件内容长度
        int len = (int) f.length();

        //计算总长度
        int total = 4 + fnameBytes.length + 4 + len;

        //初始化总数组
        byte[] bytes = new byte[total];

        //1.写入文件名长度
        byte[] fnameLenArr = Util.int2Bytes(fnameBytes.length);
        System.arraycopy(fnameLenArr, 0, bytes, 0, 4);

        //2.写入文件名本身
        System.arraycopy(fnameBytes, 0, bytes, 4, fnameBytes.length);

        //3.写入文件内容长度
        byte[] fcontentLenArr = Util.int2Bytes(len);
        System.arraycopy(fcontentLenArr, 0, bytes, 4 + fnameBytes.length, 4);

        //4.写入文件内容
        //读取文件内容到数组中
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream fis = new FileInputStream(f);
        byte[] buf = new byte[1024];
        int len0 = 0;
        while (((len0 = fis.read(buf)) != -1)) {
            baos.write(buf, 0, len0);
        }
        fis.close();
        //得到文件内容
        byte[] fileContentArr = baos.toByteArray();
        System.arraycopy(fileContentArr, 0, bytes, 4 + fnameBytes.length + 4, fileContentArr.length);
        return bytes;

    }
}
