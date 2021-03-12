/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

import org.docksidestage.bizfw.basic.buyticket.TicketTypeHolder.Price;
import org.docksidestage.bizfw.basic.buyticket.TicketTypeHolder.TicketDaysType;

/**
 * @author jflute
 * @author sato_akihide
 */
public class MultiDayTicket implements Ticket1 {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final Price displayPrice;
    private final TicketDaysType type;
    private int remainingDays = 0;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public MultiDayTicket(Price ticketPrice, TicketDaysType tikectType) {
        this.displayPrice = ticketPrice;
        this.type = tikectType;
        this.remainingDays = tikectType.getDays();
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    public void doInPark() {
        // 残日数が０の場合、入場不可
        if (remainingDays <= 0) {
            throw new IllegalStateException("日数０");
        }
        this.remainingDays--;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========

    public int getDisplayPrice() {
        return displayPrice.getValue();
    }

    public TicketDaysType getType() {
        return this.type;
    }
}
