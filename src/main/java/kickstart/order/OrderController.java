// 
// Decompiled by Procyon v0.5.36
// 

package kickstart.order;

import org.slf4j.LoggerFactory;
import org.salespointframework.order.OrderIdentifier;
import org.springframework.data.domain.Pageable;
import org.salespointframework.catalog.ProductIdentifier;
import org.salespointframework.order.OrderLine;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.salespointframework.payment.PaymentMethod;
import org.salespointframework.payment.Cash;
import kickstart.CustomerData.Kundendaten;
import kickstart.CustomerData.Bankdaten;
import kickstart.CustomerData.Adresse;
import kickstart.CustomerData.PersonlicheDaten;
import javax.validation.constraints.Email;
import org.salespointframework.useraccount.web.LoggedIn;
import org.salespointframework.useraccount.UserAccount;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.salespointframework.order.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.salespointframework.catalog.Product;
import org.salespointframework.quantity.Quantity;
import org.springframework.web.bind.annotation.RequestParam;
import kickstart.katalog.Moebelstueck;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.salespointframework.order.Cart;
import org.springframework.util.Assert;
import kickstart.lieferantenverwaltung.Lieferantenverwaltung;
import kickstart.katalog.Katalog;
import org.salespointframework.useraccount.UserAccountManagement;
import org.salespointframework.order.OrderManagement;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.stereotype.Controller;

@Controller
@SessionAttributes({ "cart" })
public class OrderController
{
    private static final Logger LOG;
    private final OrderManagement<CustomOrder> om;
    private final UserAccountManagement userAccountManagement;
    private final Katalog katalog;
    private final Lieferantenverwaltung lieferantenverwaltung;
    
    public OrderController(final OrderManagement<CustomOrder> orderManagement, final UserAccountManagement userAccountManagement, final Katalog katalog, final Lieferantenverwaltung lieferantenverwaltung) {
        Assert.notNull((Object)orderManagement, "OrderManagement must not be null!");
        this.om = orderManagement;
        Assert.notNull((Object)userAccountManagement, "UserAccountManagement must not be null!");
        this.userAccountManagement = userAccountManagement;
        this.katalog = katalog;
        this.lieferantenverwaltung = lieferantenverwaltung;
    }
    
    @ModelAttribute("cart")
    Cart initordercart() {
        return new Cart();
    }
    
    @PostMapping({ "/OrderCart" })
    String addItem(@RequestParam("pid") final Moebelstueck item, @RequestParam("number") final int number, @ModelAttribute final Cart cart) {
        cart.addOrUpdateItem((Product)item, Quantity.of((long)number));
        return "redirect:/addcart";
    }
    
    @PostMapping({ "/editCart" })
    String editItem(@RequestParam("pid") final Moebelstueck item, @RequestParam("number") final int number, @ModelAttribute final Cart cart, @ModelAttribute final Order order) {
        cart.addOrUpdateItem((Product)item, Quantity.of((long)number));
        System.out.println(invokedynamic(makeConcatWithConstants:(Ljava/lang/Class;Lorg/salespointframework/order/OrderIdentifier;)Ljava/lang/String;, order.getClass(), order.getId()));
        return "redirect:/orderedit/{order}";
    }
    
    @GetMapping({ "/addcustomertoorder" })
    String adddCustomerToOrder() {
        return "addcustomertoorder";
    }
    
    @PostMapping({ "/createorder" })
    String createOrder(@ModelAttribute final Cart cart, @LoggedIn final Optional<UserAccount> userAccount, @RequestParam("vorname") final String vorname, @RequestParam("nachname") final String nachname, @RequestParam("email") @Email final String email, @RequestParam("tel") final int tel, @RequestParam("ort") final String ort, @RequestParam("plz") final int plz, @RequestParam("street") final String street, @RequestParam("number") final int nr, @RequestParam("plz") final String zusatz, @RequestParam("iban") final String iban) {
        final PersonlicheDaten benutzerdaten = new PersonlicheDaten(vorname, nachname, email, tel);
        final Adresse adresse = new Adresse(ort, plz, street, nr, zusatz);
        final Bankdaten bankdaten = new Bankdaten(iban);
        final Kundendaten kundendaten = new Kundendaten(benutzerdaten, adresse, bankdaten);
        final CustomOrder order;
        return userAccount.map(account -> {
            order = new CustomOrder(account, (PaymentMethod)Cash.CASH, kundendaten);
            cart.addItemsTo((Order)order);
            cart.clear();
            OrderController.LOG.info(invokedynamic(makeConcatWithConstants:(Lkickstart/order/CustomOrder;Ljavax/money/MonetaryAmount;)Ljava/lang/String;, order, order.getTotal()));
            order.setLieferstatus(CustomOrder.Lieferstatus.IN);
            this.om.save((Order)order);
            order.sendMailIn(order.getKundendaten(), order.getTotal(), order.getId());
            return "redirect:/order";
        }).orElse("redirect:/addcart");
    }
    
    @PostMapping({ "/nextLieferstatus" })
    String nextLieferstatus(@RequestParam("orderid") final CustomOrder order) {
        if (order.getLieferstatus() != "Bestellung eingetroffen") {
            order.setLieferstatus(order.nextStatus(order.getLieferstatus()));
            this.om.save((Order)order);
        }
        if (order.getLieferstatus() == "Bestellung eingetroffen") {
            order.sendMailArrived(order.getKundendaten());
        }
        return "redirect:/order";
    }
    
    @PostMapping({ "/orderpay" })
    String payOrder(@RequestParam("orderid") final CustomOrder order) {
        this.om.payOrder((Order)order);
        return "redirect:/order";
    }
    
    @PostMapping({ "/ordercomplete" })
    String completeOrder(@RequestParam("orderid") final CustomOrder order) {
        if (order.getOrderStatus().toString().equals("PAID")) {
            this.om.completeOrder((Order)order);
        }
        return "redirect:/order";
    }
    
    @DeleteMapping({ "/ordercancel" })
    String cancelOrder(@RequestParam("orderid") final CustomOrder order) {
        this.om.cancelOrder((Order)order, "canceled by employee");
        return "redirect:/order";
    }
    
    @DeleteMapping({ "/orderdelete" })
    String deleteOrder(@RequestParam("orderid") final CustomOrder order) {
        this.om.delete((Order)order);
        return "redirect:/order";
    }
    
    @GetMapping({ "/orderdetails/{orderid}" })
    String orderDetails(@PathVariable("orderid") final CustomOrder order, final Model model) {
        model.addAttribute("order", (Object)order);
        return "orderdetails";
    }
    
    @PostMapping({ "/changeQuantity" })
    String changeQuantity(@RequestParam("quantity") final int quantity, @RequestParam("orderid") final CustomOrder order, @RequestParam("lineid") final OrderLine lineid, @RequestParam("productid") final ProductIdentifier productid) {
        final Moebelstueck product = this.katalog.findById((Object)productid).orElse(null);
        order.remove(lineid);
        order.addOrderLine((Product)product, Quantity.of((long)quantity));
        this.om.save((Order)order);
        return invokedynamic(makeConcatWithConstants:(Lorg/salespointframework/order/OrderIdentifier;)Ljava/lang/String;, order.getId());
    }
    
    @DeleteMapping({ "/deleteOrderLine" })
    String deleteOrderLine(@RequestParam("orderid") final CustomOrder order, @RequestParam("lineid") final OrderLine line) {
        order.remove(line);
        this.om.save((Order)order);
        return invokedynamic(makeConcatWithConstants:(Lorg/salespointframework/order/OrderIdentifier;)Ljava/lang/String;, order.getId());
    }
    
    @PostMapping({ "/extendOrder" })
    String extendOrder(@RequestParam("orderid") final CustomOrder order, @ModelAttribute final Cart cart) {
        cart.addItemsTo((Order)order);
        cart.clear();
        this.om.save((Order)order);
        return invokedynamic(makeConcatWithConstants:(Lorg/salespointframework/order/OrderIdentifier;)Ljava/lang/String;, order.getId());
    }
    
    @GetMapping({ "/order" })
    String orders(final Model model) {
        model.addAttribute("ordersavailable", (Object)this.om.findAll(Pageable.ofSize(10)));
        return "order";
    }
    
    @GetMapping({ "/resetcart" })
    String resetCart(@ModelAttribute final Cart cart) {
        cart.clear();
        return "redirect:/addcart";
    }
    
    @PostMapping({ "/orderfind" })
    String orderfind(@RequestParam("idgiven") final OrderIdentifier idgiven, final Model model) {
        final Optional<CustomOrder> order = (Optional<CustomOrder>)this.om.get(idgiven);
        System.out.println(idgiven.toString());
        if (order.isPresent()) {
            model.addAttribute("order", this.om.get(idgiven).get());
            return "orderdetails";
        }
        return "redirect:/order";
    }
    
    @GetMapping({ "/addcart" })
    String addCart(final Model model) {
        model.addAttribute("katalog", (Object)this.lieferantenverwaltung.getMoebelstueckeAktiverLieferanten());
        model.addAttribute("ordersavailable", (Object)this.om.findAll(Pageable.ofSize(10)));
        return "addcart";
    }
    
    static {
        LOG = LoggerFactory.getLogger((Class)OrderController.class);
    }
}
