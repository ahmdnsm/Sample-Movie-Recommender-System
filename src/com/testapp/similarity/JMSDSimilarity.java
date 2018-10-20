package com.testapp.similarity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.librec.math.structure.SparseVector;

public class JMSDSimilarity extends net.librec.similarity.AbstractRecommenderSimilarity{

	@Override
	public double getCorrelation(SparseVector thisVector, SparseVector thatVector) {
		List<Double> thisList = new ArrayList<Double>();
        List<Double> thatList = new ArrayList<Double>();

        for (Integer idx : thatVector.getIndex()) {
            if (thisVector.contains(idx)) {
                thisList.add(thisVector.get(idx));
                thatList.add(thatVector.get(idx));
            }
        }
        double sim = getSimilarity(thisList, thatList);
        Set<Integer> elements = new HashSet<Integer>();
        elements.addAll(thisVector.getIndexList());
        elements.addAll(thatVector.getIndexList());
        int numAllElements = elements.size();
        int numCommonElements = thisVector.size() + thatVector.size() - numAllElements;
        return ((numCommonElements + 0.0) / numAllElements)*(1-sim);
    }

	@Override
	protected double getSimilarity(List<? extends Number> thisList,
			List<? extends Number> thatList) {
		if (thisList == null || thatList == null || thisList.size() < 1 || thatList.size() < 1 || thisList.size() != thatList.size()) {
            return Double.NaN;
        }

        double sum = 0.0;

        for (int i = 0; i < thisList.size(); i++) {
            double thisValue = thisList.get(i).doubleValue();
            double thatValue = thatList.get(i).doubleValue();

            sum += Math.pow(thisValue - thatValue, 2);
        }

        double sim = sum / thisList.size();
        return sim;
	}

}
