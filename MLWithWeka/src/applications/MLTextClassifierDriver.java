package applications;

import controllers.TextClassifierController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MLTextClassifierDriver extends Application{
	
	 @Override
	    public void start(Stage primaryStage) throws Exception {

	    	Node simple = FXMLLoader.load(getClass().getResource("/view0.fxml"));
			
	        StackPane root = new StackPane();
	        root.getChildren().add(simple);
	        
	        Scene scene = new Scene(root, 600, 350);
	        
	        primaryStage.setTitle("ML Text Classifier");
	        primaryStage.setScene(scene);
	        primaryStage.setResizable(false);
	        primaryStage.show();
	        TextClassifierController.stage = primaryStage;
	    }
	 public static void main(String[] args) {
	        launch(args);
	    }

}
