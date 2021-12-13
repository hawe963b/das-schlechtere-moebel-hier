// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.katalog;

import java.util.Optional;
import org.springframework.data.util.Streamable;
import org.springframework.data.repository.CrudRepository;

public interface KatalogRepository extends CrudRepository<Moebelstueck, Long>
{
    Streamable<Moebelstueck> findAll();
    
    Optional<Moebelstueck> findByName(final String name);
    
    Optional<Moebelstueck> findByLieferant(final String lieferant);
}
