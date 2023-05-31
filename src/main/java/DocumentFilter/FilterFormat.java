package DocumentFilter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.util.regex.PatternSyntaxException;

public class FilterFormat extends DocumentFilter {

    /**
    *@param format
    *       Enviar un 4 si el formato es de IPv4, 6 si es IPv6, si envia cualquier número
    *       el formato por defecto tendrá una longitud de 10 caracteres.
    *@param regex
    *       La expresión regular que con la que el String debe coincidir.
    *@return {@code true} si, y solo si, el string cumple con las condiciones necesarias.
    *
    * @throws PatternSyntaxException
    *       Si la expresión regular es inválida.
    * */
    public JTextField filterFormatField(int format, String regex) {
        JTextField field = new JTextField();
        AbstractDocument doc = (AbstractDocument) field.getDocument();
        final int maxCharacters = (format == 4)? 8 : (format == 6)? 16 : 10;

        doc.setDocumentFilter(new DocumentFilter() {

            public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
                String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = text.substring(0, offset) + text.substring(offset + length);

                if (newText.matches(regex) || newText.length() == 0) super.remove(fb, offset, length);
            }

            public void replace(FilterBypass fb, int offset, int length, String _text, AttributeSet attrs)
                    throws BadLocationException {
                String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = text.substring(0, offset) + _text + text.substring(offset + length);
                if(newText.length() <= maxCharacters && newText.matches(regex))
                    super.replace(fb, offset, length, _text, attrs);
                else
                    Toolkit.getDefaultToolkit().beep();
            }

            public void insertString(FilterBypass fb, int offset, String str, AttributeSet attr)
                    throws BadLocationException {
                String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                String newText = text.substring(0, offset) + str + text.substring(offset);

                if((fb.getDocument().getLength() + str.length()) <= maxCharacters
                        && newText.matches(regex))
                    super.insertString(fb, offset, str, attr);
                else
                    Toolkit.getDefaultToolkit().beep();
            }
        });

        return field;
    }

}
