package lk.ijse.ormCoursework.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.ormCoursework.bo.custom.BookBo;
import lk.ijse.ormCoursework.bo.impl.BookBoImpl;
import lk.ijse.ormCoursework.dto.BookDto;
import lk.ijse.ormCoursework.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookFormController {
    public AnchorPane root;
    public TableView tblBook;
    public TableColumn colId;
    public TableColumn colTitle;
    public TableColumn colAuthor;
    public TableColumn colGenre;
    public TableColumn colStatus;
    public JFXTextField txtId;
    public JFXTextField txtTitle;
    public JFXTextField txtAuthor;
    public JFXTextField txtGenre;
    public JFXComboBox cmbStatus;
    BookBo bookBo=new BookBoImpl();

    public void initialize(){
        colId.setCellValueFactory (new PropertyValueFactory<>("bId"));
        colTitle.setCellValueFactory (new PropertyValueFactory<> ("title"));
        colAuthor.setCellValueFactory (new PropertyValueFactory<> ("author"));
        colGenre.setCellValueFactory (new PropertyValueFactory<> ("genre"));
        colStatus.setCellValueFactory (new PropertyValueFactory<> ("status"));

        loadAllBooks();
        setStatus();
        setBookId();
    }

    private void setStatus() {
        ObservableList<String> data = FXCollections.observableArrayList ("AVALILABLE", "NOTAVALAIBLE");
        cmbStatus.setItems (data);
    }

    private void loadAllBooks() {
        try {
            List allBook = bookBo.loadAll ();
            ObservableList observableList = FXCollections.observableArrayList (allBook);
            tblBook.setItems (observableList);

        } catch (Exception e) {
            System.out.println (e);
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String bookId = txtId.getText();
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String genre = txtGenre.getText();
        String status = cmbStatus.getValue().toString();
        boolean isSaved = validateBook();
        if (isSaved) {
            BookDto bookdto = new BookDto(
                    bookId,
                    title,
                    author,
                    genre,
                    status
            );

            try {

                List<BookDto> allBooks = bookBo.loadAll();

                if (checkduplicate()) {

                    bookBo.saveBook(bookdto);
                    loadAllBooks();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            new Alert(Alert.AlertType.ERROR,"ENTER RIGHT DETAIL..!").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        BookDto bookdto = new BookDto (
                txtId.getText(),
                txtTitle.getText (),
                txtAuthor.getText (),
                txtGenre.getText(),
                cmbStatus.getValue().toString()

        );

        boolean isUpdate = bookBo.updateBook (bookdto);

        if (isUpdate) {
            new Alert (Alert.AlertType.INFORMATION, " Update Complete...!").show ();
            // tblRoom.getItems ().clear ();
            clearDataText();
            loadAllBooks();
            //setBookId ();
        } else {
            new Alert (Alert.AlertType.ERROR, "Something went Wrong").show ();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        BookDto bookdto = new BookDto (
                txtId.getText (),
                txtTitle.getText (),
                txtAuthor.getText (),
                txtGenre.getText(),
                cmbStatus.getValue().toString()

        );

        boolean isDeleted = bookBo.deleteBook (bookdto);

        if (isDeleted) {
            new Alert (Alert.AlertType.INFORMATION, "Book Delete Succes..!").show ();
            tblBook.getItems ().clear ();
            clearDataText ();
            loadAllBooks ();
        } else {
            new Alert (Alert.AlertType.ERROR, "Something went Wrong").show ();
        }
    }

    private boolean validateBook() {
        String id_value=txtId.getText();
        Pattern complie=Pattern.compile("[B][0-9]{3}");
        Matcher matcher=complie.matcher(id_value);
        boolean matches=matcher.matches();
        if (!matches){
            new Alert(Alert.AlertType.ERROR,"INVALID BOOK ID").show();
            return  false;
        }
        String title=txtTitle.getText();
        Pattern compile1 = Pattern.compile("[A-Za-z]{4,}");
        Matcher matcher1=compile1.matcher(title);
        boolean isAddress=matcher1.matches();
        if (!isAddress){
            new Alert(Alert.AlertType.ERROR,"WRONG ADDRSS TYPE").show();
        }
        String nameText=txtAuthor.getText();
        boolean isnameValid= Pattern.compile("[A-Za-z]{3,}").matcher(nameText).matches();

        if (!isnameValid){
            new Alert(Alert.AlertType.ERROR,"WRONG NAME TYPE").show();
        }

        return true;
    }

    private boolean checkduplicate() {
        String bookId = txtId.getText ();
        List<BookDto> allBooks = bookBo.loadAll ();
        for (BookDto books : allBooks) {
            if (bookId.equals (books.getBId())) {
                new Alert(Alert.AlertType.ERROR, "This ID Already Have").show ();
                return false;
            }
        }
        return true;
    }

    private void clearDataText() {
        txtId.clear();
        txtTitle.clear();
        txtAuthor.clear();
        txtGenre.clear();
    }

    public String nextBookId() {
        Session session = SessionFactoryConfig.getInstance ().getSession ();
        Transaction transaction = session.beginTransaction ();

        Query query = session.createQuery ("select id from book order by id desc");

        String nextId = "B001";

        if (query.list ().size () == 0) {
            return nextId;
        } else {
            String id = (String) query.list ().get (0);

            String[] SUs = id.split ("B00");

            for (String a : SUs) {
                id = a;
            }

            int idNum = Integer.valueOf (id);

            id = "B00" + (idNum + 1);

            transaction.commit ();
            session.close ();

            return id;
        }
    }

    public void setBookId(){
        String bookId = nextBookId();
        txtId.setText(bookId);
    }
}
