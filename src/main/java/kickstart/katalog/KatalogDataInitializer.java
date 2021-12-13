// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.katalog;

import org.slf4j.LoggerFactory;
import java.util.List;
import javax.money.MonetaryAmount;
import org.salespointframework.quantity.Metric;
import org.javamoney.moneta.Money;
import org.salespointframework.core.Currencies;
import org.springframework.util.Assert;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;
import org.salespointframework.core.DataInitializer;

@Order(29)
@Component
public class KatalogDataInitializer implements DataInitializer
{
    private static final Logger LOG;
    private final Katalog katalog;
    
    KatalogDataInitializer(final Katalog katalog) {
        Assert.notNull((Object)katalog, "Katalog darf nicht leer sein!");
        this.katalog = katalog;
    }
    
    public void initialize() {
        if (this.katalog.findAll().iterator().hasNext()) {
            return;
        }
        KatalogDataInitializer.LOG.info("Creating default catalog entries.");
        this.katalog.save((Object)new Moebelstueck("brauner Tisch", (MonetaryAmount)Money.of((Number)90, Currencies.EURO), Metric.UNIT, Moebelstueck.ProductType.PIECE, "braun"));
        this.katalog.save((Object)new Moebelstueck("gelber Kindertisch", (MonetaryAmount)Money.of((Number)60, Currencies.EURO), Metric.UNIT, Moebelstueck.ProductType.PIECE, "gelb", "yellowtable1.jpg", "yellowtable2.jpg", "yellowtable3.jpg"));
        this.katalog.save((Object)new Moebelstueck("blauer Plastiktisch", (MonetaryAmount)Money.of((Number)12, Currencies.EURO), Metric.UNIT, Moebelstueck.ProductType.PIECE, "blau", "bluetable1.jpg", "bluetable2.jpg", "bluetable3.jpg"));
        this.katalog.save((Object)new Moebelstueck("Grasgr\u00fcner Tisch", (MonetaryAmount)Money.of((Number)42, Currencies.EURO), Metric.UNIT, Moebelstueck.ProductType.PIECE, "gr\u00fcn", "greentable1.jpg", "greentable2.jpg", "greentable3.jpg"));
        this.katalog.save((Object)new Moebelstueck("gelber Tisch", (MonetaryAmount)Money.of((Number)60, Currencies.EURO), Metric.UNIT, Moebelstueck.ProductType.PIECE, "gelb"));
        this.katalog.save((Object)new Moebelstueck("blauer Tisch", (MonetaryAmount)Money.of((Number)12, Currencies.EURO), Metric.UNIT, Moebelstueck.ProductType.PIECE, "blau"));
        this.katalog.save((Object)new Moebelstueck("gr\u00fcner Tisch", (MonetaryAmount)Money.of((Number)42, Currencies.EURO), Metric.UNIT, Moebelstueck.ProductType.PIECE, "gr\u00fcn"));
        this.katalog.save((Object)new Moebelstueck("Fliesentisch", (MonetaryAmount)Money.of((Number)5, Currencies.EURO), Metric.UNIT, Moebelstueck.ProductType.PIECE, "braun geflie\u00dft"));
        this.katalog.save((Object)new Moebelstueck("Castingtisch", (MonetaryAmount)Money.of((Number)60, Currencies.EURO), Metric.UNIT, Moebelstueck.ProductType.PIECE, "lederschwarz"));
        this.katalog.save((Object)new Moebelstueck("Eichentisch", (MonetaryAmount)Money.of((Number)1000, Currencies.EURO), Metric.UNIT, Moebelstueck.ProductType.PIECE, "blau"));
        this.katalog.save((Object)new Moebelstueck("Operationstisch", (MonetaryAmount)Money.of((Number)300, Currencies.EURO), Metric.UNIT, Moebelstueck.ProductType.PIECE, "wei\u00df"));
        this.katalog.save((Object)new Moebelset("Tischset", (MonetaryAmount)Money.of((Number)80, Currencies.EURO), Metric.UNIT, Moebelstueck.ProductType.SET, "gr\u00fcn", this.katalog.findAll().toList()));
    }
    
    static {
        LOG = LoggerFactory.getLogger((Class)KatalogDataInitializer.class);
    }
}
