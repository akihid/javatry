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
import org.docksidestage.bizfw.basic.buyticket.types.TicketType;

/**
 * @author jflute
 * @author sato_akihide
 */
public class TicketBooth {

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
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========

    public TicketBuyResult buyOneDayPassport(int handedMoney) {
        return doBuyPassport(handedMoney, TicketType.ONE_DAY);
    }

    public TicketBuyResult buyTwoDayPassport(int handedMoney) {
        return doBuyPassport(handedMoney, TicketType.TWO_DAY);
    }

    public TicketBuyResult buyFourDayPassport(int handedMoney) {
        return doBuyPassport(handedMoney, TicketType.FOUR_DAY);
    }

    public TicketBuyResult buyNightOnlyTwoDayPassport(int handedMoney) {
        return doBuyPassport(handedMoney, TicketType.NIGHT_ONLY_TWO_DAY);
    }

    private TicketBuyResult doBuyPassport(int handedMoney, TicketType ticketType) {

        Price price = ticketType.getPrice();
        DaysType daysType = ticketType.getDaysType();

        // 共通の処理(売り切れ判定、所持金判定）
        assertTicketExists();
        assertHandMoneyEnough(handedMoney, price);

        for (int i = 0; i < daysType.getDays(); i++) {
            --quantity;
        }
        calcSalesProceeds(price);

        Ticket ticket;
        if (ticketType == TicketType.ONE_DAY) {
            ticket = new OneDayTicket(price, daysType);
        } else {
            ticket = new MultiDayTicket(price, daysType);
        }

        return new TicketBuyResult(ticket, handedMoney - price.getValue());
    }

    /**
     * quantityを参照し、0以下の場合、チケットが存在しないので例外出力
     */
    private void assertTicketExists() {
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
    }

    /**
     * 所持金とチケットの金額を比較。所持金が不足している場合、例外出力
     */
    private void assertHandMoneyEnough(int handedMoney, Price price) {
        int ticketPrice = price.getValue();
        if (handedMoney < ticketPrice) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
    }

    /**
     * チケットの金額を売上に追加する
     */
    private void calcSalesProceeds(Price price) {
        int ticketPrice = price.getValue();
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
