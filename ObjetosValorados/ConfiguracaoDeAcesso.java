import java.beans.Visibility;

public class ConfiguracaoDeAcesso implements ObjetoValorado{
    private final static int JOGADORES_MIN = 4;
    private final int jogadoresMax;
    private final int tempoParaOProximoJogador;
    private final Visibilidade visibilidade;

    private ConfiguracaoDeAcesso(final int jogadoresMax, final int tempoParaOProximoJogador,  final Visibilidade visibilidade){
        this.jogadoresMax = jogadoresMax;
        this.tempoParaOProximoJogador = tempoParaOProximoJogador;
        this.visibilidade = visibilidade;
    }

    public static ConfiguracaoDeAcesso ofPublic(final int jogadoresMax){
        return ConfiguracaoDeAcesso.of(jogadoresMax, Visibilidade.PUBLICO);
    }

    public static ConfiguracaoDeAcesso ofPrivate(final int jogadoresMax){
        return ConfiguracaoDeAcesso.of(jogadoresMax, Visibilidade.PRIVADO);
    }

    public static ConfiguracaoDeAcesso of(final int jogadoresMax, Visibilidade visibilidade){
        if(jogadoresMax < ConfiguracaoDeAcesso.JOGADORES_MIN){
            throw new RuntimeException("número de jogadores máximos não pode ser menor que 4");
        }

        if(jogadoresMax > 13){
            throw new RuntimeException("número de jogadores máximos atingido");
        }
        return new ConfiguracaoDeAcesso(jogadoresMax, 15, visibilidade);
    }

    public static int getJogadoresMin() {
        return JOGADORES_MIN;
    }

    public int getJogadoresMax() {
        return jogadoresMax;
    }

    public int getTempoParaOProximoJogador() {
        return tempoParaOProximoJogador;
    }

    public Visibilidade getVisibilidade() {
        return visibilidade;
    }

    public enum Visibilidade{
        PUBLICO, PRIVADO
    }
}
