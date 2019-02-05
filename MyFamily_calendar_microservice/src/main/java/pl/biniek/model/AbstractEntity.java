package pl.biniek.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;


/**
 *
 * Klasa nadrzędna dla wszystkich klas encyjnych. Ustala wspólne metody:
 * toString() equals() hashCode()
 */
@MappedSuperclass
public abstract class AbstractEntity {
    
    protected static final long serialVersionUID = 1L;

    
    protected abstract Object getId();

    
    protected abstract Object getBusinessKey();



    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[id=" + getId() + ", key=" + getBusinessKey() +  "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        
        if(this.getClass().isAssignableFrom(obj.getClass())) {
            return this.getBusinessKey().equals(((AbstractEntity)obj).getBusinessKey());
        } else {
            return false;
        }

    }

    @Override
    public int hashCode() {
        return getBusinessKey().hashCode(); 
    }
}
