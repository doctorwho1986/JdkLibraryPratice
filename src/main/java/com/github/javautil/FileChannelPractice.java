package com.github.javautil;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Assert;




public class FileChannelPractice {

	public static void main(String[] args) throws IOException {
		p1();
	}
	
	public static void p1() throws IOException {
		FileChannel fileChannel = new RandomAccessFile("t", "rw").getChannel();
		String string = "hello doctor";
		fileChannel.write(ByteBuffer.wrap(string.getBytes()));
		fileChannel.close();
		
		fileChannel = new RandomAccessFile("t", "rw").getChannel();
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		fileChannel.read(byteBuffer);
		fileChannel.close();
		
		byte[] dst = new byte[1024];
		byteBuffer.flip();
		StringBuffer stringBuffer = new StringBuffer();
		while (byteBuffer.hasRemaining()) {
			stringBuffer.append((char)byteBuffer.get());
		}
		
		Assert.assertEquals(string, stringBuffer.toString());
	}

}
