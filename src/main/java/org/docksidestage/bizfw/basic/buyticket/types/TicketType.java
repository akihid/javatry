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
package org.docksidestage.bizfw.basic.buyticket.types;

/**
 * @author jflute
 * @author sato_akihide
 */
public enum TicketType {

    ONE_DAY(Price.ONE_DAY, DaysType.ONE_DAY), //
    TWO_DAY(Price.TWO_DAY, DaysType.TWO_DAY), //
    FOUR_DAY(Price.FOUR_DAY, DaysType.FOUR_DAY), //
    NIGHT_ONLY_TWO_DAY(Price.NIGHT_ONLY_TWO_DAY, DaysType.TWO_DAY);

    private final Price price;
    private final DaysType daysType;

    private TicketType(Price price, DaysType daysType) {
        this.price = price;
        this.daysType = daysType;
    }

    public Price getPrice() {
        return price;
    }

    public DaysType getDaysType() {
        return daysType;
    }

}