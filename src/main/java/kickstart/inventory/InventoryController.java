// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.inventory;

import org.springframework.web.bind.annotation.PostMapping;
import org.salespointframework.quantity.Quantity;
import org.springframework.web.bind.annotation.RequestParam;
import org.salespointframework.inventory.InventoryItemIdentifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.salespointframework.inventory.UniqueInventory;
import org.springframework.stereotype.Controller;

@Controller
class InventoryController
{
    private final UniqueInventory<UniqueInventoryItem> inventory;
    
    InventoryController(final UniqueInventory<UniqueInventoryItem> inventory) {
        Assert.notNull((Object)inventory, "inventory must not be null");
        this.inventory = inventory;
    }
    
    @GetMapping({ "/stock" })
    String stock(final Model model) {
        model.addAttribute("stock", (Object)this.inventory.findAll());
        return "stock";
    }
    
    @PostMapping({ "/restock" })
    String restock(@RequestParam("itemid") final InventoryItemIdentifier itemid) {
        final UniqueInventoryItem item = this.inventory.findById((Object)itemid).get();
        this.inventory.save((Object)item.increaseQuantity(Quantity.of(10L)));
        return "redirect:/stock";
    }
}
