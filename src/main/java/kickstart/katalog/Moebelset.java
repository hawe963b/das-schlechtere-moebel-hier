// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.katalog;

import java.util.Iterator;
import org.javamoney.moneta.Money;
import org.salespointframework.core.Currencies;
import org.salespointframework.quantity.Metric;
import java.util.ArrayList;
import javax.persistence.OneToMany;
import java.util.List;
import javax.persistence.ManyToOne;
import kickstart.lieferantenverwaltung.Lieferant;
import javax.money.MonetaryAmount;
import javax.persistence.Entity;

@Entity
public class Moebelset extends Moebelstueck
{
    private String colour;
    private MonetaryAmount lieferantenPreis;
    @ManyToOne
    private Lieferant lieferant;
    @OneToMany
    private List<Moebelstueck> moebels;
    
    private Moebelset() {
        this.moebels = new ArrayList<Moebelstueck>();
    }
    
    public Moebelset(final String name, final MonetaryAmount lieferantenPrice, final Metric metric, final ProductType type, final String colour, final List<Moebelstueck> moebels) {
        super(name, lieferantenPrice, metric, type, colour);
        this.moebels = new ArrayList<Moebelstueck>();
        this.lieferantenPreis = lieferantenPrice;
        this.setPrice(this.calculateVerkaufspreis(moebels));
        this.colour = colour;
        this.moebels = moebels;
    }
    
    @Override
    public String getColour() {
        return this.colour;
    }
    
    public MonetaryAmount calculateVerkaufspreis(final List<Moebelstueck> moebel) {
        MonetaryAmount lieferantenPrice = (MonetaryAmount)Money.of((Number)0, Currencies.EURO);
        for (final Moebelstueck mobel : moebel) {
            lieferantenPrice = lieferantenPrice.add(mobel.getPrice());
        }
        return lieferantenPrice;
    }
    
    @Override
    public Lieferant getLieferant() {
        return this.lieferant;
    }
    
    @Override
    public MonetaryAmount getLieferantenPreis() {
        return this.lieferantenPreis;
    }
    
    public List<Moebelstueck> getMoebels() {
        return this.moebels;
    }
    
    public void addMoebel(final Moebelstueck moebel) {
        this.moebels.add(moebel);
    }
}
