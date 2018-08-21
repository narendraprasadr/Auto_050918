package com.macys.mst.macysnet.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KillProcess {

	private static final String TASKLIST = "tasklist";
	private static final String KILL = "taskkill /F /IM ";

	public static boolean isProcessRunning(String serviceName) throws Exception {

	 Process p = Runtime.getRuntime().exec(TASKLIST);
	 BufferedReader reader = new BufferedReader(new InputStreamReader(
	   p.getInputStream()));
	 String line;
	 while ((line = reader.readLine()) != null) {

	  //System.out.println(line);
	  if (line.contains(serviceName)) {
		  //System.out.println(serviceName+"--");
	   return true;
	  }
	 }

	 return false;

	}

	public static void killProcess(String serviceName) throws Exception {

	  Runtime.getRuntime().exec(KILL + serviceName);
	 // System.out.println("pass");

	 }
	/*public static void main(String[] args) throws Exception {
		 String processName = "Winium.Desktop.Driver.exe";

		 //System.out.print(isProcessRunning(processName));

		 if (isProcessRunning(processName)) {

		  killProcess(processName);
		 }

	}*/

}
