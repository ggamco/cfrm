/*
 * Clase que permitirá la creación de archivos pdf para carnets del tipo que sea.
 */
package com.gmbdesign.cfrm.pdfcreator;

import com.gmbdesign.cfrm.bussiness.ICredentialCard;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ggamboa
 */
public class CarnetFactory {

    public static ByteArrayOutputStream createCarnetPdf(List<ICredentialCard> carnets) throws DocumentException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = null;

        Rectangle formato = new Rectangle(153, 243);
        Document document = new Document(formato, 10f, 10f, 10f, 0f);

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
        
        int contador = 0;
        
        for (ICredentialCard carnet : carnets) {

            Barcode128 barcode128 = new Barcode128();
            barcode128.setCode(carnet.getBarcode());
            barcode128.setCodeType(Barcode.CODE128);
            barcode128.setAltText(carnet.getTipo());
            barcode128.setFont(font);
            barcode128.setSize(7);
            Image code128Image = barcode128.createImageWithBarcode(cb, null, null);
            //TODO: cambiar posición absoluta del codigo de barras
            code128Image.setAbsolutePosition(22.5f, 68f);

            document.add(code128Image);

            cb = writer.getDirectContent();

            cb.saveState();
            cb.beginText();
            cb.setFontAndSize(font, 7);
            cb.showTextAligned(1, carnet.getAlias(), 76, 128, 0);            //+10
            cb.showTextAligned(0, carnet.getCredentialType(), 5, 118, 0);    //+8
            cb.showTextAligned(0, carnet.getNumberCode(), 5, 110, 0);        //+8
            cb.showTextAligned(0, carnet.getAccessDoor(), 5, 102, 0);

//            cb.showTextAligned(1, carnet.getAlias(), 76, 40, 0);
//            cb.showTextAligned(0, carnet.getCredentialType(), 5, 30, 0);
//            cb.showTextAligned(0, carnet.getNumberCode(), 5, 22, 0);
//            cb.showTextAligned(0, carnet.getAccessDoor(), 5, 14, 0);
            //cb.showTextAligned(0, "1", 5, 6, 0);
            cb.endText();
            cb.restoreState();

            System.out.println(carnet.getNumberCode());
            System.out.println(carnet.getAlias());
            System.out.println(carnet.getCredentialType());
            System.out.println(carnet.getBarcode());
            System.out.println(carnet.getAccessDoor());

            if (carnets.size() > ++contador) {
                document.newPage();
            }

        }
        document.close();

        return baos;
    }
}
