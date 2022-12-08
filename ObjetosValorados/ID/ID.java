
import java.util.Objects;
import java.util.UUID;

public abstract class ID implements ObjetoValorado{
    private final UUID valor;

    protected ID(final UUID valor){
        this.valor = valor;
    }

    public String getValor(){
        return valor.toString();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        ID id = (ID) o;
        return valor.equals(id.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
