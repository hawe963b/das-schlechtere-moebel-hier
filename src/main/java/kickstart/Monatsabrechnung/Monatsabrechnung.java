// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.Monatsabrechnung;

import java.util.Date;
import org.springframework.mail.SimpleMailMessage;
import org.salespointframework.support.RecordingMailSender;
import java.util.Map;
import org.javamoney.moneta.Money;
import org.salespointframework.core.Currencies;
import java.util.Optional;
import java.util.Iterator;
import java.util.List;
import org.salespointframework.order.OrderLine;
import kickstart.lieferantenverwaltung.Lieferant;
import org.salespointframework.catalog.ProductIdentifier;
import org.salespointframework.time.Interval;
import java.time.YearMonth;
import java.util.concurrent.TimeUnit;
import org.springframework.scheduling.annotation.Scheduled;
import javax.money.MonetaryAmount;
import java.util.HashMap;
import java.time.LocalDate;
import org.springframework.util.Assert;
import org.salespointframework.time.BusinessTime;
import kickstart.lieferantenverwaltung.Lieferantenverwaltung;
import org.salespointframework.order.Order;
import org.salespointframework.order.OrderManagement;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class Monatsabrechnung
{
    private final OrderManagement<Order> om;
    private final Lieferantenverwaltung lieferantenverwaltung;
    private final BusinessTime bm;
    private int lastMonth;
    private int year;
    private int finaldayLa;
    private int prMonth;
    private int prYear;
    private int finaldayPr;
    
    public Monatsabrechnung(final OrderManagement<Order> om, final Lieferantenverwaltung lieferantenverwaltung, final BusinessTime bm) {
        this.finaldayLa = 0;
        this.finaldayPr = 0;
        Assert.notNull((Object)om, "Order Management must not be null");
        Assert.notNull((Object)lieferantenverwaltung, "lieferantenverwaltung must not be null");
        Assert.notNull((Object)bm, "Buisinesstime must not be null");
        this.om = om;
        this.lieferantenverwaltung = lieferantenverwaltung;
        this.bm = bm;
    }
    
    @Scheduled(fixedDelay = 24L, timeUnit = TimeUnit.HOURS)
    public void scheduleFixedDelayTask() {
        final LocalDate today = this.bm.getTime().toLocalDate();
        System.out.println(invokedynamic(makeConcatWithConstants:(Ljava/time/LocalDate;)Ljava/lang/String;, today));
        System.out.println(invokedynamic(makeConcatWithConstants:(I)Ljava/lang/String;, today.getDayOfMonth()));
        if (today.getDayOfMonth() == 1) {
            this.getDates(today);
            final HashMap<String, MonetaryAmount> sales_last = this.getSales(LocalDate.of(this.year, this.lastMonth, 1), LocalDate.of(this.year, this.lastMonth, this.finaldayLa));
            final HashMap<String, MonetaryAmount> sales_previous = this.getSales(LocalDate.of(this.prYear, this.prMonth, 1), LocalDate.of(this.prYear, this.prMonth, this.finaldayPr));
            this.sendMailToBoss(sales_last, sales_previous);
        }
    }
    
    private void getDates(final LocalDate da) {
        if (da.getMonthValue() == 1) {
            this.lastMonth = 12;
            this.prMonth = 11;
            this.year = da.getYear() - 1;
            this.prYear = this.year;
        }
        else if (da.getMonthValue() == 2) {
            this.lastMonth = 1;
            this.prMonth = 12;
            this.year = da.getYear();
            this.prYear = da.getYear() - 1;
        }
        else {
            this.lastMonth = da.getMonthValue() - 1;
            this.prMonth = da.getMonthValue() - 2;
            this.year = da.getYear();
            this.prYear = da.getYear();
        }
        YearMonth verymonth = YearMonth.of(this.year, this.lastMonth);
        this.finaldayLa = verymonth.lengthOfMonth();
        verymonth = YearMonth.of(this.prYear, this.prMonth);
        this.finaldayPr = verymonth.lengthOfMonth();
    }
    
    private HashMap<String, MonetaryAmount> getSales(final LocalDate startDate, final LocalDate finalDate) {
        final HashMap<String, MonetaryAmount> sales = new HashMap<String, MonetaryAmount>();
        final List<Order> bestellungen = (List<Order>)this.om.findBy(Interval.from(startDate.atStartOfDay()).to(finalDate.atTime(23, 59, 59, 999999999))).toList();
        for (final Order order : bestellungen) {
            for (final ProductIdentifier ident : order.getOrderLines().map(elem -> elem.getProductIdentifier())) {
                final Optional<OrderLine> product = order.getOrderLines().filter(o -> o.getProductIdentifier().equals((Object)ident)).get().findFirst();
                if (!this.lieferantenverwaltung.findLieferantOfMoebelstuckByProdID(ident).isEmpty() && !product.isEmpty()) {
                    MonetaryAmount val = sales.get(this.lieferantenverwaltung.findLieferantOfMoebelstuckByProdID(ident).get().toString());
                    if (val == null) {
                        val = product.get().getPrice();
                    }
                    else {
                        val = product.get().getPrice().add(val);
                    }
                    sales.put("lieferant", val);
                }
            }
        }
        return sales;
    }
    
    private void sendMailToBoss(final HashMap<String, MonetaryAmount> sales_last, final HashMap<String, MonetaryAmount> sales_previous) {
        MonetaryAmount sum_last = (MonetaryAmount)Money.of((Number)0, Currencies.EURO);
        MonetaryAmount sum_prev = (MonetaryAmount)Money.of((Number)0, Currencies.EURO);
        String text = invokedynamic(makeConcatWithConstants:(Ljava/time/LocalDate;Ljava/time/LocalDate;)Ljava/lang/String;, LocalDate.of(this.year, this.lastMonth, 1), LocalDate.of(this.year, this.lastMonth, this.finaldayLa));
        for (final Map.Entry<String, MonetaryAmount> set : sales_last.entrySet()) {
            text = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;, text, (String)set.getKey(), set.getValue());
            sum_last = sum_last.add((MonetaryAmount)set.getValue());
        }
        text = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, text, sum_last.toString());
        text = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/time/LocalDate;Ljava/time/LocalDate;)Ljava/lang/String;, text, LocalDate.of(this.prYear, this.prMonth, 1), LocalDate.of(this.prYear, this.prMonth, this.finaldayPr));
        for (final Map.Entry<String, MonetaryAmount> set : sales_previous.entrySet()) {
            text = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;, text, (String)set.getKey(), set.getValue());
            sum_prev = sum_prev.add((MonetaryAmount)set.getValue());
        }
        text = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, text, sum_prev.toString());
        text = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, text);
        for (final Map.Entry<String, MonetaryAmount> set2 : sales_last.entrySet()) {
            for (final Map.Entry<String, MonetaryAmount> set3 : sales_previous.entrySet()) {
                if (set2.getKey().equalsIgnoreCase(set3.getKey())) {
                    if (set3.getValue().isGreaterThan((MonetaryAmount)set2.getValue())) {
                        text = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;Ljavax/money/MonetaryAmount;)Ljava/lang/String;, text, (String)set2.getKey(), set3.getValue().subtract((MonetaryAmount)set2.getValue()));
                    }
                    else if (set3.getValue().isLessThan((MonetaryAmount)set2.getValue())) {
                        text = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;Ljavax/money/MonetaryAmount;)Ljava/lang/String;, text, (String)set2.getKey(), set2.getValue().subtract((MonetaryAmount)set3.getValue()));
                    }
                    else {
                        text = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, text, (String)set2.getKey());
                    }
                }
            }
        }
        if (sum_last.isGreaterThan(sum_prev)) {
            text = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljavax/money/MonetaryAmount;)Ljava/lang/String;, text, sum_last.subtract(sum_prev));
        }
        else if (sum_last.isLessThan(sum_prev)) {
            text = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljavax/money/MonetaryAmount;)Ljava/lang/String;, text, sum_prev.subtract(sum_last));
        }
        else {
            text = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, text);
        }
        final RecordingMailSender sender = new RecordingMailSender();
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("Geschaeftsfuehrer@moebel-hier.de");
        message.setFrom("moebelshop@moebel-hier.de");
        message.setReplyTo("moebelshop@moebel-hier.de");
        message.setSentDate(new Date());
        message.setSubject("Monatsabrechnung");
        message.setText(text);
        sender.send(message);
    }
}
