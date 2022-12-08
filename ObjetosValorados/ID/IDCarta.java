
import java.util.UUID;


public class IDCarta extends ID{
    
    private IDCarta(final UUID valor){
        super(valor);
    }

    public static IDCarta of(){
        return new IDCarta(UUID.randomUUID());
    }

    public static IDCarta of(final UUID valor){
        return new IDCarta(valor);
    }
}
