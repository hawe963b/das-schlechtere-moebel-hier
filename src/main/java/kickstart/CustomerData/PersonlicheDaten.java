// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.CustomerData;

import javax.validation.constraints.Email;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class PersonlicheDaten
{
    @Id
    @GeneratedValue
    private long id;
    private String vorname;
    private String nachname;
    @Email
    private String email;
    private int tel;
    private String name;
    
    private PersonlicheDaten() {
    }
    
    public PersonlicheDaten(final String vorname, final String nachname, final String email, final int tel) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.tel = tel;
        this.name = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, vorname, nachname);
    }
    
    public String getVorname() {
        return this.vorname;
    }
    
    public String getNachname() {
        return this.nachname;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public int getTel() {
        return this.tel;
    }
    
    public String getName() {
        return this.name;
    }
}
