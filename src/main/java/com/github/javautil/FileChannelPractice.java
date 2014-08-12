package com.github.javautil;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import org.junit.Assert;





public class FileChannelPractice {

	public static void main(String[] args) throws IOException {
		p1();
		fileCopy();
	}
	
	public static void p1() throws IOException {
		Charset charset = Charset.forName("utf-8"); //注意编码问题。
		FileChannel fileChannel = new RandomAccessFile("t", "rw").getChannel();
		fileChannel.truncate(0);
		String string = "hello doctor打法dfa三十三岁三十三岁三十三岁三十三岁三十三岁三十三岁三十三岁三十三岁 ";
		fileChannel.write(charset.encode(string));
		fileChannel.close();
		
		fileChannel = new RandomAccessFile("t", "rw").getChannel();
		ByteBuffer dst = ByteBuffer.allocateDirect(512);
		dst.clear();
		
		StringBuffer stringBuffer = new StringBuffer();
		while (fileChannel.read(dst)!= -1) {
			dst.flip();
		    stringBuffer.append(charset.decode(dst));
			dst.clear();
		}
		
		fileChannel.close();
		Assert.assertEquals(string, stringBuffer.toString());
		
	}
	
	public static void fileCopy() throws IOException {
		StringBuffer stringBuffer = new StringBuffer(512);
		stringBuffer.append(new File("").getCanonicalPath());
		String separator = System.getProperty("file.separator");
		stringBuffer.append(separator).append("src").append(separator).append("main").append(separator);
		stringBuffer.append("java").append(separator);
		stringBuffer.append(FileChannelPractice.class.getName().replace('.', separator.charAt(0)));
		stringBuffer.append(".java");
		
		FileChannel fileChannelR = new RandomAccessFile(stringBuffer.toString(), "rw").getChannel();
		FileChannel fileChannelW = new RandomAccessFile("copy.java", "rw").getChannel();
		ByteBuffer dst = ByteBuffer.allocateDirect(512);
		
		while (fileChannelR.read(dst) != -1) {
			
			dst.flip();
			fileChannelW.write(dst);
			dst.clear();
		}
		
		fileChannelR.close();
		fileChannelW.close();
		
		fileChannelW = new RandomAccessFile("copy.java", "rw").getChannel();
		dst.clear();
		while (fileChannelW.read(dst) !=-1) {
			dst.flip();
			System.out.println(Charset.forName("utf-8").decode(dst));
			dst.clear();
		}
		
		fileChannelW.close();
	}

}
