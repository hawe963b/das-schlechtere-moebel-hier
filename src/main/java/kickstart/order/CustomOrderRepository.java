// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.order;

import org.springframework.data.util.Streamable;
import kickstart.CustomerData.Kundendaten;
import org.salespointframework.order.OrderIdentifier;
import org.springframework.data.repository.Repository;

interface CustomOrderRepository extends Repository<CustomOrder, OrderIdentifier>
{
    Streamable<CustomOrder> findByKundendaten(final Kundendaten kundendaten);
}
