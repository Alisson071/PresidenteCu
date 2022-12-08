
import java.util.UUID;

public class IDSala extends ID{

    private IDSala(UUID valor){
        super(valor);
    }

    public static IDSala of(){
        return new IDSala(UUID.randomUUID());
    }

    public static IDSala of(UUID valor){
        return new IDSala(valor);
    }

    public static IDSala of(String valor){
        return new IDSala(UUID.fromString(valor));
    }
    
}
