package com.github.javautil;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.junit.Assert;



public class ByteBufferPractice {

	public static void main(String[] args) throws UnsupportedEncodingException {
		final int capacity = 512;
		ByteBuffer buffer = ByteBuffer.allocate(capacity);//初始化
		Assert.assertEquals(capacity, buffer.capacity()); //容量 == capacity
		Assert.assertEquals(capacity, buffer.limit()); //默认buffer写状态，limit代表可写入容量限制
		Assert.assertEquals(0, buffer.position()); //代表当期要操作的buffer位置，默认0
		
		
		String src = "laljdkj";
		buffer.put(src.getBytes("utf-8")); // 写入src字符串
		Assert.assertEquals(capacity, buffer.capacity()); //容量 == 初始capacity
		Assert.assertEquals(capacity , buffer.limit()); //buffer写状态，limit代表可写入容量限制,当然是capacity容量
		Assert.assertEquals(src.length(), buffer.position()); //代表当期要操作的buffer位置，默认0
		
		
		
		buffer.flip();//设置读状态
		Assert.assertEquals(capacity, buffer.capacity()); //容量 == 初始capacity
		Assert.assertEquals(src.length() , buffer.limit()); //buffer变为读状态，limit代表可读取容量限制,当然是已经写入多少的容量
		Assert.assertEquals(0, buffer.position()); //代表当期要操作的buffer位置，读，默认从0开始
		
		
		
		byte[] dst = new byte[1024];
		buffer.get(dst,0,src.length()); //读取多长度字节
		Assert.assertEquals(capacity, buffer.capacity()); //容量 == 初始capacity
		Assert.assertEquals(src.length() , buffer.limit()); //buffer变为读状态，limit代表可读取容量限制,当然是已经写入多少的容量
		Assert.assertEquals(src.length(), buffer.position()); //代表当期要操作的buffer位置，读取了多长，就位于此位置

		String string = new String(dst).trim(); //读到之后，内容是否相等，这里要trim
		Assert.assertTrue(string.equals(src));
		
		
		buffer.clear(); //恢复初始状态
		Assert.assertEquals(capacity, buffer.capacity()); 
		Assert.assertEquals(capacity , buffer.limit()); 
		Assert.assertEquals(0, buffer.position()); 
		
		
		/////////////////////////////////////////////////////////////////
		
		FloatBuffer floatBuffer = ByteBuffer.allocateDirect(capacity).asFloatBuffer(); // 源码得知asFloatBuffer，容量 >>2,右移操作
		Assert.assertEquals(capacity >> 2, floatBuffer.capacity());
		Assert.assertEquals(capacity >> 2, floatBuffer.limit());
		Assert.assertEquals(0, floatBuffer.position());
		
		
		float[] f = {12F,14F,15F};
		floatBuffer.put(f);
		Assert.assertEquals(capacity >> 2, floatBuffer.capacity());
		Assert.assertEquals(capacity >> 2, floatBuffer.limit());
		Assert.assertEquals(f.length, floatBuffer.position());
		
		
		IntBuffer intBuffer = ByteBuffer.allocate(capacity).asIntBuffer();
		Assert.assertEquals(capacity >> 2, intBuffer.capacity());
		Assert.assertEquals(capacity >> 2, intBuffer.limit());
		Assert.assertEquals(0, intBuffer.position());
		
		
		DoubleBuffer doubleBuffer = ByteBuffer.allocate(capacity).asDoubleBuffer();
		Assert.assertEquals(capacity >> 3, doubleBuffer.capacity());
		Assert.assertEquals(capacity >> 3, doubleBuffer.limit());
		Assert.assertEquals(0, doubleBuffer.position());
	}

}
