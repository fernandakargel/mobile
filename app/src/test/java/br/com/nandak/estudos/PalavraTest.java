package br.com.nandak.estudos;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import br.com.nandak.estudos.palavra.Palavra;

public class PalavraTest {
    @Test
    public void addition_isCorrect() {
        Palavra palavra = new Palavra();
        palavra.setPalavra("Play");
        assertEquals(palavra.getPalavra() ,"Play");
    }
}
