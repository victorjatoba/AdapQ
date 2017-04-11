/**
 * Copyright 2016 J. Patrick Meyer
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.itemanalysis.psychometrics.measurement;

import com.itemanalysis.psychometrics.data.VariableName;
import com.itemanalysis.psychometrics.polycor.PearsonCorrelation;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * Item response summary conditional on a test score.
 */
public class ConditionalTextItemResponseSummary {

    private VariableName variableName = null;

    /**
     * Proportion endorsing item/category
     */
    private Mean mean = null;

    /**
     * Standard deviation of endorsed category
     */
    private StandardDeviation sd = null;

    /**
     * Item/Category - total correlation
     */
    private PearsonCorrelation pearsonCorrelation = null;

    /**
     * Map of item response summaries for each category
     */
    private TreeMap<Double, TextItemResponseSummary> summaryTreeMap = null;

    public ConditionalTextItemResponseSummary(VariableName variableName){
        this.variableName = variableName;
        summaryTreeMap = new TreeMap<Double, TextItemResponseSummary>();

        mean = new Mean();
        sd = new StandardDeviation();
        pearsonCorrelation = new PearsonCorrelation();
    }

    public void increment(double score, String response){
        increment(score, response, 1.0);
    }

    public void increment(double score, String response, double freqWeight){
        Double d = Double.valueOf(score);
        TextItemResponseSummary irs = summaryTreeMap.get(d);
        if(null==irs){
            irs = new TextItemResponseSummary(variableName);
            summaryTreeMap.put(d, irs);
        }
        irs.increment(response, freqWeight);
        mean.increment(score);
        sd.increment(score);
        pearsonCorrelation.increment(score, irs.getScoreAt(response));
    }

    public void increment(double score, double response){
        increment(score, response);
    }

    public void increment(double score, double response, double freqWeight){
        Double d = Double.valueOf(score);
        TextItemResponseSummary irs = summaryTreeMap.get(d);
        if(null==irs){
            irs = new TextItemResponseSummary(variableName);
            summaryTreeMap.put(d, irs);
        }
        irs.increment(response, freqWeight);
        mean.increment(score);
        sd.increment(score);
        pearsonCorrelation.increment(score, irs.getScoreAt(Double.valueOf(response).toString()));
    }

    public void increment(double score, int response){
        increment(score, response, 1.0);
    }

    public void increment(double score, int response, double freqWeight){
        Double d = Double.valueOf(score);
        TextItemResponseSummary irs = summaryTreeMap.get(d);
        if(null==irs){
            irs = new TextItemResponseSummary(variableName);
            summaryTreeMap.put(d, irs);
        }
        irs.increment(response, freqWeight);
        mean.increment(score);
        sd.increment(score);
        pearsonCorrelation.increment(score, irs.getScoreAt(Double.valueOf(response).toString()));
    }

    /**
     * Returns frequency of examinees with a given test score who gave a particular response.
     *
     * @param score test score
     * @param response item response
     * @return frequency of item response by those with a value of score
     */
    public double getFrequencyAt(double score, String response){
        TextItemResponseSummary irs = summaryTreeMap.get(Double.valueOf(score));
        if(null==irs) return 0.0;
        return irs.getFrequencyAt(response);
    }

    public double getFrequencyAt(double score, double response){
        TextItemResponseSummary irs = summaryTreeMap.get(Double.valueOf(score));
        if(null==irs) return 0.0;
        return irs.getFrequencyAt(response);
    }

    public double getFrequencyAt(double score, int response){
        TextItemResponseSummary irs = summaryTreeMap.get(Double.valueOf(score));
        if(null==irs) return 0.0;
        return irs.getFrequencyAt(response);
    }

    public double getProportionAt(double score, String response){
        TextItemResponseSummary irs = summaryTreeMap.get(Double.valueOf(score));
        if(null==irs) return 0.0;
        return irs.getProportionAt(response);
    }

    public double getProportionAt(double score, double response){
        TextItemResponseSummary irs = summaryTreeMap.get(Double.valueOf(score));
        if(null==irs) return 0.0;
        return irs.getProportionAt(response);
    }

    public double getProportionAt(double score, int response){
        TextItemResponseSummary irs = summaryTreeMap.get(Double.valueOf(score));
        if(null==irs) return 0.0;
        return irs.getProportionAt(response);
    }

    public double getMeanAt(double score){
        TextItemResponseSummary irs = summaryTreeMap.get(Double.valueOf(score));
        if(null==irs) return 0.0;
        return irs.mean();
    }

    public double getSampleVarianceAt(double score){
        TextItemResponseSummary irs = summaryTreeMap.get(Double.valueOf(score));
        if(null==irs) return 0.0;
        return irs.sampleVariance();
    }

    public double getPopulationVarianceAt(double score){
        TextItemResponseSummary irs = summaryTreeMap.get(Double.valueOf(score));
        if(null==irs) return 0.0;
        return irs.populationVariance();
    }

    public double getSampleStandardDeviationAt(double score){
        TextItemResponseSummary irs = summaryTreeMap.get(Double.valueOf(score));
        if(null==irs) return 0.0;
        return irs.sampleStandardDeviation();
    }

    public double getPopulationStandardDeviationAt(double score){
        TextItemResponseSummary irs = summaryTreeMap.get(Double.valueOf(score));
        if(null==irs) return 0.0;
        return irs.populationStandardDeviation();
    }

    public double mean(){
        return mean.getResult();
    }

    public double standardDeviation(){
        return sd.getResult();
    }

    public double correlation(){
        return pearsonCorrelation.value();
    }

    public Iterator<Double> iterator(){
        return summaryTreeMap.keySet().iterator();
    }

    public Iterator<String> iteratorAt(double score){
        return summaryTreeMap.get(score).stringIterator();
    }

}
