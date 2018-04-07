package models;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
