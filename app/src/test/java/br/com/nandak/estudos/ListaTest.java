package br.com.nandak.estudos;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import br.com.nandak.estudos.lista.Lista;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ListaTest {
    @Test
    public void addition_isCorrect() {
        Lista lista = new Lista();
        lista.setNome("Verbos");
        assertEquals(lista.getNome() ,"Verbos");
    }
}