// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.lieferantenverwaltung;

import org.slf4j.LoggerFactory;
import java.util.Iterator;
import kickstart.katalog.Moebelstueck;
import org.springframework.util.Assert;
import kickstart.katalog.Katalog;
import org.slf4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.salespointframework.core.DataInitializer;

@Component
@Order(30)
public class LieferantDataInitializer implements DataInitializer
{
    private static final Logger LOG;
    final Lieferantenverwaltung lieferantenverwaltung;
    final LieferantenRepository lieferantenRepository;
    final Katalog katalog;
    
    public LieferantDataInitializer(final LieferantenRepository lieferantenRepository, final Katalog katalog, final Lieferantenverwaltung lieferantenverwaltung) {
        Assert.notNull((Object)lieferantenRepository, "LieferantenRepository must not be null!");
        Assert.notNull((Object)katalog, "katalog must not be null!");
        Assert.notNull((Object)lieferantenverwaltung, "Lieferantenverwaltung must not be null!");
        this.lieferantenRepository = lieferantenRepository;
        this.katalog = katalog;
        this.lieferantenverwaltung = lieferantenverwaltung;
    }
    
    public void initialize() {
        if (this.lieferantenRepository.findAll().iterator().hasNext()) {
            return;
        }
        LieferantDataInitializer.LOG.info("Creating default Lieferanten.");
        final Lieferant klaus = new Lieferant("Schreinerei Klaus Trophobie", true);
        final Lieferant volker = new Lieferant("Caj\u00f3nst\u00fchle Vokler Racho", true);
        final Lieferant popo = new Lieferant("POPO Sitzm\u00f6bel", true);
        this.lieferantenRepository.save((Object)klaus);
        this.lieferantenRepository.save((Object)popo);
        this.lieferantenRepository.save((Object)volker);
        for (final Moebelstueck m : this.katalog.findAll().toList()) {
            if (Math.random() > 0.66) {
                this.lieferantenverwaltung.addMoebelToLieferant(popo, m);
            }
            else if (Math.random() >= 0.5) {
                this.lieferantenverwaltung.addMoebelToLieferant(klaus, m);
            }
            else {
                this.lieferantenverwaltung.addMoebelToLieferant(volker, m);
            }
        }
    }
    
    static {
        LOG = LoggerFactory.getLogger((Class)LieferantDataInitializer.class);
    }
}
