package controllers;

import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import models.TextClassifierModel;

public class TextClassifierController {
	
	public static TextClassifierModel textClassifierModel = new TextClassifierModel();
	
	@FXML private FileChooser fileChooser = new FileChooser();
    @FXML private ComboBox<String> modelCB = new ComboBox<>();
    @FXML private TextField outputTF = new TextField();
    @FXML private Button predictBTN;
    @FXML private TextArea textTA;
    @FXML private TextField fileTF;
    @FXML private Button fileBTN;
    @FXML private Button resetBTN;
	
    @FXML void initialize(){
    	if(modelCB != null) {
    		
        	modelCB.getItems().addAll("Naive Bayes Multinominal Text","Customized Naive Bayes Multinominal");
            modelCB.setValue("Naive Bayes Multinominal Text");
            
            Map<String,String> modelMap = new HashMap<String,String>();
            modelMap.put("Naive Bayes Multinominal Text", "classBalancedNBMT.model");
            modelMap.put("Customized Naive Bayes Multinominal", "classBalancedSTWVNBM.model");
            
            modelCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
		    	System.out.println(newValue+" selected");
		    });
    		
    	}
    }

}
