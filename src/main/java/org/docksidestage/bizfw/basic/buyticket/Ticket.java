package org.docksidestage.bizfw.basic.buyticket;

import org.docksidestage.bizfw.basic.buyticket.types.DaysType;

/**
 * @author jflute
 * @author sato_akihide
 */
public interface Ticket {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    void doInPark();

    int getDisplayPrice();

    DaysType getDaysType();
}
