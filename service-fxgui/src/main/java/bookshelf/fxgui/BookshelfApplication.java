package bookshelf.fxgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by usuario on 30/9/15.
 */
public class BookshelfApplication extends Application {

    /**
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane pane = (Pane) FXMLLoader.load(getClass().getResource("view/BookshelfLayout.fxml"));
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Bookshelf Application");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
