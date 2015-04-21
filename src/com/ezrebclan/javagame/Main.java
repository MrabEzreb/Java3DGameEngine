package com.ezrebclan.javagame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.ezrebclan.javagame.java.LoadingDlls;
import com.ezrebclan.javagame.scala.JavaGame;


public class Main {

	public static void main(String[] args) {
		if(checkForDeps() == false) {
			LoadingDlls progress = new LoadingDlls();
			progress.setVisible(true);
			copyDeps();
			progress.dispose();
			relaunchWithDeps();
		} else if(args.length != 0) {
			System.out.println();
			System.out.println("Complete Process Output:");
			System.out.println("Complete launch");
			JavaGame.main(args);
		} else {
			relaunchWithDeps();
		}
	}
	
	private static boolean checkForDeps() {
		File depFolder = new File("deps");
		if(depFolder.exists() && depFolder.isDirectory()) {
			return true;
		}
		return false;
	}
	
	private static void relaunchWithDeps() {
		String[] command = {
				"java",
				"-Djava.library.path=\"deps/\"",
				"-jar",
				System.getProperty("java.class.path"),
				"args"
		};
		try {
			ProcessBuilder pb = new ProcessBuilder(command);
			pb.inheritIO();
			System.out.println("Relaunching with args");
			Process p = pb.start();
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Process terminated, ending main process.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void copyDeps() {
		new File("deps").mkdirs();
		String[] deps = {
				"jinput-dx8",
				"jinput-dx8_64",
				"jinput-raw",
				"jinput-raw_64",
				"lwjgl",
				"lwjgl64",
				"OpenAL32",
				"OpenAL64"
		};
		for (String string : deps) {
			copyDep(string);
		}
	}
	private static void copyDep(String name) {
		InputStream file = Main.class.getClassLoader().getResourceAsStream("deps/"+name+".dll");
		try {
			FileOutputStream out = new FileOutputStream("deps/"+name+".dll");
			byte[] bytes = new byte[1];
			while(file.available() > 0) {
				file.read(bytes);
				out.write(bytes);
				bytes = new byte[1];
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
