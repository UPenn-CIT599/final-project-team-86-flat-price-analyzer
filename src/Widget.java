//package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
// import javafx.scene.layout.BorderPane;
import javafx.scene.control.TabPane;
import javafx.fxml.FXMLLoader;


public class Widget extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			TabPane root = (TabPane)FXMLLoader.load(getClass().getResource("GUI.fxml"));
			Scene scene = new Scene(root, 600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
//	/Users/admin/GitHub/final-project-team-86-flat-price-analyzer/src/ 
// 11.0.1
	public static void main(String[] args) {
		launch(args);
	}
}
