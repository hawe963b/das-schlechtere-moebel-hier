// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.CustomerData;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class Adresse
{
    @Id
    @GeneratedValue
    private long id;
    private String ort;
    private int plz;
    private String street;
    private int hausnummer;
    private String zusatz;
    
    private Adresse() {
    }
    
    public Adresse(final String ort, final int plz, final String street, final int hausnummer, final String zusatz) {
        this.ort = ort;
        this.plz = plz;
        this.street = street;
        this.hausnummer = hausnummer;
        this.zusatz = zusatz;
    }
    
    public String getOrt() {
        return this.ort;
    }
    
    public int getPlz() {
        return this.plz;
    }
    
    public String getStreet() {
        return this.street;
    }
    
    public int getHausnummer() {
        return this.hausnummer;
    }
    
    public String getZusatz() {
        return this.zusatz;
    }
}
