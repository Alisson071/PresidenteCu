import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carta implements Comparable<Carta>{
    private final IDCarta idCarta;
    private final ValorCarta valorCarta;
    private final Naipe naipe;

    private Carta(final IDCarta idCarta, final ValorCarta valorCarta, final Naipe naipe){
        this.idCarta = idCarta;
        this.valorCarta = valorCarta;
        this.naipe = naipe;
    }

    public static Carta of(final ValorCarta valorCarta, final Naipe naipe){
        return new Carta(IDCarta.of(), valorCarta, naipe);
    }

    public IDCarta getIDCarta(){
        return idCarta;
    }

    public ValorCarta getValorCarta() {
        return valorCarta;
    }

    public Naipe getNaipe() {
        return naipe;
    }

    @Override
    public String toString() {
        return this.valorCarta + " de " + this.naipe;
    }

    @Override
    public int compareTo(final Carta o) {
        return o.getValorCarta().getValor() - this.getValorCarta().getValor();
    }
}
