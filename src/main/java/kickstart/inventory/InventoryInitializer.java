// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.inventory;

import kickstart.katalog.Moebelstueck;
import org.salespointframework.quantity.Quantity;
import org.salespointframework.catalog.Product;
import org.springframework.util.Assert;
import kickstart.katalog.Katalog;
import org.salespointframework.inventory.UniqueInventoryItem;
import org.salespointframework.inventory.UniqueInventory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.salespointframework.core.DataInitializer;

@Component
@Order(40)
class InventoryInitializer implements DataInitializer
{
    private final UniqueInventory<UniqueInventoryItem> inventory;
    private final Katalog katalog;
    
    InventoryInitializer(final UniqueInventory<UniqueInventoryItem> inventory, final Katalog katalog) {
        Assert.notNull((Object)inventory, "Inventory must not be null!");
        Assert.notNull((Object)katalog, "Katalog must not be null!");
        this.inventory = inventory;
        this.katalog = katalog;
    }
    
    public void initialize() {
        this.katalog.findAll().forEach(item -> {
            if (this.inventory.findByProduct((Product)item).isEmpty()) {
                this.inventory.save((Object)new UniqueInventoryItem((Product)item, Quantity.of(10L)));
            }
        });
    }
}
