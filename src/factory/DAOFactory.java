package factory;

import util.ConfigUtil;

public class DAOFactory {
//	type:�ӿ�����
	public static Object getInstance(String type) {
		Object obj = null;
//		���ݽӿڵ����ƻ�ȡʵ����·��
		String className = ConfigUtil.getValue(type);
//		ʹ�÷���ļ�������DAO����
		try {
			obj = Class.forName(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

}
