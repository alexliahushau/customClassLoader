package com.epam.mentoring.lessone;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class App {

	final private static String CLASS_TO_LOAD = "Semaphore";
	final private static String EXIT_COMMAND = "exit";
	final private static String STATUS_SUCCESS = "Status: success";
	final private static String STATUS_ERROR = "Status: error";
	
	final private static Scanner scanner = new Scanner(System.in, "UTF-8");
	
	public static void main(String[] args) {
		run();
	}
	
	private static void run() {
        boolean run = true;
        System.out.println("Welcome to custom classloader");
        while(run) {
        	System.out.println("Please enter path to load from or 'exit' to close the programm");
        	final String input = scanner.nextLine();
        	if (input.equals(EXIT_COMMAND)) {
                run = false;
            } else if (!input.isEmpty()) {
            	System.out.println("");
            	load(input);
            }
		}
    }
	
	private static void load(final String path) {
		System.out.println("Status: start loading...");
		final CustomLoader loader = new CustomLoader(path, ClassLoader.getSystemClassLoader());
		
		try {
			Class<?> clazz = loader.loadClass(CLASS_TO_LOAD);
			System.out.println(STATUS_SUCCESS);
			Method m = clazz.getMethod("lever");
			Object obj = clazz.newInstance();
			m.invoke(obj);
		} catch (ClassNotFoundException e) {
			System.out.println(STATUS_ERROR);
			e.printStackTrace(System.out);
		} catch (NoSuchMethodException e) {
			System.out.println(STATUS_ERROR);
			e.printStackTrace(System.out);
		} catch (SecurityException e) {
			System.out.println(STATUS_ERROR);
			e.printStackTrace(System.out);
		} catch (InstantiationException e) {
			System.out.println(STATUS_ERROR);
			e.printStackTrace(System.out);
		} catch (IllegalAccessException e) {
			System.out.println(STATUS_ERROR);
			e.printStackTrace(System.out);
		} catch (IllegalArgumentException e) {
			System.out.println(STATUS_ERROR);
			e.printStackTrace(System.out);
		} catch (InvocationTargetException e) {
			System.out.println(STATUS_ERROR);
			e.printStackTrace(System.out);
		}
	} 

}
