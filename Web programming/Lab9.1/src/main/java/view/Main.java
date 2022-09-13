package view;

import controller.LibraryController;
import model.dao.BookDao;
import model.dao.LogbookDao;
import model.dao.ReaderDao;
import model.entity.BookEntity;
import model.entity.BookEntity_;
import model.entity.LogbookEntity;
import model.entity.ReaderEntity;
import model.exception.ControllerException;
import model.exception.DaoException;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ReaderDao readerDao = new ReaderDao();
        BookDao bookDao = new BookDao();
        LogbookDao logbookDao = new LogbookDao();
        LogbookEntity l = new LogbookEntity();

        LibraryController controller = new LibraryController();

        List debtors = null;
        try {
            debtors = controller.GetDebtors();
        } catch (ControllerException e) {
            e.printStackTrace();
        }
        for (Object d: debtors) {
            System.out.println(d);
        }

        int num = 0;
        try {
            num = controller.GetNumberOfBook("hello", "author");
        } catch (ControllerException e) {
            e.printStackTrace();
        }
        System.out.println(num);

        List books = null;
        try {
            books = controller.GetBooks("author");
        } catch (ControllerException e) {
            e.printStackTrace();
        }
        for (Object b : books) {
            System.out.println(b);
        }

//        try {
//            controller.GiveBook("War and peace", "author", "andrew", Date.valueOf("2022-05-03"));
//        } catch (ControllerException e) {
//            e.printStackTrace();
//        }
    }
}
