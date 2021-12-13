// 
// Decompiled by Procyon v0.5.36
// 

package kickstart;

import javax.validation.constraints.NotEmpty;

class RegistrationForm
{
    @NotEmpty(message = "{RegistrationForm.name.NotEmpty}")
    private final String name;
    @NotEmpty(message = "{RegistrationForm.password.NotEmpty}")
    private final String password;
    @NotEmpty(message = "{RegistrationForm.mail.NotEmpty}")
    private final String mail;
    
    public RegistrationForm(final String name, final String password, final String address, final String mail) {
        this.name = name;
        this.password = password;
        this.mail = mail;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public String getMail() {
        return this.mail;
    }
}
