// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.katalog;

import javax.money.MonetaryOperator;
import javax.money.Monetary;
import org.salespointframework.quantity.Metric;
import javax.persistence.ManyToOne;
import kickstart.lieferantenverwaltung.Lieferant;
import javax.money.MonetaryAmount;
import javax.persistence.Entity;
import org.salespointframework.catalog.Product;

@Entity
public class Moebelstueck extends Product
{
    private String imageeins;
    private String imagezwei;
    private String imagedrei;
    private String colour;
    private ProductType type;
    private MonetaryAmount lieferantenPreis;
    @ManyToOne
    private Lieferant lieferant;
    private Boolean sichtbar;
    
    protected Moebelstueck() {
        this.imageeins = "placeholder.jpg";
        this.imagezwei = "placeholder.jpg";
        this.imagedrei = "placeholder.jpg";
    }
    
    public Moebelstueck(final String name, final MonetaryAmount lieferantenPrice, final Metric metric, final ProductType type, final String colour) {
        super(name, lieferantenPrice, metric);
        this.imageeins = "placeholder.jpg";
        this.imagezwei = "placeholder.jpg";
        this.imagedrei = "placeholder.jpg";
        this.sichtbar = true;
        this.lieferantenPreis = lieferantenPrice;
        this.setPrice(this.calculateVerkaufspreis(lieferantenPrice));
        this.type = type;
        this.colour = colour;
    }
    
    public Moebelstueck(final String name, final MonetaryAmount lieferantenPrice, final Metric metric, final ProductType type, final String colour, final String imageeins) {
        super(name, lieferantenPrice, metric);
        this.imageeins = "placeholder.jpg";
        this.imagezwei = "placeholder.jpg";
        this.imagedrei = "placeholder.jpg";
        this.lieferantenPreis = lieferantenPrice;
        this.setPrice(this.calculateVerkaufspreis(lieferantenPrice));
        this.type = type;
        this.sichtbar = true;
        this.colour = colour;
        this.imageeins = imageeins;
    }
    
    public Moebelstueck(final String name, final MonetaryAmount lieferantenPrice, final Metric metric, final ProductType type, final String colour, final String imageeins, final String imagezwei) {
        super(name, lieferantenPrice, metric);
        this.imageeins = "placeholder.jpg";
        this.imagezwei = "placeholder.jpg";
        this.imagedrei = "placeholder.jpg";
        this.lieferantenPreis = lieferantenPrice;
        this.setPrice(this.calculateVerkaufspreis(lieferantenPrice));
        this.type = type;
        this.sichtbar = true;
        this.colour = colour;
        this.imageeins = imageeins;
        this.imagezwei = imagezwei;
    }
    
    public Moebelstueck(final String name, final MonetaryAmount lieferantenPrice, final Metric metric, final ProductType type, final String colour, final String imageeins, final String imagezwei, final String imagedrei) {
        super(name, lieferantenPrice, metric);
        this.imageeins = "placeholder.jpg";
        this.imagezwei = "placeholder.jpg";
        this.imagedrei = "placeholder.jpg";
        this.sichtbar = true;
        this.lieferantenPreis = lieferantenPrice;
        this.setPrice(this.calculateVerkaufspreis(lieferantenPrice));
        this.type = type;
        this.colour = colour;
        this.imageeins = imageeins;
        this.imagezwei = imagezwei;
        this.imagedrei = imagedrei;
        this.sichtbar = true;
    }
    
    public String getColour() {
        return this.colour;
    }
    
    public MonetaryAmount calculateVerkaufspreis(final MonetaryAmount einkaufspreis) {
        return einkaufspreis.multiply(2.05).with((MonetaryOperator)Monetary.getDefaultRounding());
    }
    
    public ProductType getType() {
        return this.type;
    }
    
    public Lieferant getLieferant() {
        return this.lieferant;
    }
    
    public void setLieferant(final Lieferant lieferant) {
        this.lieferant = lieferant;
    }
    
    public MonetaryAmount getLieferantenPreis() {
        return this.lieferantenPreis;
    }
    
    public String getImageeins() {
        return this.imageeins;
    }
    
    public String getImagezwei() {
        return this.imagezwei;
    }
    
    public String getImagedrei() {
        return this.imagedrei;
    }
    
    public void setSichtbar(final Boolean sichtbar) {
        this.sichtbar = sichtbar;
    }
    
    public Boolean getSichtbar() {
        return this.sichtbar;
    }
    
    public enum ProductType
    {
        PIECE, 
        SET;
        
        private static /* synthetic */ ProductType[] $values() {
            return new ProductType[] { ProductType.PIECE, ProductType.SET };
        }
        
        static {
            $VALUES = $values();
        }
    }
}
