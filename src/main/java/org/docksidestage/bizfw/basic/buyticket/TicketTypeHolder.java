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

/**
 * @author jflute
 * @author sato_akihide
 */
public class TicketTypeHolder {

    public static final TicketTypeHolder ONE_DAY_TYPE =
            new TicketTypeHolder(TicketTypeHolder.Price.ONE_DAY, TicketTypeHolder.TicketDaysType.ONE_DAY);
    
    public static final TicketTypeHolder TWO_DAY_TYPE =
            new TicketTypeHolder(TicketTypeHolder.Price.TWO_DAY, TicketTypeHolder.TicketDaysType.TWO_DAY);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final Price price;
    private final TicketDaysType type;

    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200;

    public enum TicketDaysType {
        ONE_DAY(1), TWO_DAY(2);

        private int days;

        private TicketDaysType(int days) {
            this.days = days;
        }

        public int getDays() {
            return days;
        }
    }

    public enum Price {
        ONE_DAY(ONE_DAY_PRICE), TWO_DAY(TWO_DAY_PRICE);

        private int value;

        private Price(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketTypeHolder(Price price, TicketDaysType oneDay) {
        this.price = price;
        this.type = oneDay;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public Price getPrice() {
        return this.price;
    }

    public int getPriceValue() {
        return this.price.getValue();
    }

    //
    public TicketDaysType getType() {
        return this.type;
    }

}
