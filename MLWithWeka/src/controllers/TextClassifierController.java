package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import supportclasses.Classify;
import supportclasses.ToArff;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.TextClassifierModel;

public class TextClassifierController {
	
	public static TextClassifierModel textClassifierModel = new TextClassifierModel();
	public static Stage stage;
	
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
    
    @FXML
    private void fileBTNAction() {
    	File file = fileChooser.showOpenDialog(stage);
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        if (file != null) {
        	
        	fileTF.setText(file.getPath());
        	ToArff toArff1 = new ToArff(file.getPath());
        	textTA.setText(toArff1.getText());
        }
    }
    

    @FXML void initialize(){
    	if(modelCB != null) {
    		
        	modelCB.getItems().addAll("Naive Bayes Multinominal Text","Customized Naive Bayes Multinominal");
            modelCB.setValue("Naive Bayes Multinominal Text");
            
            modelCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            	textClassifierModel.setCBSelection(newValue);
		    });
    		
    	}
    }

}
