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

public class QPersonlicheDaten extends EntityPathBase<PersonlicheDaten>
{
    private static final long serialVersionUID = -1390388410L;
    public static final QPersonlicheDaten personlicheDaten;
    public final StringPath email;
    public final NumberPath<Long> id;
    public final StringPath nachname;
    public final StringPath name;
    public final NumberPath<Integer> tel;
    public final StringPath vorname;
    
    public QPersonlicheDaten(final String variable) {
        super((Class)PersonlicheDaten.class, PathMetadataFactory.forVariable(variable));
        this.email = this.createString("email");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.nachname = this.createString("nachname");
        this.name = this.createString("name");
        this.tel = (NumberPath<Integer>)this.createNumber("tel", (Class)Integer.class);
        this.vorname = this.createString("vorname");
    }
    
    public QPersonlicheDaten(final Path<? extends PersonlicheDaten> path) {
        super(path.getType(), path.getMetadata());
        this.email = this.createString("email");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.nachname = this.createString("nachname");
        this.name = this.createString("name");
        this.tel = (NumberPath<Integer>)this.createNumber("tel", (Class)Integer.class);
        this.vorname = this.createString("vorname");
    }
    
    public QPersonlicheDaten(final PathMetadata metadata) {
        super((Class)PersonlicheDaten.class, metadata);
        this.email = this.createString("email");
        this.id = (NumberPath<Long>)this.createNumber("id", (Class)Long.class);
        this.nachname = this.createString("nachname");
        this.name = this.createString("name");
        this.tel = (NumberPath<Integer>)this.createNumber("tel", (Class)Integer.class);
        this.vorname = this.createString("vorname");
    }
    
    static {
        personlicheDaten = new QPersonlicheDaten("personlicheDaten");
    }
}
