// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.lieferantenverwaltung;

import org.springframework.util.Assert;
import java.util.ArrayList;
import javax.persistence.OneToMany;
import kickstart.katalog.Moebelstueck;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class Lieferant
{
    private String name;
    private boolean aktiv;
    @Id
    @GeneratedValue
    public long id;
    @OneToMany
    private List<Moebelstueck> moebelAngebotLieferant;
    
    public Lieferant(final String name, final boolean aktiv) {
        this.moebelAngebotLieferant = new ArrayList<Moebelstueck>();
        Assert.hasText(name, "Lieferantenname must not be null or empty");
        this.name = name;
        this.setAktivStatus(aktiv);
    }
    
    private Lieferant() {
        this.moebelAngebotLieferant = new ArrayList<Moebelstueck>();
    }
    
    public void switchAktivStatus() {
        if (this.getAktivStatus()) {
            this.setAktivStatus(false);
        }
        else {
            this.setAktivStatus(true);
        }
    }
    
    public void addMoebelstueck(final Moebelstueck moebelstueck) {
        this.moebelAngebotLieferant.add(moebelstueck);
        System.out.print("Moebelstueck has been added");
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public boolean getAktivStatus() {
        return this.aktiv;
    }
    
    public void setAktivStatus(final boolean aktiv) {
        this.aktiv = aktiv;
        System.out.print(invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Z)Ljava/lang/String;, this.getName(), this.getAktivStatus()));
    }
    
    public List<Moebelstueck> getMoebelAngebotLieferant() {
        return this.moebelAngebotLieferant;
    }
}
