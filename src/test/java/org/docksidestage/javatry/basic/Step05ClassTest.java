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
package org.docksidestage.javatry.basic;

import org.docksidestage.bizfw.basic.buyticket.MultiDayTicket;
import org.docksidestage.bizfw.basic.buyticket.OneDayTicket;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth.TicketShortMoneyException;
import org.docksidestage.bizfw.basic.buyticket.TicketBuyResult;
import org.docksidestage.bizfw.basic.buyticket.types.TicketType;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of class. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author sato_akihide
 */
public class Step05ClassTest extends PlainTestCase {
    /**
    * 指摘事項などのメモ
    * 宣言の場所も呼ばれる順番に合わせるべき
      チェックという言葉は曖昧（どっちに捉えるか）
      例外を投げるときによく使われる、assert。例）assertTicketExists()
      assert正しい状態（処理を続けるのに満たしているか）というメソッド名がよく使われる
      再利用の仕方
      処理の順番を再利用していない
      チェック処理を追加するときに困るから、チェック処理を外付けするべき
      処理そのものを抽象化するのもあり
      在庫を共有している（ワンデイとツーデイで分けてもよい）
      まるごと再利用できるようにとか（流れ全体を再利用できるように）→処理を呼ぶメソッドを中間にもう一個作る
      
      パブリックメソッドかプライベートメソッドかわかりにくい。先頭
      DoByPassport→do処理をするプライベートメソッド（innernalとかつけたり）
      
      購入価格と表示価格が必ず一緒とは限らない
      チケットの在庫を渡すのではなく、daysでOK
      逆算処理はするのはNG
      
      enumをTicketTypeクラスに分ける
      それとわかるprice・typeをもたせる（定義については調べてやる）
      
      
      2020/10/16
      プライベートメソッドの書き方は呼び出し順に書くべき。
      あるいはカテゴライズして、見やすくするべき。
      getIntValueという名前は変。valueに変更して、getValueに修正。
      ticketTypeの中にtypeがあるのはおかしいのでは・・・。
      TickeTDaysTypeとかのほうが明確。
      
      不要なコメント行は削除する必要あり。
      
      2021/04/09
      チケットデイズのタイプとチケットプライスのタイプに分けた模様
      命名がややこしい
      チケットデイズタイプのタイプはデイズタイプに治そう
      ゲットタイプもゲットデイズタイプに
      
      oneかそれ以外はまあ許容範囲
      宣言する際にスコープは短いように直前でやるべき
      
      チケットタイプホルダー自身をenumにして、それぞれも分けたほうがよい
      
      2021/05/14
      インターフェースでcommant + t 押せば、使用先がわかる
      抽象的なクラスとしてdoInParkできるかどうかがわからない（Ticket)
      使用可能か判断できるものをもたせてもいいのでは
      型パラメータを使用すると固くなる（クラスに対して型を渡す）具象クラスを使うように
      TicketBuyResult<MultiDayTicket>　List<String>
      
      チケットに夜チケットかどうかのオプションをつけるとかも面白そう 夜だったら何％オフにするとか。
    
    */
    // ===================================================================================
    //                                                                          How to Use
    //                                                                          ==========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_class_howToUse_basic() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(7400);
        int sea = booth.getQuantity();
        log(sea); // your answer? => 9
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_overpay() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? => 7400
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_nosales() {
        TicketBooth booth = new TicketBooth();
        Integer sea = booth.getSalesProceeds();
        log(sea); // your answer? => 0→null Integerの初期値はnull
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_class_howToUse_wrongQuantity() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // your answer? => Failed to buy one-day passport: money=7399 TicketShortMoneyExceptionの内容 10
    }

    private Integer doTest_class_ticket_wrongQuantity() {
        TicketBooth booth = new TicketBooth();
        int handedMoney = 7399;
        try {
            booth.buyOneDayPassport(handedMoney);
            fail("always exception but none");
        } catch (TicketShortMoneyException continued) {
            log("Failed to buy one-day passport: money=" + handedMoney, continued);
            // catchでこっちに来る
            // 7399とログ出力はされるはず

        }
        return booth.getQuantity(); //9を返す→変えてないから10になった
    }

    // ===================================================================================
    //                                                                           Let's fix
    //                                                                           =========
    /**
     * Fix the problem of ticket quantity reduction when short money. (Don't forget to fix also previous exercise answers) <br>
     * (お金不足でもチケットが減る問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_ticketQuantityReduction() {
        Integer sea = doTest_class_ticket_wrongQuantity();
        log(sea); // should be max quantity, visual check here 10
    }

    /**
     * Fix the problem of sales proceeds increased by handed money. (Don't forget to fix also previous exercise answers) <br>
     * (受け取ったお金の分だけ売上が増えていく問題をクラスを修正して解決しましょう (以前のエクササイズのanswerの修正を忘れずに))
     */
    public void test_class_letsFix_salesProceedsIncrease() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        Integer sea = booth.getSalesProceeds();
        log(sea); // should be same as one-day price, visual check here　7400
    }

    /**
     * Make method for buying two-day passport (price is 13200). (which can return change as method return value)
     * (TwoDayPassport (金額は13200) も買うメソッドを作りましょう (戻り値でお釣りをちゃんと返すように))
     */
    public void test_class_letsFix_makeMethod_twoday() {
        // comment out after making the method
        //        TicketBooth booth = new TicketBooth();
        //        int money = 14000;
        //        int change = booth.buyTwoDayPassport(money);
        //        Integer sea = booth.getSalesProceeds() + change;
        //        log(sea); // should be same as money
        //
        //        //         and show two-day passport quantity here
        //        log(booth.getQuantity());
    }

    /**
     * Recycle duplicate logics between one-day and two-day by e.g. private method in class. (And confirm result of both before and after) <br>
     * (OneDayとTwoDayで冗長なロジックがあったら、クラス内のprivateメソッドなどで再利用しましょう (修正前と修正後の実行結果を確認))
     */
    public void test_class_letsFix_refactor_recycle() {
        TicketBooth booth = new TicketBooth();
        booth.buyOneDayPassport(10000);
        log(booth.getQuantity(), booth.getSalesProceeds()); // should be same as before-fix
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Now you cannot get ticket if you buy one-day passport, so return Ticket class and do in-park. <br>
     * (OneDayPassportを買ってもチケットをもらえませんでした。戻り値でTicketクラスを戻すようにしてインしましょう)
     */
    public void test_class_moreFix_return_ticket() {
        //        // comment out after modifying the method
        //        TicketBooth booth = new TicketBooth();
        //        Ticket oneDayPassport = booth.buyOneDayPassport(10000);
        //        log(oneDayPassport.getDisplayPrice()); // should be same as one-day price
        //        log(oneDayPassport.isAlreadyIn()); // should be false
        //        oneDayPassport.doInPark();
        //        log(oneDayPassport.isAlreadyIn()); // should be true
    }

    /**
     * Now also you cannot get ticket if two-day passport, so return class that has ticket and change. <br>
     * (TwoDayPassportもチケットをもらえませんでした。チケットとお釣りを戻すクラスを作って戻すようにしましょう)
     */
    public void test_class_moreFix_return_whole() {
        // comment out after modifying the method
        //        TicketBooth booth = new TicketBooth();
        //        int handedMoney = 20000;
        //        TicketBuyResult twoDayPassportResult = booth.buyTwoDayPassport(handedMoney);
        //        Ticket_old twoDayPassport = twoDayPassportResult.getTicket();
        //        int change = twoDayPassportResult.getChange();
        //        log(twoDayPassport.getDisplayPrice() + change); // should be same as money
    }

    /**
     * Now you cannot judge ticket type "one-day or two-day?", so add method to judge it. <br>
     * (チケットをもらってもOneDayなのかTwoDayなのか区別が付きません。区別を付けられるメソッドを追加しましょう)
     */
    public void test_class_moreFix_type() {
        // your confirmation code here
        //        TicketBooth booth = new TicketBooth();
        //        TicketBuyResult oneDayPasssportResult = booth.buyOneDayPassport(10000);
        //        Ticket_old oneDayPassport = oneDayPassportResult.getTicket();
        //        log(oneDayPassport.getType().equals(TicketTypeHolder.TicketDaysType.ONE_DAY));
        //        log(booth.getQuantity());
        //        log(booth.getSalesProceeds());
        //
        //        TicketBooth another_booth = new TicketBooth();
        //        TicketBuyResult twoDayPassportResult = another_booth.buyTwoDayPassport(20000);
        //        Ticket_old twoDayPassport = twoDayPassportResult.getTicket();
        //
        //        log(twoDayPassport.getType().equals(TicketTypeHolder.TicketDaysType.ONE_DAY));
        //        log(another_booth.getQuantity());
        //        log(another_booth.getSalesProceeds());
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Now only one use with two-day passport, so split ticket in one-day and two-day class and use interface. <br>
     * <pre>
     * o change Ticket class to interface, define doInPark(), getDisplayPrice() in it
     * o make class for one-day and class for plural days (called implementation class)
     * o make implementation classes implement Ticket interface
     * o doInPark() of plural days can execute defined times
     * </pre>
     * (TwoDayのチケットが一回しか利用できません。OneDayとTwoDayのクラスを分けてインターフェースを使うようにしましょう)
     * <pre>
     * o Ticket をインターフェース(interface)にして、doInPark(), getDisplayPrice() を定義
     * o OneDay用のクラスと複数日用のクラスを作成 (実装クラスと呼ぶ)
     * o 実装クラスが Ticket を implements するように
     * o 複数日用のクラスでは、決められた回数だけ doInPark() できるように
     * </pre>
     */
    public void test_class_moreFix_useInterface() {
        // your confirmation code here
        TicketBooth booth = new TicketBooth();
        TicketBuyResult oneDayPassportResult = booth.buyOneDayPassport(10000);
        OneDayTicket oneDayPassport = (OneDayTicket) oneDayPassportResult.getTicket();

        log(oneDayPassport.getDaysType().equals(TicketType.ONE_DAY.getDaysType()));
        log(booth.getQuantity());
        log(booth.getSalesProceeds());

        TicketBooth another_booth = new TicketBooth();
        TicketBuyResult twoDayPassportResult = another_booth.buyTwoDayPassport(20000);
        MultiDayTicket twoDayPassport = (MultiDayTicket) twoDayPassportResult.getTicket();
        log("1回目");
        twoDayPassport.doInPark();
        log("2回目");
        twoDayPassport.doInPark();

        log(twoDayPassport.getDaysType().equals(TicketType.ONE_DAY.getDaysType()));
        log(another_booth.getQuantity());
        log(another_booth.getSalesProceeds());
    }

    /**
     * Fix it to be able to buy four-day passport (price is 22400). <br>
     * (FourDayPassport (金額は22400) のチケットも買えるようにしましょう)
     */
    public void test_class_moreFix_wonder() {
        // your confirmation code here
        TicketBooth booth = new TicketBooth();
        TicketBuyResult fourDayPassportResult = booth.buyFourDayPassport(30000);
        MultiDayTicket fourDayPassport = (MultiDayTicket) fourDayPassportResult.getTicket();

        log(fourDayPassport.getDaysType().equals(TicketType.FOUR_DAY.getDaysType()));
        log(booth.getQuantity());
        log(booth.getSalesProceeds());

        log("1回目");
        fourDayPassport.doInPark();
        log("2回目");
        fourDayPassport.doInPark();
        log("3回目");
        fourDayPassport.doInPark();
        log("4回目");
        fourDayPassport.doInPark();

        log(fourDayPassport.getDaysType().equals(TicketType.FOUR_DAY.getDaysType()));
        log(booth.getSalesProceeds());

        //        fourDayPassport.doInPark();
    }

    /**
     * Fix it to be able to buy night-only two-day passport (price is 7400). <br>
     * (NightOnlyTwoDayPassport (金額は7400) のチケットも買えるようにしましょう)
     */
    public void test_class_moreFix_wonder_night() {
        // your confirmation code here
        TicketBooth booth = new TicketBooth();
        TicketBuyResult nightOnlyTwoDayPassportResult = booth.buyNightOnlyTwoDayPassport(30000);
        MultiDayTicket nightOnlyTwoDayPassport = (MultiDayTicket) nightOnlyTwoDayPassportResult.getTicket();

        log(nightOnlyTwoDayPassport.getDaysType().equals(TicketType.NIGHT_ONLY_TWO_DAY.getDaysType()));
        log(booth.getSalesProceeds());
    }

    /**
     * Refactor if you want to fix (e.g. is it well-balanced name of method and variable?). <br>
     * (その他、気になるところがあったらリファクタリングしてみましょう (例えば、バランスの良いメソッド名や変数名になっていますか？))
     */
    public void test_class_moreFix_yourRefactoring() {
        // write confirmation code here
    }
}
