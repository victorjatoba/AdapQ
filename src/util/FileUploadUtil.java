/*
 * Copyright 2017 V. M. G. Jatobá
 *
 * Licensed under the MIT;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://opensource.org/licenses/MIT
 *
 * The software is provided "AS IS", without warranty of any kind, 
 * express or implied, including but not limited to the warranties
 * of merchantability, fitness for a particular purpose and
 * noninfringement. In no event shall the authors or copyright
 * holders be liable for any claim, damages or other liability,
 * whether in an action of contract, tort or otherwise, arising
 * from, out of or in connection with the software or the use
 * or other dealings in the software.
 */
package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import testdata.TestData;

/**
 * Generic characteristics about File upload
 * 
 * @author victorjatoba
 */
public class FileUploadUtil {

	private static byte[][] lsat7 = null;
	private String path;
	private String pathFormatted;
	private byte[] content;
	private String description;
	private String contentType;

	public FileUploadUtil() {

	}

	/**
	 * Reading data file.
	 * 
	 * @param dataName
	 *            the name of the file.
	 * @param lineNumber
	 *            the number of lines
	 * @param columnNumber
	 *            the number of columns
	 * @return the matrix of data
	 */
	public static byte[][] readTestData(String dataName, int lineNumber, int columnNumber) {
		byte[][] testData = null;
		try {
			testData = new byte[lineNumber][columnNumber];
			File f = FileUtils.toFile(TestData.class.getResource(dataName));
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = "";
			String[] s = null;
			int row = 0;
			// br.readLine();// eliminate column names by skipping first row
			while ((line = br.readLine()) != null) {
				s = line.split(",");
				for (int j = 0; j < s.length; j++) {
					testData[row][j] = Byte.parseByte(s[j]);
				}
				row++;
			}
			br.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return testData;
	}

	public static void readLsat7Data() {
		lsat7 = new byte[32][5];
		try {
			File f = FileUtils.toFile(TestData.class.getResource("lsat7.txt"));
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = "";
			String[] s = null;
			int row = 0;
			br.readLine();// eliminate column names by skipping first row
			while ((line = br.readLine()) != null) {
				s = line.split(",");
				for (int j = 0; j < s.length; j++) {
					lsat7[row][j] = Byte.parseByte(s[j]);
				}
				row++;
			}
			br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPathFormatted() {
		return pathFormatted;
	}

	public void setPathFormatted(String pathFormatted) {
		this.pathFormatted = pathFormatted;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(final String contentType) {
		this.contentType = contentType;
	}

	public String getPathSrc() {
		final String path = this.pathFormatted;
		return path;
	}
}
