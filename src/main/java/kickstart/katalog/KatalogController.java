// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.katalog;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import kickstart.lieferantenverwaltung.Lieferantenverwaltung;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.salespointframework.inventory.UniqueInventory;
import org.springframework.stereotype.Controller;

@Controller
public class KatalogController
{
    private final Katalog katalog;
    private final UniqueInventory<UniqueInventoryItem> inventory;
    private final Lieferantenverwaltung lieferantenverwaltung;
    
    KatalogController(final Katalog katalog, final UniqueInventory<UniqueInventoryItem> inventory, final Lieferantenverwaltung lieferantenverwaltung) {
        Assert.notNull((Object)lieferantenverwaltung, "Lieferantenverwaltung can not be null");
        this.lieferantenverwaltung = lieferantenverwaltung;
        this.katalog = katalog;
        this.inventory = inventory;
    }
    
    @GetMapping({ "/katalog" })
    String katalog(final Model model) {
        model.addAttribute("katalog", (Object)this.lieferantenverwaltung.getSichtbareMoebelstueckeAktiverLieferanten());
        return "katalog";
    }
    
    @PostMapping({ "/ausblenden" })
    String ausblenden(@RequestParam("productid") final Moebelstueck moebelstueck) {
        moebelstueck.setSichtbar(false);
        this.katalog.save((Object)moebelstueck);
        return "redirect:/katalog";
    }
    
    @GetMapping({ "/produktdetails" })
    String produktdetails(@RequestParam("productid") final Moebelstueck moebelstueck, final Model model) {
        if (moebelstueck.getType() == Moebelstueck.ProductType.PIECE) {
            final Iterable<Moebelstueck> alternatives = this.katalog.findByLieferant(moebelstueck.getLieferant());
            model.addAttribute("moebelstueck", (Object)moebelstueck);
            model.addAttribute("Alternativen", (Object)alternatives);
            return "piecedetails";
        }
        if (moebelstueck.getType() == Moebelstueck.ProductType.SET) {
            model.addAttribute("moebelstueck", (Object)moebelstueck);
            return "setdetails";
        }
        return "redirect:/katalog";
    }
    
    public Katalog getKatalog() {
        return this.katalog;
    }
}
