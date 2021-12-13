// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.CustomerData;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.core.types.dsl.EntityPathBase;

public class QBankdaten extends EntityPathBase<Bankdaten>
{
    private static final long serialVersionUID = 1201824848L;
    public static final QBankdaten bankdaten;
    public final StringPath iban;
    public final NumberPath<Long> id;
    
    public QBankdaten(final String variable) {
        super((Class)Bankdaten.class, PathMetadataFactory.forVariable(variable));
        this.iban = this.createString("iban");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
    }
    
    public QBankdaten(final Path<? extends Bankdaten> path) {
        super(path.getType(), path.getMetadata());
        this.iban = this.createString("iban");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
    }
    
    public QBankdaten(final PathMetadata metadata) {
        super((Class)Bankdaten.class, metadata);
        this.iban = this.createString("iban");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
    }
    
    static {
        bankdaten = new QBankdaten("bankdaten");
    }
}
