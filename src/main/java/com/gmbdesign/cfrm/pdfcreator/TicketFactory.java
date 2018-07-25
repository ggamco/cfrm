package com.gmbdesign.cfrm.pdfcreator;

import com.gmbdesign.cfrm.bussiness.ITicket;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketFactory {

    public static ByteArrayOutputStream createTicketPdf(List<ITicket> entradas) throws DocumentException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = null;
        Document document = new Document();

        try {
            writer = PdfWriter.getInstance(document, baos);
        } catch (DocumentException ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
        }

        document.open();
        PdfContentByte cb = writer.getDirectContent();
        BaseFont font = null;

        try {
            font = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED);
        } catch (IOException ex) {
            Logger.getLogger(CarnetFactory.class.getName()).log(Level.SEVERE, "Error al cargar fuente. {0}", ex);
        }

        return baos;
    }
}
