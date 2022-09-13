package view;

import controller.LibraryController;
import model.dao.BookDao;
import model.dao.LogbookDao;
import model.dao.ReaderDao;
import model.entity.BookEntity;
import model.entity.LogbookEntity;
import model.entity.ReaderEntity;
import model.exception.ControllerException;
import model.exception.DaoException;

import java.sql.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ReaderDao readerDao = new ReaderDao();
        BookDao bookDao = new BookDao();
        LogbookDao logbookDao = new LogbookDao();
        ReaderEntity reader = new ReaderEntity();
        reader.setName("Kate");
        LogbookEntity l = new LogbookEntity();
        l.setBookId(1);
        l.setReaderId(2);
        l.setEndDate(Date.valueOf("2020-04-03"));
        try {
            logbookDao.save(l);
            System.out.println(readerDao.get(1).getName());
            readerDao.getAll().forEach(i -> System.out.println(i.getName()));

        } catch (DaoException e) {
            e.printStackTrace();
        }
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

        List<BookEntity> books = null;
        try {
            books = controller.GetBooks("author");
        } catch (ControllerException e) {
            e.printStackTrace();
        }
        for (BookEntity b: books) {
            System.out.println(b);
        }

//        try {
//            controller.GiveBook("War and peace", "author", "andrew", Date.valueOf("2022-05-03"));
//        } catch (ControllerException e) {
//            e.printStackTrace();
//        }
    }
}
//логгер и сообщения
