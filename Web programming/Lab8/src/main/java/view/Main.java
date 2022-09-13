package view;

import controller.LibraryController;
import model.exception.ControllerException;
import model.exception.DaoException;

import java.sql.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        LibraryController controller = null;
        try {
            controller = new LibraryController();
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            return;
        }

        List debtors = null;
        try {
            debtors = controller.GetDebtors();
        } catch (ControllerException e) {
            System.out.println(e.getMessage());
        }
        for (var d: debtors) {
            System.out.println(d);
        }

        int num = 0;
        try {
            num = controller.GetNumberOfBook("hello", "author");
        } catch (ControllerException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(num);

        List books = null;
        try {
            books = controller.GetBooks("author");
        } catch (ControllerException e) {
            System.out.println(e.getMessage());
        }
        for (var b: books) {
            System.out.println(b);
        }

//        try {
//            controller.GiveBook("War and peace", "author", "andrew", Date.valueOf("2022-05-03"));
//        } catch (ControllerException e) {
//            e.printStackTrace();
//        }
    }
}
