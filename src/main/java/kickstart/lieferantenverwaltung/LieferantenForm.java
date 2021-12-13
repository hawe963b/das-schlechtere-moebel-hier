// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.lieferantenverwaltung;

import javax.validation.constraints.NotBlank;

public class LieferantenForm
{
    @NotBlank
    private final String name;
    private final boolean aktivStatus;
    
    public LieferantenForm(final String name, final boolean aktivStatus) {
        this.name = name;
        this.aktivStatus = aktivStatus;
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean getAktivStatus() {
        return this.aktivStatus;
    }
    
    Lieferant toNewLieferant() {
        return new Lieferant(this.getName(), this.getAktivStatus());
    }
}
