/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Texto;

import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author nejo
 */
public class TxtMayusculas extends TxtText1 {
    public TxtMayusculas() {
        DocumentFilter filter = new UppercaseDocumentFilter();
        ((AbstractDocument) this.getDocument()).setDocumentFilter(filter);
        
    }

    class UppercaseDocumentFilter extends DocumentFilter {

        public void insertString(DocumentFilter.FilterBypass fb, int offset, String text,
                AttributeSet attr) throws BadLocationException {

            fb.insertString(offset, text.toUpperCase(), attr);
        }

        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text,
                AttributeSet attrs) throws BadLocationException {

            fb.replace(offset, length, text.toUpperCase(), attrs);
        }
    }
}
