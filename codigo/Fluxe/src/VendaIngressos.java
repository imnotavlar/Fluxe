
public class VendaIngressos {

    private int limite;
    private int estoque;

    // Construtor
    public VendaIngressos(int limite) {
        this.limite = limite;
        this.estoque = limite;
    }

    // Retorna o limite total de ingressos
    public int getLimite() {
        return this.limite;
    }

    // Retorna quanto ainda tem disponível
    public int getEstoque() {
        return this.estoque;
    }

    // Verifica se ainda há ingressos
    public boolean temIngressos() {
        return this.estoque > 0;
    }

    // Vender 1 ingresso
    public boolean vender() {
        return vender(1);
    }

    // Vender vários ingressos
    public boolean vender(int quantidade) {
        if (quantidade <= 0) {
            return false; // quantidade inválida
        }
        if (estoque >= quantidade) {
            estoque -= quantidade;
            return true;
        }
        return false; // tentou vender mais do que tem
    }

    // Cancelar 1 ingresso
    public boolean cancelar() {
        return cancelar(1);
    }

    // Cancelar vários ingressos
    public boolean cancelar(int quantidade) {
        if (quantidade <= 0) {
            return false; // quantidade inválida
        }
        if (estoque + quantidade <= limite) {
            estoque += quantidade;
            return true;
        }
        return false; // não pode passar do limite original
    }
}
