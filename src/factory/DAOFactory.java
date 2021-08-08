package factory;

import util.ConfigUtil;

public class DAOFactory {
//	type:接口类型
	public static Object getInstance(String type) {
		Object obj = null;
//		根据接口的名称获取实现类路径
		String className = ConfigUtil.getValue(type);
//		使用反射的技术创建DAO对象
		try {
			obj = Class.forName(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

}
