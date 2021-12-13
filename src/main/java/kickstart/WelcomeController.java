// 
// Decompiled by Procyon v0.5.36
// 

package kickstart;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;

@Controller
public class WelcomeController
{
    private static final Logger LOG;
    
    @GetMapping({ "/" })
    public String index() {
        WelcomeController.LOG.info("*********************Welcome.***********************************");
        return "welcome";
    }
    
    static {
        LOG = LoggerFactory.getLogger((Class)WelcomeController.class);
    }
}
