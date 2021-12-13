// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.lieferantenverwaltung;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.util.Assert;
import org.springframework.stereotype.Controller;

@Controller
public class LieferantenController
{
    final LieferantenRepository lieferantenRepository;
    final Lieferantenverwaltung lieferantenverwaltung;
    
    public LieferantenController(final LieferantenRepository lieferantenRepository, final Lieferantenverwaltung lieferantenverwaltung) {
        Assert.notNull((Object)lieferantenRepository, "LieferantenRepository must not be null");
        Assert.notNull((Object)lieferantenverwaltung, "LieferantenVerwaltung must not be null");
        this.lieferantenverwaltung = lieferantenverwaltung;
        this.lieferantenRepository = lieferantenRepository;
    }
    
    @PostMapping({ "/changeAktivStatus" })
    String changeAktivStatus(@RequestParam("lieferantenId") final long iDzu\u00c4ndernderLieferant) {
        this.lieferantenverwaltung.invertAktivStatus(iDzu\u00c4ndernderLieferant);
        return "redirect:/lieferanten";
    }
    
    @GetMapping({ "/lieferanten" })
    public String lieferanten(final Model model) {
        model.addAttribute("lieferanten", (Object)this.lieferantenverwaltung.getAllLieferanten());
        return "lieferanten";
    }
}
