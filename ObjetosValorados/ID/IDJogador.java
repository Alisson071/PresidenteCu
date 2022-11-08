package PresidenteCu.ID;
import java.util.UUID;

public class IDJogador extends ID{
    private IDJogador(final UUID valor){
        super(valor);
    }

    public static IDJogador of(){
        return new IDJogador(UUID.randomUUID());
    }

    public static IDJogador of(final UUID valor){
        return new IDJogador(valor);
    }
}
