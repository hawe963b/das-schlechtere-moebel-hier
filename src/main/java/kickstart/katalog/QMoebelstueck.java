// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.katalog;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.BooleanPath;
import org.salespointframework.catalog.QProductIdentifier;
import org.salespointframework.quantity.Metric;
import com.querydsl.core.types.dsl.EnumPath;
import javax.money.MonetaryAmount;
import com.querydsl.core.types.dsl.SimplePath;
import kickstart.lieferantenverwaltung.QLieferant;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.core.types.dsl.SetPath;
import org.salespointframework.catalog.QProduct;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.EntityPathBase;

public class QMoebelstueck extends EntityPathBase<Moebelstueck>
{
    private static final long serialVersionUID = 530548382L;
    private static final PathInits INITS;
    public static final QMoebelstueck moebelstueck;
    public final QProduct _super;
    public final SetPath<String, StringPath> categories;
    public final StringPath colour;
    public final StringPath imagedrei;
    public final StringPath imageeins;
    public final StringPath imagezwei;
    public final QLieferant lieferant;
    public final SimplePath<MonetaryAmount> lieferantenPreis;
    public final EnumPath<Metric> metric;
    public final StringPath name;
    public final SimplePath<MonetaryAmount> price;
    public final QProductIdentifier productIdentifier;
    public final BooleanPath sichtbar;
    public final EnumPath<Moebelstueck.ProductType> type;
    
    public QMoebelstueck(final String variable) {
        this(Moebelstueck.class, PathMetadataFactory.forVariable(variable), QMoebelstueck.INITS);
    }
    
    public QMoebelstueck(final Path<? extends Moebelstueck> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QMoebelstueck.INITS));
    }
    
    public QMoebelstueck(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QMoebelstueck.INITS));
    }
    
    public QMoebelstueck(final PathMetadata metadata, final PathInits inits) {
        this(Moebelstueck.class, metadata, inits);
    }
    
    public QMoebelstueck(final Class<? extends Moebelstueck> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.colour = this.createString("colour");
        this.imagedrei = this.createString("imagedrei");
        this.imageeins = this.createString("imageeins");
        this.imagezwei = this.createString("imagezwei");
        this.lieferantenPreis = (SimplePath<MonetaryAmount>)this.createSimple("lieferantenPreis", (Class)MonetaryAmount.class);
        this.sichtbar = this.createBoolean("sichtbar");
        this.type = (EnumPath<Moebelstueck.ProductType>)this.createEnum("type", (Class)Moebelstueck.ProductType.class);
        this._super = new QProduct((Class)type, metadata, inits);
        this.categories = (SetPath<String, StringPath>)this._super.categories;
        this.lieferant = (inits.isInitialized("lieferant") ? new QLieferant(this.forProperty("lieferant")) : null);
        this.metric = (EnumPath<Metric>)this._super.metric;
        this.name = this._super.name;
        this.price = (SimplePath<MonetaryAmount>)this._super.price;
        this.productIdentifier = this._super.productIdentifier;
    }
    
    static {
        INITS = PathInits.DIRECT2;
        moebelstueck = new QMoebelstueck("moebelstueck");
    }
}
