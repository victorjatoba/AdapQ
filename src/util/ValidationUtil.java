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

/**
 * Contains all util file methods.
 * 
 * @author victorjatoba
 */
public class ValidationUtil {

	/**
	 * Verify if the object is null or empty.
	 * 
	 * @param obj
	 * @return {@code true} if is <br/>
	 *         {@code false} otherwise
	 */
	public static Boolean isNullOrEmpty(Object obj) {
		return (obj == null || obj.equals(""));
	}

	/**
	 * Verify if the object is null or empty.
	 * 
	 * @param <T>
	 * 
	 * @param obj
	 * @return {@code true} if is <br/>
	 *         {@code false} otherwise
	 */
	public static <T> Boolean isNull(T obj) {
		return (obj == null);
	}
}
