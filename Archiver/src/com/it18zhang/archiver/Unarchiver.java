package com.it18zhang.archiver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: super1peng
 * @Date: 2018/12/3 下午11:39
 * @Version 1.0
 */
public class Unarchiver {

    public static void main(String[] args) throws Exception{

        List<FileBean> files = new ArrayList<FileBean>();
        FileInputStream fis = new FileInputStream("/Users/lxp/eclipse-workspace/Archiver/source/arch/result.xar");

        FileBean fileBean = null;

        while(( fileBean = readNextFile(fis)) != null){
            files.add(fileBean);
        }
        // 关闭流
        fis.close();
        FileOutputStream fos = null;

        for(FileBean fb : files){
            fos = new FileOutputStream("/Users/lxp/eclipse-workspace/Archiver/source/unarch/" + fb.getFileName());
            fos.write(fb.getFileContent());
            fos.close();
        }
    }

    public static FileBean readNextFile(FileInputStream fis) throws Exception{

        byte[] bytes4 = new byte[4];

        // 读取4个字节
        int res = fis.read(bytes4);

        if(res == -1){
            return null ;
        }

        // 文件名长度
        int fnameLen = Util.bytes2Int(bytes4);

        // 文件名数组
        byte[] fileNameBytes = new byte[fnameLen];
        fis.read(fileNameBytes);

        // 得到文件名
        String fname = new String(fileNameBytes);

        // 再读取4个字节，作为文件内容的长度
        fis.read(bytes4);
        int fileContLen = Util.bytes2Int(bytes4);

        // 读取文件内容
        byte[] fileContBytes = new byte[fileContLen];
        fis.read(fileContBytes);
        return new FileBean(fname, fileContBytes);

    }
}