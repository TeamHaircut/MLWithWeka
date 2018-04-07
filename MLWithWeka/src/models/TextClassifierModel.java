package models;

import java.util.HashMap;
import java.util.Map;


public class TextClassifierModel {
	
	private String cbSelection;
	
	private Map<String,String> modelMap = new HashMap<String,String>();
	
	public TextClassifierModel() {
		cbSelection = "Naive Bayes Multinominal Text";
		
		modelMap.put("Naive Bayes Multinominal Text", "classBalancedNBMT.model");
	    modelMap.put("Customized Naive Bayes Multinominal", "classBalancedSTWVNBM.model");
	}
	
	public void setCBSelection(String newSelectedItem) {
		cbSelection = newSelectedItem;
	}
	
	public String getCBSelectionProp() {
		return cbSelection;
	}
	
	public String getModel() {
		return modelMap.get(cbSelection);
	}

}
