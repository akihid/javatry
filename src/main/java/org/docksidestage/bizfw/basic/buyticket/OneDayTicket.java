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

import org.docksidestage.bizfw.basic.buyticket.types.DaysType;
import org.docksidestage.bizfw.basic.buyticket.types.Price;

/**
 * @author jflute
 * @author sato_akihide
 */
public class OneDayTicket implements Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final Price displayPrice;
    private final DaysType daysType;
    private boolean alreadyIn;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public OneDayTicket(Price ticketPrice, DaysType tikectType) {
        this.displayPrice = ticketPrice;
        this.daysType = tikectType;
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    public void doInPark() {
        if (alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        alreadyIn = true;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========

    public int getDisplayPrice() {
        return displayPrice.getValue();
    }

    public boolean isAlreadyIn() {
        return alreadyIn;
    }

    public DaysType getDaysType() {
        return this.daysType;
    }
}
