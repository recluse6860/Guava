package com.paic.mercury.utils;

import java.util.Map;

import com.paic.mercury.utils.ParseXml;

public class Global {
	
	public static Map<String, String> global;
	
	static{
		ParseXml px = new ParseXml("src/test/resources/test-data/global.xml");
		global = px.getChildrenInfoByElement(px.getElementObject("/*"));
	}
	
}
