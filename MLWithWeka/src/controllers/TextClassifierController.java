package controllers;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import supportclasses.Classify;
import supportclasses.ToArff;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    @FXML private Button classifyBTN;
    @FXML private TextArea textTA;
    @FXML private TextField fileTF;
    @FXML private Button fileBTN;
    @FXML private Button resetBTN;
    
    @FXML
    private void classifyBTNAction() {
    	System.out.println("Button Pressed");
    	PrintWriter out = null;
        String input = textTA.getText();
        try {
            out = new PrintWriter("cache.txt", "UTF-8");
            out.println(input);
        } catch (FileNotFoundException | UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        out.close();
        
        ToArff toArff = new ToArff("cache.txt");
        toArff.convert();

        Classify classifier = new Classify();
        classifier.loadModel(textClassifierModel.getModel());
        System.out.println("using model: "+ textClassifierModel.getModel());
        classifier.classify("output.arff");

        classifier.printTestClasses();
        outputTF.setText(classifier.classificationsAsString());
    }
    
    @FXML
    private void resetBTNAction() {
    	textTA.setText("");
    	outputTF.setText("");
    	fileTF.setText("");
    }

	
    @FXML void initialize(){
    	if(modelCB != null) {
    		
        	modelCB.getItems().addAll("Naive Bayes Multinominal Text","Customized Naive Bayes Multinominal");
            modelCB.setValue("Naive Bayes Multinominal Text");
            
            modelCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            	textClassifierModel.setCBSelection(newValue);
		    	System.out.println(newValue+" selected");
		    });
    		
    	}
    }

}
