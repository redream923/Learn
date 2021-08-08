package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
	static Properties p = new Properties();
	static {
//		��ȡ�ļ���
		InputStream ins = ConfigUtil.class.getClassLoader()
				.getResourceAsStream("config.properties");
		try {
			p.load(ins);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	����key��ȡvalueֵ
	public static String getValue(String key) {
		return p.getProperty(key);
	}
	
	public static void main(String[] args) {
		System.out.println(getValue("AdminDAO"));
	}
	
	
}
