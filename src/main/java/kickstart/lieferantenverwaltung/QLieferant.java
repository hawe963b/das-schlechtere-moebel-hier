// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.lieferantenverwaltung;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.StringPath;
import kickstart.katalog.QMoebelstueck;
import kickstart.katalog.Moebelstueck;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.EntityPathBase;

public class QLieferant extends EntityPathBase<Lieferant>
{
    private static final long serialVersionUID = 1463300634L;
    public static final QLieferant lieferant;
    public final BooleanPath aktiv;
    public final NumberPath<Long> id;
    public final ListPath<Moebelstueck, QMoebelstueck> moebelAngebotLieferant;
    public final StringPath name;
    
    public QLieferant(final String variable) {
        super((Class)Lieferant.class, PathMetadataFactory.forVariable(variable));
        this.aktiv = this.createBoolean("aktiv");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.moebelAngebotLieferant = (ListPath<Moebelstueck, QMoebelstueck>)this.createList("moebelAngebotLieferant", (Class)Moebelstueck.class, (Class)QMoebelstueck.class, PathInits.DIRECT2);
        this.name = this.createString("name");
    }
    
    public QLieferant(final Path<? extends Lieferant> path) {
        super(path.getType(), path.getMetadata());
        this.aktiv = this.createBoolean("aktiv");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.moebelAngebotLieferant = (ListPath<Moebelstueck, QMoebelstueck>)this.createList("moebelAngebotLieferant", (Class)Moebelstueck.class, (Class)QMoebelstueck.class, PathInits.DIRECT2);
        this.name = this.createString("name");
    }
    
    public QLieferant(final PathMetadata metadata) {
        super((Class)Lieferant.class, metadata);
        this.aktiv = this.createBoolean("aktiv");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.moebelAngebotLieferant = (ListPath<Moebelstueck, QMoebelstueck>)this.createList("moebelAngebotLieferant", (Class)Moebelstueck.class, (Class)QMoebelstueck.class, PathInits.DIRECT2);
        this.name = this.createString("name");
    }
    
    static {
        lieferant = new QLieferant("lieferant");
    }
}
