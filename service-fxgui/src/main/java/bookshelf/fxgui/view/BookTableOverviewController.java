package bookshelf.fxgui.view;


import bookshelf.inventory.api.MutableBook;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BookTableOverviewController {

    @FXML
    private TableView<MutableBook> bookTableView;
    @FXML
    private TableColumn<MutableBook, String> isbnTableColumn;
    @FXML
    private TableColumn<MutableBook, String> titleTableColumn;
    @FXML
    private TableColumn<MutableBook, String> authorTableColumn;
    @FXML
    private TableColumn<MutableBook, String> categoryTableColumn;
    @FXML
    private TableColumn<MutableBook, Integer> ratingTableColumn;

    @FXML
    private void initialize() {

        //isbnTableColumn.setCellValueFactory(cellData -> cellData.getValue().getIsbn());
        //titleTableColumn.setCellValueFactory(cellData -> cellData.getValue().getTile());

    }
}
