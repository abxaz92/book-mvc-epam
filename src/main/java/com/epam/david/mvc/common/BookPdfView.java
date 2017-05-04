package com.epam.david.mvc.common;

import com.epam.david.mvc.entities.Book;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by David_Chaava on 5/4/2017.
 */
public class BookPdfView extends AbstractPdfView {
    private static final Logger logger = Logger.getLogger("BookPdfView");

    @Override
    protected void buildPdfDocument(Map<String, Object> map,
                                    Document document,
                                    PdfWriter pdfWriter,
                                    HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse) throws Exception {


        List<Book> books = (List<Book>) map.get("books");

        Table table = new Table(2);
        table.addCell("Name");
        table.addCell("Author");
        for (Book entry : books) {
            table.addCell(entry.getName());
            table.addCell(entry.getAuthor());
            logger.info(entry.getAuthor());
        }
        document.add(table);
    }
}
