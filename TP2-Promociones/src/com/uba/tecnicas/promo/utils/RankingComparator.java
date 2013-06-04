package com.uba.tecnicas.promo.utils;

import java.util.Comparator;
import java.util.Map;
public class RankingComparator implements Comparator<String> {

    Map<String, Integer> base;

    public RankingComparator(Map<String, Integer> base) {
        this.base = base;
    }

    @Override
    public int compare(String a, String b) {
    	if (base.get(a).intValue() == base.get(b).intValue()) {
    		return 0;
    	} else if (base.get(a).intValue() <= base.get(b).intValue()) {
            return 1;
        } else {
            return -1;
        }
    }
}