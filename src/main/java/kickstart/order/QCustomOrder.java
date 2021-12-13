// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.order;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadataFactory;
import org.salespointframework.useraccount.QUserAccount;
import org.salespointframework.payment.PaymentMethod;
import com.querydsl.core.types.dsl.SimplePath;
import org.salespointframework.order.OrderStatus;
import org.salespointframework.order.QOrderLine;
import org.salespointframework.order.OrderLine;
import org.salespointframework.order.QOrderIdentifier;
import com.querydsl.core.types.dsl.EnumPath;
import kickstart.CustomerData.QKundendaten;
import java.time.LocalDateTime;
import com.querydsl.core.types.dsl.DateTimePath;
import org.salespointframework.order.QChargeLine;
import org.salespointframework.order.QChargeLine_AttachedChargeLine;
import org.salespointframework.order.ChargeLine;
import com.querydsl.core.types.dsl.ListPath;
import org.salespointframework.order.QOrder;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.EntityPathBase;

public class QCustomOrder extends EntityPathBase<CustomOrder>
{
    private static final long serialVersionUID = 374756363L;
    private static final PathInits INITS;
    public static final QCustomOrder customOrder;
    public final QOrder _super;
    public final ListPath<ChargeLine.AttachedChargeLine, QChargeLine_AttachedChargeLine> attachedChargeLines;
    public final ListPath<ChargeLine, QChargeLine> chargeLines;
    public final DateTimePath<LocalDateTime> dateCreated;
    public final QKundendaten kundendaten;
    public final EnumPath<CustomOrder.Lieferstatus> lieferstatus;
    public final QOrderIdentifier orderIdentifier;
    public final ListPath<OrderLine, QOrderLine> orderLines;
    public final EnumPath<OrderStatus> orderStatus;
    public final SimplePath<PaymentMethod> paymentMethod;
    public final QUserAccount userAccount;
    
    public QCustomOrder(final String variable) {
        this(CustomOrder.class, PathMetadataFactory.forVariable(variable), QCustomOrder.INITS);
    }
    
    public QCustomOrder(final Path<? extends CustomOrder> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), QCustomOrder.INITS));
    }
    
    public QCustomOrder(final PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, QCustomOrder.INITS));
    }
    
    public QCustomOrder(final PathMetadata metadata, final PathInits inits) {
        this(CustomOrder.class, metadata, inits);
    }
    
    public QCustomOrder(final Class<? extends CustomOrder> type, final PathMetadata metadata, final PathInits inits) {
        super((Class)type, metadata, inits);
        this.lieferstatus = (EnumPath<CustomOrder.Lieferstatus>)this.createEnum("lieferstatus", (Class)CustomOrder.Lieferstatus.class);
        this._super = new QOrder((Class)type, metadata, inits);
        this.attachedChargeLines = (ListPath<ChargeLine.AttachedChargeLine, QChargeLine_AttachedChargeLine>)this._super.attachedChargeLines;
        this.chargeLines = (ListPath<ChargeLine, QChargeLine>)this._super.chargeLines;
        this.dateCreated = (DateTimePath<LocalDateTime>)this._super.dateCreated;
        this.kundendaten = (inits.isInitialized("kundendaten") ? new QKundendaten(this.forProperty("kundendaten"), inits.get("kundendaten")) : null);
        this.orderIdentifier = this._super.orderIdentifier;
        this.orderLines = (ListPath<OrderLine, QOrderLine>)this._super.orderLines;
        this.orderStatus = (EnumPath<OrderStatus>)this._super.orderStatus;
        this.paymentMethod = (SimplePath<PaymentMethod>)this._super.paymentMethod;
        this.userAccount = this._super.userAccount;
    }
    
    static {
        INITS = PathInits.DIRECT2;
        customOrder = new QCustomOrder("customOrder");
    }
}
