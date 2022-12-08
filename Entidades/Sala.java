
import java.io.ObjectInputFilter.Status;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sala {

   private final static int N_BARALHOS = 2;
   private final IDSala idSala;
   private Jogador dono;
   private final LinkSala linkSala;
   private ConfiguracaoDeAcesso configuracaoDeAcesso;
   private List<Jogador> jogadores;
   private List<IDJogador> jogadorAEscolher;
   private List<Carta> cartasAEscolher;

   private List<Carta> cartasDistribuidas;
   private Status status;

   private Sala( final IDSala idSala, final Jogador dono, final LinkSala linkSala, final ConfiguracaoDeAcesso configuracaoDeAcesso, final List<Jogador> jogadores){
      this.idSala = idSala;
      this.dono = dono;
      this.linkSala = linkSala;
      this.configuracaoDeAcesso = configuracaoDeAcesso;
      this.jogadores = jogadores;
      this.cartasAEscolher = new ArrayList<>();
      this.cartasDistribuidas = new ArrayList<>();

      inicializarJogadores(jogadores);
      this.status = Status.ESPERANDO;
   }

   public void  distribuirCartas(){
      var  qntCartasARemover = (N_BARALHOS * 52) % jogadores.size();
      for(int i = 0; i < N_BARALHOS; i++){
         for(final var carta : Baralho.of().getCartas()){
            if(carta.getValorCarta().equals(ValorCarta.TRES) && qntCartasARemover > 0){
               qntCartasARemover--;
               continue;
            }
            cartasDistribuidas.add(carta);
         }
      }

      Collections.shuffle(cartasDistribuidas);
      var jogadorAtual = 0;

      for(final var carta : cartasDistribuidas){
         jogadores.get(jogadorAtual).addCarta(carta);
         jogadorAtual = (jogadorAtual + 1) % jogadores.size();
      }
   }

   private void inicializarJogadores(List<Jogador> jogadores){
      this.jogadorAEscolher = new ArrayList<>(jogadores.stream().map(p -> p.getIdJogador()).toList());

   }

   public void Sorteando(){
      if(status != Status.ESPERANDO){
         throw new IllegalStateException("A sala não está em espera");
      }

      embaralharCartasEscolhidas();
      this.status = Status.SORTEANDO;
   }

   private void embaralharCartasEscolhidas(){
      if(jogadores.size()< configuracaoDeAcesso.getJogadoresMin()){
         throw new RuntimeException("Número de jogadores minimos não pode ser menor que 4");
      }
      for(final var valorCarta : ValorCarta.values()){
         cartasAEscolher.add(Carta.of(valorCarta, Naipe.PAUS));
      }

      Collections.shuffle(cartasAEscolher);
   }
   public static Sala of(final Jogador dono, final ConfiguracaoDeAcesso configuracaoDeAcesso){
      final var idSala = IDSala.of();
      final var linkSala = LinkSala.of();
      final var jogadores = new ArrayList<Jogador>();
      jogadores.add(dono);

      return new Sala(idSala, dono, linkSala, configuracaoDeAcesso, jogadores);
   }

   public Carta cartaEscolhida(final Jogador jogador){
      if(status != Status.SORTEANDO){
         throw new IllegalStateException("A sala não está sorteando");
      }
      if(!jogadorAEscolher.contains(jogador.getIdJogador())){
         throw new IllegalStateException("Não é possivel escolher uma carta para o jogador " + jogador.getIdJogador().getValor());
      }

      final var carta = cartasAEscolher.get(0);
      jogador.cartaEscolhida(carta);
      cartasAEscolher.remove(carta);
      jogadorAEscolher.remove(jogador.getIdJogador());

      return carta;
   }

   public void sortearJogadores(){
      if(jogadorAEscolher.isEmpty()){
         jogadores.sort(Jogador::compareTo);
         distribuindo();
      }
   }

   void distribuindo(){
      if(status != Status.SORTEANDO && status != Status.RODADA_FINALIZADA){
         throw new IllegalStateException("A sala não está sorteando ou emrodada finalizada");
      }

      this.status = Status.DISTRIBUINDO_CARTAS;
   }

   public void jogando(){
      if(status != Status.DISTRIBUINDO_CARTAS){
         throw new IllegalStateException("A sala não distribuiu as cartas");
      }

      status = Status.JOGANDO;
   }

   public void adicionarJogador(final Jogador  jogador){
      if(jogadores.size() >= configuracaoDeAcesso.getJogadoresMax()){
         throw new RuntimeException("A sala está cheia");
      }
      this.jogadores.add(jogador);
      this.jogadorAEscolher.add(jogador.getIdJogador());
   }

   public IDSala getIdSala() {
       return idSala;
   }

   public Jogador getDono() {
       return dono;
   }

   public LinkSala getLinkSala() {
       return linkSala;
   }

   public ConfiguracaoDeAcesso getConfiguracaoDeAcesso() {
       return configuracaoDeAcesso;
   }

   public List<Jogador> getJogadores() {
       return jogadores;
   }

   public Status getStatus() {
       return status;
   }

   public List<Carta> getCartasDistribuidas() {
       return Collections.unmodifiableList(cartasDistribuidas);
   }

   public enum Status{
      ESPERANDO, SORTEANDO, DISTRIBUINDO_CARTAS, JOGANDO, RODADA_FINALIZADA, JOGO_FINALIZADO
   }

}