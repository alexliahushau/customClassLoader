package com.epam.mentoring.lessone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class CustomLoader extends ClassLoader {
	
	private String path;

	public CustomLoader(final String path, final ClassLoader parent) {
		super(parent);
		this.path = path;
	}

	@Override
	public Class<?> findClass(final String className) throws ClassNotFoundException {
		try {
			byte b[] = fetchClassFromFS(path + className + ".class");
			return defineClass(className, b, 0, b.length);
		} catch (FileNotFoundException ex) {
			return super.findClass(className);
		} catch (IOException ex) {
			return super.findClass(className);
		}

	}

	private byte[] fetchClassFromFS(final String path) throws FileNotFoundException, IOException {
		final InputStream is = new FileInputStream(new File(path));
		final long length = new File(path).length();
		final byte[] bytes = new byte[(int) length];

		int offset = 0;
		int numRead = 0;
		
		while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		if (offset < bytes.length) {
			throw new IOException("Could not completely read file " + path);
		}

		is.close();
		return bytes;
	}
}
