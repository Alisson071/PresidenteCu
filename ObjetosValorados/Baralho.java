
public class Baralho implements ObjetoValorado{
    private final Carta[] cartas;

    private Baralho(){
        this.cartas = new Carta[52];

        for(final var carta : ValorCarta.values()){
            for(final var naipe : Naipe.values()){
                cartas[carta.ordinal() + naipe.ordinal()* 13] = Carta.of(carta, naipe);
            }
        }
    }
    public static Baralho of(){
        return new Baralho();
    }

    public Carta[] getCartas(){
        return cartas;
    }
}
