package com.mycompany.todo;

import com.mycompany.todo.data.Todo;
import com.mycompany.todo.data.TodoMangerFileImp;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;


/**
 * Controller of graphic user interface.
 * @author indra43
 */
public class FXMLController implements Initializable {

    @FXML
    private TableColumn todoColumn;

    @FXML
    private TableColumn<Todo, Date> doneColumn;

    @FXML
    private Button newTodoButton;

    @FXML
    private Button todoDoneButton;
    @FXML
    private TableView<Todo> todoTable;

    @FXML
    void createTodo(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Create new todo");
        dialog.setHeaderText("Enter todo");
        dialog.setContentText("Type your todo here:");
        Optional<String> resault = dialog.showAndWait();
        resault.ifPresent(name -> {
            if(!name.isEmpty()){
            todoTable.getItems().add(new Todo(name));
            }
        });

    }

    @FXML
    void setTodoAsDone(ActionEvent event) {
        if (!todoTable.getSelectionModel().isEmpty() && todoTable.getSelectionModel().getSelectedItem().getCompletionTime() == null) {
            todoTable.getSelectionModel().getSelectedItem().setCompletionTime(new Date().getTime());
            todoTable.refresh();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        todoTable.getItems().addAll(TodoMangerFileImp.getInstance().getAllToDoes());

    }
/**
 * Inits table view columns
 */
    private void initTable() {
        todoColumn.setCellValueFactory(new PropertyValueFactory("task"));
        doneColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Todo, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Todo, Date> param) {
                if (param.getValue().getCompletionTime() != null) {

                    return new SimpleObjectProperty<>(new Date(param.getValue().getCompletionTime()));
                }
                return new SimpleObjectProperty<>();
            }
        });
        todoTable.setRowFactory(new Callback<TableView<Todo>, TableRow<Todo>>() {
            @Override
            public TableRow<Todo> call(TableView<Todo> param) {
                final TableRow<Todo> row = new TableRow<Todo>() {
                    @Override
                    protected void updateItem(Todo item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty&&item.getCompletionTime() != null) {
                            setStyle("-fx-background-color:lightgreen");
                }
                    }
                };

                
                
                return row;
            }
        });
    }
/**
 * Saves all todos to file.
 */
    public void saveTodos() {
        TodoMangerFileImp.getInstance().saveTodo(todoTable.getItems());
    }

}
