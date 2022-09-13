package com.example.lab5_ultimate.controller.tag;

import com.example.lab5_ultimate.model.entity.BookEntity;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class NumberItemsTableTag extends SimpleTagSupport {

    public void doTag() throws JspException {

        Locale loc = (Locale) getJspContext().getAttribute("userLocale", PageContext.SESSION_SCOPE);
        ResourceBundle bundle = ResourceBundle.getBundle("locale", loc, this.getClass().getClassLoader());

        String role = (String) getJspContext().getAttribute("role", PageContext.SESSION_SCOPE);
        boolean isAdmin = Objects.equals(role, "admin");
        List<BookEntity> books = (List<BookEntity>) getJspContext().getAttribute("allBooks", PageContext.REQUEST_SCOPE);

        String result = "<table class=\"table\">"
                + "<thead>"
                + "<tr>"
                + "<th>" + bundle.getString("number_items.author") + "</th>"
                + "<th>" + bundle.getString("number_items.title") + "</th>"
                + "<th>" + bundle.getString("number_items.number_items") + "</th>"
                + (isAdmin ? "<th></th>" : "")
                + "</tr>"
                + "</thead>"
                + "<tbody>";
        if (books != null) {
            if (!books.isEmpty()) {

                for (BookEntity book : books) {
                    result += "<tr>"
                            + "<td>" + book.getTitle() + "</td>"
                            + "<td>" + book.getAuthor() + "</td>"
                            + "<td>" + book.getNumOfItems() + "</td>";
                    if (isAdmin) {
                        result += "<td>\n" +
                                "<form method=\"POST\"\n" +
                                "action=\"" + ((PageContext) getJspContext()).getServletContext().getContextPath() + "/library-servlet?action=remove_book\">\n" +
                                "<input type=\"hidden\" name=\"bookId\" value=\"" + book.getId() + "\">\n" +
                                "<button type=\"submit\" class=\"btn btn-primary\">" + bundle.getString("table.removeBook") + "</button>\n" +
                                "</form>\n" +
                                "</td>";
                    }
                    result += "</tr>";
                }

                result += "</tbody>"
                        + "</table>";
            }

            try {
                JspWriter out = getJspContext().getOut();
                out.write(result);
            } catch (IOException e) {
                throw new JspException(e.getMessage());
            }
        }
    }

}
