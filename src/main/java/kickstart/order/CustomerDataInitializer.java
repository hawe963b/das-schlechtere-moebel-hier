// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.order;

import org.slf4j.LoggerFactory;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.Password;
import org.springframework.util.Assert;
import org.salespointframework.useraccount.UserAccountManagement;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.salespointframework.core.DataInitializer;

@Component
class CustomerDataInitializer implements DataInitializer
{
    private static final Logger LOG;
    private final UserAccountManagement userAccountManagement;
    
    CustomerDataInitializer(final UserAccountManagement userAccountManagement) {
        Assert.notNull((Object)userAccountManagement, "UserAccountManagement must not be null!");
        this.userAccountManagement = userAccountManagement;
    }
    
    public void initialize() {
        if (this.userAccountManagement.findByUsername("boss").isPresent()) {
            return;
        }
        CustomerDataInitializer.LOG.info("Creating default users and customers.");
        this.userAccountManagement.create("boss", Password.UnencryptedPassword.of("123"), new Role[] { Role.of("BOSS") });
        final UserAccount test = this.userAccountManagement.create("testdude", Password.UnencryptedPassword.of("234"), new Role[] { Role.of("WORKER") });
    }
    
    static {
        LOG = LoggerFactory.getLogger((Class)CustomerDataInitializer.class);
    }
}
