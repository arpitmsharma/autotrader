package com.fm.fixconnector;

import com.sun.jdmk.comm.AuthInfo;
import com.sun.jdmk.comm.HtmlAdaptorServer;

public class ClassLoader {

	public static void main(String args[]) {
		ApplicationLauncher appLauncher= new ApplicationLauncher();
		appLauncher.start();
		HtmlAdaptorServer htmlAdaptor = (HtmlAdaptorServer) appLauncher
				.getBeanObj("htmlAdaptor");
		AuthInfo authInfo = new AuthInfo("bhavishya", "canNotHack4");
		htmlAdaptor.addUserAuthenticationInfo(authInfo);
	}
}
