// 
// Decompiled by Procyon v0.5.36
// 

package kickstart;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.Errors;
import javax.validation.Valid;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.Password;
import org.springframework.util.Assert;
import org.salespointframework.useraccount.UserAccountManagement;
import org.springframework.stereotype.Controller;

@Controller
public class RegisterController
{
    private UserAccountManagement uam;
    
    RegisterController(final UserAccountManagement uam) {
        this.uam = null;
        Assert.notNull((Object)uam, "UserAccountManagement must not be null!");
        (this.uam = uam).create("ma1", Password.UnencryptedPassword.of("123"), "ma1@moebelshop.de", new Role[] { Role.of("MA") });
        uam.create("gesch\u00e4ftsf\u00fchrermirko", Password.UnencryptedPassword.of("JexamLord"), "Mirko@moebelshop.de", new Role[] { Role.of("MA") });
    }
    
    @PostMapping({ "/register" })
    String registerNew(@Valid final RegistrationForm form, final Errors result) {
        if (result.hasErrors()) {
            return "register";
        }
        this.uam.create(form.getName(), Password.UnencryptedPassword.of(form.getPassword()), form.getMail(), new Role[] { Role.of("MA") });
        return "redirect:/";
    }
    
    @GetMapping({ "/register" })
    String register(final Model model, final RegistrationForm form) {
        return "register";
    }
}
