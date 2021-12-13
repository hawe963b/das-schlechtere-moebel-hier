// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.CustomerData;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.EntityPathBase;

public class QKundendaten extends EntityPathBase<Kundendaten>
{
    private static final long serialVersionUID = -1385461149L;
    private static final PathInits INITS;
    public static final QKundendaten kundendaten;
    public final QAdresse adresse;
    public final QBankdaten bankdaten;
    public final NumberPath<Long> id;
    public final QPersonlicheDaten personlicheDaten;
    
    public QKundendaten(final String variable) {
        this(Kundendaten.class, PathMetadataFactory.forVariable(variable), QKundendaten.INITS);
    }
    
    public QKundendaten(final Path<? extends Kundendaten> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QKundendaten.INITS));
    }
    
    public QKundendaten(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QKundendaten.INITS));
    }
    
    public QKundendaten(final PathMetadata metadata, final PathInits inits) {
        this(Kundendaten.class, metadata, inits);
    }
    
    public QKundendaten(final Class<? extends Kundendaten> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.adresse = (inits.isInitialized("adresse") ? new QAdresse(this.forProperty("adresse")) : null);
        this.bankdaten = (inits.isInitialized("bankdaten") ? new QBankdaten(this.forProperty("bankdaten")) : null);
        this.personlicheDaten = (inits.isInitialized("personlicheDaten") ? new QPersonlicheDaten(this.forProperty("personlicheDaten")) : null);
    }
    
    static {
        INITS = PathInits.DIRECT2;
        kundendaten = new QKundendaten("kundendaten");
    }
}
