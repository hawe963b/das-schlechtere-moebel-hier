// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.lieferantenverwaltung;

import java.util.Optional;
import org.springframework.data.util.Streamable;
import org.springframework.data.repository.CrudRepository;

public interface LieferantenRepository extends CrudRepository<Lieferant, Long>
{
    Streamable<Lieferant> findAll();
    
    Optional<Lieferant> findByName(final String name);
}
