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
package data;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.exception.DimensionMismatchException;

import com.itemanalysis.psychometrics.irt.estimation.IrtExaminee;
import com.itemanalysis.psychometrics.irt.model.ItemResponseModel;

public class ExamineeModel {

	private List<ItemResponseModel> irm = null;
	private double estimatedTheta = 0.0; // TODO initialize to NaN??
	private IrtExaminee examinee = null;

	public ExamineeModel(ItemResponseModel[] irm) throws DimensionMismatchException {
		this.irm = Arrays.asList(irm);
		initializeScores(irm);
	}

	private void initializeScores(ItemResponseModel[] irm) {
		this.examinee = new IrtExaminee(irm);
	}

	public List<ItemResponseModel> getIrm() {
		return irm;
	}

	public void setIrm(List<ItemResponseModel> irm) {
		this.irm = irm;
	}

	public double getTheta() {
		return estimatedTheta;
	}

	public void setTheta(double estimatedTheta) {
		this.estimatedTheta = estimatedTheta;
	}

	public double getEstimatedTheta() {
		return estimatedTheta;
	}

	public void setEstimatedTheta(double estimatedTheta) {
		this.estimatedTheta = estimatedTheta;
	}

	public IrtExaminee getExaminee() {
		return examinee;
	}

	public void setExaminee(IrtExaminee examinee) {
		this.examinee = examinee;
	}

}
