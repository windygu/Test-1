package com.yangyang.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class TestRhino {

	public static void main(String[] args) throws Exception {
		Context cx = new Context();
		Scriptable scope = cx.initStandardObjects();
		String script = "";
		File file = new File("javascript.js");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str = "";
		while((str = br.readLine()) != null)
			script += str + "\n";
		System.out.println(script);
		cx.evaluateString(scope, script, "[", 1, null);
		
	}

}
