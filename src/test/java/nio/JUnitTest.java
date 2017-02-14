package nio;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class JUnitTest {

	public void test() {
		try {
			FileInputStream is = new FileInputStream(new File("D:/搜狗高速下载/navicat注册机/使用说明.txt"));
			System.out.println(is.available());
			is.getChannel();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
