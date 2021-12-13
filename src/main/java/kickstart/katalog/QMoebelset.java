// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.katalog;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.BooleanPath;
import org.salespointframework.catalog.QProductIdentifier;
import com.querydsl.core.types.dsl.ListPath;
import org.salespointframework.quantity.Metric;
import com.querydsl.core.types.dsl.EnumPath;
import javax.money.MonetaryAmount;
import com.querydsl.core.types.dsl.SimplePath;
import kickstart.lieferantenverwaltung.QLieferant;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.core.types.dsl.SetPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.EntityPathBase;

public class QMoebelset extends EntityPathBase<Moebelset>
{
    private static final long serialVersionUID = 1341374653L;
    private static final PathInits INITS;
    public static final QMoebelset moebelset;
    public final QMoebelstueck _super;
    public final SetPath<String, StringPath> categories;
    public final StringPath colour;
    public final StringPath imagedrei;
    public final StringPath imageeins;
    public final StringPath imagezwei;
    public final QLieferant lieferant;
    public final SimplePath<MonetaryAmount> lieferantenPreis;
    public final EnumPath<Metric> metric;
    public final ListPath<Moebelstueck, QMoebelstueck> moebels;
    public final StringPath name;
    public final SimplePath<MonetaryAmount> price;
    public final QProductIdentifier productIdentifier;
    public final BooleanPath sichtbar;
    public final EnumPath<Moebelstueck.ProductType> type;
    
    public QMoebelset(final String variable) {
        this(Moebelset.class, PathMetadataFactory.forVariable(variable), QMoebelset.INITS);
    }
    
    public QMoebelset(final Path<? extends Moebelset> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QMoebelset.INITS));
    }
    
    public QMoebelset(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QMoebelset.INITS));
    }
    
    public QMoebelset(final PathMetadata metadata, final PathInits inits) {
        this(Moebelset.class, metadata, inits);
    }
    
    public QMoebelset(final Class<? extends Moebelset> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.colour = this.createString("colour");
        this.lieferantenPreis = (SimplePath<MonetaryAmount>)this.createSimple("lieferantenPreis", (Class)MonetaryAmount.class);
        this.moebels = (ListPath<Moebelstueck, QMoebelstueck>)this.createList("moebels", (Class)Moebelstueck.class, (Class)QMoebelstueck.class, PathInits.DIRECT2);
        this._super = new QMoebelstueck(type, metadata, inits);
        this.categories = this._super.categories;
        this.imagedrei = this._super.imagedrei;
        this.imageeins = this._super.imageeins;
        this.imagezwei = this._super.imagezwei;
        this.lieferant = (inits.isInitialized("lieferant") ? new QLieferant(this.forProperty("lieferant")) : null);
        this.metric = this._super.metric;
        this.name = this._super.name;
        this.price = this._super.price;
        this.productIdentifier = this._super.productIdentifier;
        this.sichtbar = this._super.sichtbar;
        this.type = this._super.type;
    }
    
    static {
        INITS = PathInits.DIRECT2;
        moebelset = new QMoebelset("moebelset");
    }
}
