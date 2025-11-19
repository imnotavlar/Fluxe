import static org.junit.Assert.*;
import org.junit.Test;

public class VendaIngressosTest {

    // alterar o valor de cada teste de forma facil

    private final int limitedepessoas = 100;
    private final int estoqueinicial = 100;

    private final int vendaesperada = 99;

    private final int limitevenda = 1;
    private final int estoquezerado = 0;

    private final int limiteestoque = 5;

    private final int limitecancelar = 100;

    private final int temingressolimite = 2;

    private final int limitetestemaior = 300;

    // 1 - estoque inicial deve ser igual ao limite configurado
    @Test
    public void testEstoqueInicial() {
        VendaIngressos v = new VendaIngressos(limitedepessoas);
        assertEquals(estoqueinicial, v.getEstoque());
        assertEquals(limitedepessoas, v.getLimite());
    }

    // 2 - deve permitir vender 1 ingresso quando tem estoque
    @Test
    public void testVenderUmIngresso() {
        VendaIngressos v = new VendaIngressos(limitedepessoas);
        boolean sucesso = v.vender();
        assertTrue(sucesso);
        assertEquals(vendaesperada, v.getEstoque());
    }

    // 3 - nao pode vender quando nao tem estoque
    @Test
    public void testVenderSemEstoque() {
        VendaIngressos v = new VendaIngressos(limitevenda);
        v.vender();
        boolean sucesso = v.vender();
        assertFalse(sucesso);
        assertEquals(estoquezerado, v.getEstoque());
    }

    // 4 - deve vender ingressos ate zerar o estoque
    @Test
    public void testVenderAteZerar() {
        VendaIngressos v = new VendaIngressos(limiteestoque);
        for (int i = 0; i < limiteestoque; i++) {
            assertTrue(v.vender());
        }
        assertEquals(estoquezerado, v.getEstoque());
    }

    // 5 - nao deixar vender mais do que o estoque
    @Test
    public void testVendaMaiorQueEstoque() {
        VendaIngressos v = new VendaIngressos(limiteestoque);
        boolean sucesso = v.vender(10);
        assertFalse(sucesso);
        assertEquals(limiteestoque, v.getEstoque());
    }

    // 6 - tem que deixar cancelar um ingresso
    @Test
    public void testCancelarUmIngresso() {
        VendaIngressos v = new VendaIngressos(limitecancelar);
        v.vender();
        boolean sucesso = v.cancelar();
        assertTrue(sucesso);
        assertEquals(limitecancelar, v.getEstoque());
    }

    // 7 - nao deixar cancelar acima do limite inicial
    @Test
    public void testCancelarAlemDoLimite() {
        VendaIngressos v = new VendaIngressos(limitecancelar);
        boolean sucesso = v.cancelar();
        assertFalse(sucesso);
        assertEquals(limitecancelar, v.getEstoque());
    }

    // 8 - testar se temIngressos funciona
    @Test
    public void testTemIngressos() {
        VendaIngressos v = new VendaIngressos(temingressolimite);
        assertTrue(v.temIngressos());
        v.vender();
        v.vender();
        assertFalse(v.temIngressos());
    }

    // 9 - deve vender depois de cancelar
    @Test
    public void testVenderDepoisDeCancelar() {
        VendaIngressos v = new VendaIngressos(limitevenda);
        v.vender();
        v.cancelar();
        boolean sucesso = v.vender();
        assertTrue(sucesso);
        assertEquals(estoquezerado, v.getEstoque());
    }

    // 10 - deve respeitar limite no construtor
    @Test
    public void testLimiteConfigurado() {
        VendaIngressos v = new VendaIngressos(limitetestemaior);
        assertEquals(limitetestemaior, v.getLimite());
        assertEquals(limitetestemaior, v.getEstoque());
    }
}
