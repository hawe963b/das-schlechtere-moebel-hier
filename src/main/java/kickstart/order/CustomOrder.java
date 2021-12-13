// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.order;

import org.salespointframework.order.OrderIdentifier;
import javax.money.MonetaryAmount;
import java.util.Date;
import org.springframework.mail.SimpleMailMessage;
import org.salespointframework.support.RecordingMailSender;
import org.salespointframework.payment.PaymentMethod;
import org.salespointframework.useraccount.UserAccount;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import kickstart.CustomerData.Kundendaten;
import javax.persistence.Entity;
import org.salespointframework.order.Order;

@Entity
public class CustomOrder extends Order
{
    private Lieferstatus lieferstatus;
    @OneToOne(cascade = { CascadeType.ALL })
    private Kundendaten kundendaten;
    
    private CustomOrder() {
        final Lieferstatus lieferstatus = Lieferstatus.IN;
    }
    
    public CustomOrder(final UserAccount userAccount, final PaymentMethod paymentMethod, final Kundendaten kundendaten) {
        super(userAccount, paymentMethod);
        this.kundendaten = kundendaten;
    }
    
    public Kundendaten getKundendaten() {
        return this.kundendaten;
    }
    
    public String getLieferstatus() {
        if (this.lieferstatus == Lieferstatus.IN) {
            return "Bestellung eingegangen";
        }
        if (this.lieferstatus == Lieferstatus.PREPARE) {
            return "Bestellung wird vorbereitet";
        }
        if (this.lieferstatus == Lieferstatus.SEND) {
            return "Bestellung in Zustellung";
        }
        return "Bestellung eingetroffen";
    }
    
    public void setLieferstatus(final Lieferstatus ls) {
        this.lieferstatus = ls;
    }
    
    public Lieferstatus nextStatus(final String status) {
        if (status.equals("Bestellung eingegangen")) {
            return Lieferstatus.PREPARE;
        }
        if (status.equals("Bestellung wird vorbereitet")) {
            return Lieferstatus.SEND;
        }
        if (status.equals("Bestellung in Zustellung")) {
            return Lieferstatus.ARRIVED;
        }
        return Lieferstatus.ARRIVED;
    }
    
    public void setKundendaten(final Kundendaten kundendaten) {
        this.kundendaten = kundendaten;
    }
    
    public void sendMailArrived(final Kundendaten kd) {
        final String mailtext = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, kd.getPersonlicheDaten().getVorname(), kd.getPersonlicheDaten().getNachname());
        final RecordingMailSender sender = new RecordingMailSender();
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(kd.getPersonlicheDaten().getEmail());
        message.setFrom("moebelshop@moebel-hier.de");
        message.setReplyTo("moebelshop@moebel-hier.de");
        message.setSentDate(new Date());
        message.setSubject("Bestellung eingetroffen");
        message.setText(mailtext);
        sender.send(message);
    }
    
    public void sendMailIn(final Kundendaten kd, final MonetaryAmount preis, final OrderIdentifier id) {
        final String mailtext = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, kd.getPersonlicheDaten().getVorname(), kd.getPersonlicheDaten().getNachname(), preis.toString(), id.toString());
        final RecordingMailSender sender = new RecordingMailSender();
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(kd.getPersonlicheDaten().getEmail());
        message.setFrom("moebelshop@moebel-hier.de");
        message.setReplyTo("moebelshop@moebel-hier.de");
        message.setSentDate(new Date());
        message.setSubject("Bestellung eingegangen");
        message.setText(mailtext);
        sender.send(message);
    }
    
    public enum Lieferstatus
    {
        IN, 
        PREPARE, 
        SEND, 
        ARRIVED;
        
        private static /* synthetic */ Lieferstatus[] $values() {
            return new Lieferstatus[] { Lieferstatus.IN, Lieferstatus.PREPARE, Lieferstatus.SEND, Lieferstatus.ARRIVED };
        }
        
        static {
            $VALUES = $values();
        }
    }
}
