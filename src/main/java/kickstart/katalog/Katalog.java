// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.katalog;

import kickstart.lieferantenverwaltung.Lieferant;
import org.springframework.data.domain.Sort;
import org.salespointframework.catalog.Catalog;

public interface Katalog extends Catalog<Moebelstueck>
{
    public static final Sort DEFAULT_SORT = Sort.by(new String[] { "productIdentifier" }).descending();
    
    Iterable<Moebelstueck> findByType(final Moebelstueck.ProductType type, final Sort sort);
    
    default Iterable<Moebelstueck> findByType(final Moebelstueck.ProductType type) {
        return this.findByType(type, Katalog.DEFAULT_SORT);
    }
    
    Iterable<Moebelstueck> findByLieferant(final Lieferant lieferant, final Sort sort);
    
    default Iterable<Moebelstueck> findByLieferant(final Lieferant lieferant) {
        return this.findByLieferant(lieferant, Katalog.DEFAULT_SORT);
    }
    
    Iterable<Moebelstueck> findByColour(final String colour, final Sort sort);
    
    default Iterable<Moebelstueck> findByColour(final String colour) {
        return this.findByColour(colour, Katalog.DEFAULT_SORT);
    }
}
