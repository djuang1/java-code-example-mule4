package com.dejim;

import java.io.*;

public class SysCommand {

	public SysCommand() {

	}

	boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

	public String process(String command) {
		StringBuffer response = new StringBuffer();
		String line;
		Process process;

		try {
			if (isWindows) {
				process = Runtime.getRuntime().exec(String.format(command	));
			} else {
				process = Runtime.getRuntime().exec(String.format(command));
			}
			process.waitFor();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while ((line = reader.readLine()) != null) {
				response.append(line + "\n");
			}
			while ((line = error.readLine()) != null) {
				response.append(line + "\n");
			}
		} catch (IOException e1) {
		} catch (InterruptedException e2) {
		}
		return response.toString();
	}
}
