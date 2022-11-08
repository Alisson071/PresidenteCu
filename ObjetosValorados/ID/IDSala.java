package PresidenteCu.ObjetosValorados.ID;
import java.util.UUID;

public class IDSala extends ID{

    private IDSala(UUID value){
        super(value);
    }

    public static IDSala of(){
        return new IDSala(UUID.randomUUID());
    }

    public static IDSala of(UUID value){
        return new IDSala(value);
    }
    
}
