// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.katalog;

import java.util.Iterator;
import java.util.function.Supplier;
import org.javamoney.moneta.Money;
import org.salespointframework.core.Currencies;
import javax.money.MonetaryAmount;
import org.salespointframework.catalog.ProductIdentifier;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class Katalogverwaltung
{
    private final Katalog katalog;
    
    public Katalogverwaltung(final Katalog katalog) {
        this.katalog = katalog;
    }
    
    public MonetaryAmount calculateVerkaufspreis(final List<ProductIdentifier> ids) {
        final MonetaryAmount lieferantenpreis = (MonetaryAmount)Money.of((Number)0, Currencies.EURO);
        for (final ProductIdentifier id : ids) {
            lieferantenpreis.add(this.katalog.findById((Object)id).orElseGet(null).getPrice());
        }
        return lieferantenpreis;
    }
}
