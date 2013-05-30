package com.uba.tecnicas.promo.vista;

import java.io.OutputStream;
import java.io.IOException;
import javax.swing.JTextArea;

public class StreamOutput extends OutputStream{

   private JTextArea textArea;
     
    public StreamOutput(JTextArea textArea) {
        this.textArea = textArea;
    }
     
    @Override
    public void write(int b) throws IOException {
        textArea.append(String.valueOf((char)b));
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
