package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author jflute
 * @author sato_akihide
 */
public interface Ticket {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    //    final Price displayPrice;
    //    final TicketDaysType type;
    //    boolean alreadyIn;

    void doInPark();

    int getDisplayPrice();
}
