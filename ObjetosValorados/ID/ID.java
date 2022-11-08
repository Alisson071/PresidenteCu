
import java.util.Objects;
import java.util.UUID;

public abstract class ID implements ObjetoValorado{
    private final UUID value;

    protected ID(final UUID value){
        this.value = value;
    }

    public String getValue(){
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        ID id = (ID) o;
        return value.equals(id.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
