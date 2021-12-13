// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.CustomerData;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class Kundendaten
{
    @Id
    @GeneratedValue
    private long id;
    @OneToOne(cascade = { CascadeType.ALL })
    private PersonlicheDaten personlicheDaten;
    @OneToOne(cascade = { CascadeType.ALL })
    private Adresse adresse;
    @OneToOne(cascade = { CascadeType.ALL })
    private Bankdaten bankdaten;
    
    private Kundendaten() {
    }
    
    public Kundendaten(final PersonlicheDaten personlicheDaten, final Adresse adresse, final Bankdaten bankdaten) {
        this.personlicheDaten = personlicheDaten;
        this.adresse = adresse;
        this.bankdaten = bankdaten;
    }
    
    public PersonlicheDaten getPersonlicheDaten() {
        return this.personlicheDaten;
    }
    
    public Adresse getAdresse() {
        return this.adresse;
    }
    
    public Bankdaten getBankdaten() {
        return this.bankdaten;
    }
}
