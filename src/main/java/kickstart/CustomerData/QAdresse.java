// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.CustomerData;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.EntityPathBase;

public class QAdresse extends EntityPathBase<Adresse>
{
    private static final long serialVersionUID = -1002421701L;
    public static final QAdresse adresse;
    public final NumberPath<Integer> hausnummer;
    public final NumberPath<Long> id;
    public final StringPath ort;
    public final NumberPath<Integer> plz;
    public final StringPath street;
    public final StringPath zusatz;
    
    public QAdresse(final String variable) {
        super((Class)Adresse.class, PathMetadataFactory.forVariable(variable));
        this.hausnummer = (NumberPath<Integer>)this.createNumber("hausnummer", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.ort = this.createString("ort");
        this.plz = (NumberPath<Integer>)this.createNumber("plz", (Class)Integer.class);
        this.street = this.createString("street");
        this.zusatz = this.createString("zusatz");
    }
    
    public QAdresse(final Path<? extends Adresse> path) {
        super(path.getType(), path.getMetadata());
        this.hausnummer = (NumberPath<Integer>)this.createNumber("hausnummer", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.ort = this.createString("ort");
        this.plz = (NumberPath<Integer>)this.createNumber("plz", (Class)Integer.class);
        this.street = this.createString("street");
        this.zusatz = this.createString("zusatz");
    }
    
    public QAdresse(final PathMetadata metadata) {
        super((Class)Adresse.class, metadata);
        this.hausnummer = (NumberPath<Integer>)this.createNumber("hausnummer", (Class)Integer.class);
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.ort = this.createString("ort");
        this.plz = (NumberPath<Integer>)this.createNumber("plz", (Class)Integer.class);
        this.street = this.createString("street");
        this.zusatz = this.createString("zusatz");
    }
    
    static {
        adresse = new QAdresse("adresse");
    }
}
