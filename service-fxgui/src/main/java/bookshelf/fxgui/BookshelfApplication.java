package bookshelf.fxgui;

import bookshelf.inventory.api.MutableBook;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by usuario on 30/9/15.
 */
public class BookshelfApplication extends Application {

    private ObservableList<MutableBook> bookObservableList;

    /**
     *
     * @return
     */
    private ObservableList<MutableBook> getBookObservableList() {
        if (bookObservableList == null) {
            bookObservableList = FXCollections.observableArrayList();
        }
        return bookObservableList;

    }

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
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
