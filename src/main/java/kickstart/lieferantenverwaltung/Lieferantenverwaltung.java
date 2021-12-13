// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.lieferantenverwaltung;

import java.util.Collection;
import org.salespointframework.catalog.ProductIdentifier;
import java.util.Optional;
import kickstart.katalog.Moebelstueck;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import org.springframework.util.Assert;
import kickstart.katalog.Katalog;
import org.springframework.stereotype.Service;

@Service
public class Lieferantenverwaltung
{
    private final LieferantenRepository lieferantenRepository;
    private final Katalog katalog;
    
    public Lieferantenverwaltung(final LieferantenRepository lieferantenRepository, final Katalog katalog) {
        Assert.notNull((Object)lieferantenRepository, "LieferantenRepository must not be null!");
        this.lieferantenRepository = lieferantenRepository;
        Assert.notNull((Object)katalog, "Katalog must not be null!");
        this.katalog = katalog;
    }
    
    public List<Lieferant> getAktiveLieferanten() {
        final List<Lieferant> aktiveLieferanten = new ArrayList<Lieferant>();
        for (final Lieferant lieferant : this.lieferantenRepository.findAll()) {
            if (lieferant.getAktivStatus()) {
                aktiveLieferanten.add(lieferant);
            }
        }
        return aktiveLieferanten;
    }
    
    public List<Lieferant> getAllLieferanten() {
        final List<Lieferant> Lieferanten = new ArrayList<Lieferant>();
        for (final Lieferant lieferant : this.lieferantenRepository.findAll()) {
            Lieferanten.add(lieferant);
        }
        return Lieferanten;
    }
    
    public Optional<Lieferant> findLieferantOfMoebelstuck(final Moebelstueck moebelstueck) {
        for (final Lieferant lieferant : this.lieferantenRepository.findAll()) {
            for (final Moebelstueck m : lieferant.getMoebelAngebotLieferant()) {
                if (m == moebelstueck) {
                    return Optional.of(lieferant);
                }
            }
        }
        final Optional<Lieferant> optionalerLieferant = Optional.empty();
        return optionalerLieferant;
    }
    
    public Optional<Moebelstueck> findMoebelstueckById(final ProductIdentifier id) {
        for (final Lieferant lieferant : this.lieferantenRepository.findAll()) {
            for (final Moebelstueck m : lieferant.getMoebelAngebotLieferant()) {
                if (m.getId() == id) {
                    return Optional.of(m);
                }
            }
        }
        return Optional.empty();
    }
    
    public Optional<Lieferant> findLieferantOfMoebelstuckByProdID(final ProductIdentifier prodId) {
        for (final Lieferant lieferant : this.lieferantenRepository.findAll()) {
            for (final Moebelstueck m : lieferant.getMoebelAngebotLieferant()) {
                if (m.getId() == prodId) {
                    return Optional.of(lieferant);
                }
            }
        }
        final Optional<Lieferant> optionalerLieferant = Optional.empty();
        return optionalerLieferant;
    }
    
    public void invertAktivStatus(final long iDzu\u00c4ndernderLieferant) {
        final Lieferant zu\u00c4ndernderLieferant = this.lieferantenRepository.findById((Object)iDzu\u00c4ndernderLieferant).get();
        zu\u00c4ndernderLieferant.switchAktivStatus();
        this.lieferantenRepository.save((Object)zu\u00c4ndernderLieferant);
    }
    
    public List<Moebelstueck> getMoebelstueckeAktiverLieferanten() {
        final List<Moebelstueck> aktiveMoebel = new ArrayList<Moebelstueck>();
        for (final Lieferant lieferant : this.lieferantenRepository.findAll()) {
            if (lieferant.getAktivStatus()) {
                aktiveMoebel.addAll(lieferant.getMoebelAngebotLieferant());
            }
        }
        return aktiveMoebel;
    }
    
    public List<Moebelstueck> getSichtbareMoebelstueckeAktiverLieferanten() {
        final List<Moebelstueck> aktiveMoebel = new ArrayList<Moebelstueck>();
        for (final Lieferant lieferant : this.lieferantenRepository.findAll()) {
            if (lieferant.getAktivStatus()) {
                for (final Moebelstueck m : lieferant.getMoebelAngebotLieferant()) {
                    if (m.getSichtbar()) {
                        aktiveMoebel.add(m);
                    }
                }
            }
        }
        return aktiveMoebel;
    }
    
    public void addMoebelToLieferant(final Lieferant lieferant, final Moebelstueck moebel) {
        moebel.setLieferant(lieferant);
        this.katalog.save((Object)moebel);
        lieferant.addMoebelstueck(moebel);
        this.lieferantenRepository.save((Object)lieferant);
    }
}
