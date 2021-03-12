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
public class TicketBooth1 {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity = MAX_QUANTITY;
    private Integer salesProceeds;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth1() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========

    public TicketBuyResult buyOneDayPassport(int handedMoney) {

        return doBuyPassport(handedMoney, TicketTypeHolder.ONE_DAY_TYPE);

    }

    public TicketBuyResult buyTwoDayPassport(int handedMoney) {
        return doBuyPassport(handedMoney, TicketTypeHolder.TWO_DAY_TYPE);
    }

    private TicketBuyResult doBuyPassport(int handedMoney, TicketTypeHolder ticketType) {

        // 共通の処理(売り切れ判定、所持金判定）
        assertTicketExists();
        assertHandMoneyEnough(handedMoney, ticketType.getPriceValue());

        int ticketDays = ticketType.getType().getDays();
        for (int i = 0; i < ticketDays; i++) {
            --quantity;
        }
        calcSalesProceeds(ticketType.getPriceValue());

        return new TicketBuyResult(new Ticket(ticketType.getPrice(), ticketType.getType()), handedMoney - ticketType.getPriceValue());
    }

    private void assertTicketExists() {
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
    }

    private void assertHandMoneyEnough(int handedMoney, int ticketPrice) {
        if (handedMoney < ticketPrice) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
    }

    private void calcSalesProceeds(int ticketPrice) {
        if (salesProceeds != null) {
            salesProceeds = salesProceeds + ticketPrice;
        } else {
            salesProceeds = ticketPrice;
        }
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getQuantity() {
        return quantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
