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
