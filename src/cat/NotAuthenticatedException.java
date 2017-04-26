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
package cat;

import javax.naming.AuthenticationException;

/**
 * @author victorjatoba
 *
 */
@SuppressWarnings("serial")
public class NotAuthenticatedException extends AuthenticationException {

	/**
	 * 
	 */
	public NotAuthenticatedException(String message) {
		super(message);
	}

}
