import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Jogador implements Comparable<Jogador>{
    private IDJogador idJogador;
    private String nome;
    private List<Carta> cartas;
    private Carta cartaEscolhida;

    private Jogador(final IDJogador idJogador, final String nome, final List<Carta> cartas, final Carta cartaEscolhida){
        this.idJogador = idJogador;
        this.nome = nome;
        this.cartas = cartas;
        this.cartaEscolhida = cartaEscolhida;
    }

    public static Jogador of(final String nome){
        return new Jogador(IDJogador.of(), nome, new ArrayList<>(), null);
    }

    public void cartaEscolhida(final Carta carta){
        this.cartaEscolhida = carta;
    }

    public void addCarta(final Carta carta){
        this.cartas.add(carta);
    }

    public IDJogador getIdJogador() {
        return idJogador;
    }

    public String getNome() {
        return nome;
    }

    public List<Carta> getCartas() {
        return Collections.unmodifiableList(cartas);
    }

    public Carta getCartaEscolhida() {
        return cartaEscolhida;
    }

    @Override
    public int compareTo(final Jogador jogador) {
        return this.cartaEscolhida.compareTo(jogador.cartaEscolhida);
    }
    
}
