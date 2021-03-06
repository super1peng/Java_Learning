package com.it18zhang.archive.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.junit.Test;


public class TestZip {
	
	/**
	 *  zip 文件压缩
	 * @throws Exception
	 */
	@Test
	public void zip() throws Exception{
		// 文件输出流
		FileOutputStream fos = new FileOutputStream("/Users/lxp/eclipse-workspace/Archiver/source/arch/xxx.zip");
		// 压缩流
		ZipOutputStream zos = new ZipOutputStream(fos);
		
		String[] arr = {
				"/Users/lxp/eclipse-workspace/Archiver/source/a.png",
				"/Users/lxp/eclipse-workspace/Archiver/source/b.csv",
				"/Users/lxp/eclipse-workspace/Archiver/source/c.txt"
		};
		for(String s : arr) {
			addFile(zos, s);
		}
		zos.close();
		fos.close();
		System.out.println("Over");
	}
	
	/**
	 *  循环向zos中添加条目
	 */
	public static void addFile(ZipOutputStream zos, String path) throws Exception{
		 File f = new File(path);
		 zos.putNextEntry(new ZipEntry(f.getName()));
		 FileInputStream fis = new FileInputStream(f);
		 byte[] bytes = new byte[fis.available()];
		 fis.read(bytes);
		 fis.close();
		 
		 zos.write(bytes);
		 zos.closeEntry();
	}
	
	
	/**
	 * 解压缩
	 */
	@Test
	public void unzip() throws Exception{
		//
		FileInputStream fis = new FileInputStream("/Users/lxp/eclipse-workspace/Archiver/source/arch/xxx.zip");
		ZipInputStream zis = new ZipInputStream(fis);
		// 
		ZipEntry entry = null;
		byte[] buf = new byte[1024];
		int len = 0;
		while((entry = zis.getNextEntry()) != null) {
			String name = entry.getName();
			FileOutputStream fos = new FileOutputStream("/Users/lxp/eclipse-workspace/Archiver/source/unzip/" + name);
			while((len = zis.read(buf)) != -1) {
				fos.write(buf, 0, len);
			}
			fos.close();
		}
		zis.close();
		fis.close();
	}
}
