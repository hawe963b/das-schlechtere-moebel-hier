// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.CustomerData;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class Bankdaten
{
    @Id
    @GeneratedValue
    private long id;
    private String iban;
    
    private Bankdaten() {
    }
    
    public Bankdaten(final String iban) {
        this.iban = iban;
    }
    
    public String getIban() {
        return this.iban;
    }
}
