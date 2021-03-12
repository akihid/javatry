package org.docksidestage.bizfw.basic.buyticket;

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
