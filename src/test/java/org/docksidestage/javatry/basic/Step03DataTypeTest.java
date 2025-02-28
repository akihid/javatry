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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of data type. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step03DataTypeTest extends PlainTestCase {

    // ===================================================================================
    //                                                                          Basic Type
    //                                                                          ==========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_datatype_basicType() {
        String sea = "mystic";
        Integer land = 416;
        LocalDate piari = LocalDate.of(2001, 9, 4);
        LocalDateTime bonvo = LocalDateTime.of(2001, 9, 4, 12, 34, 56);
        Boolean dstore = true;
        BigDecimal amba = new BigDecimal("9.4");

        piari = piari.plusDays(1); // 9/5
        land = piari.getYear(); //2001y
        bonvo = bonvo.plusMonths(1); //2001/10
        land = bonvo.getMonthValue(); //10
        land--; //9
        if (dstore) {
            BigDecimal addedDecimal = amba.add(new BigDecimal(land)); //9.4 + 9 = 18.4
            sea = String.valueOf(addedDecimal);
        }
        log(sea); // your answer? => 18.4
    }

    // ===================================================================================
    //                                                                           Primitive
    //                                                                           =========
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_datatype_primitive() {
        byte sea = 127; // max
        short land = 32767; // max
        int piari = 2147483647; // max
        long bonvo = 9223372036854775807L; // max
        float dstore = 2147483647.1f; // 2.14748365E9
        double amba = 2.3d;
        char miraco = 'a';
        boolean dohotel = miraco == 'a'; // true
        if (dohotel && dstore >= piari) { //floatとintの比較
            bonvo = sea;
            land = (short) bonvo;
            bonvo = piari;
            sea = (byte) land;
            if (amba == 2.3D) {
                sea = (byte) amba; //byteにキャスト→2(8ビットの範囲で-128～127までの整数)
            }
        }
        if (dstore > piari) {
            sea = 0;
        }
        log(sea); // your answer? => 0 →2
        // why?
        // dstore == piariがtrue。intとfloatって同じ値になるの？言語仕様。
        // intとfloatの比較の場合、intに寄せられる。
        // intとdoubleの比較の場合、doubuleに寄せられる。
        // BigDecimal.valueOf(piari).toPlainString()すると同じ値になってるっぽい。。。わからぬ。

    }

    // ===================================================================================
    //                                                                              Object
    //                                                                              ======
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_datatype_object() {
        St3ImmutableStage stage = new St3ImmutableStage("hangar");
        String sea = stage.getStageName();
        log(sea); // your answer? => 　hangar
        // コンストラクタでstageNameに設定した値をそのままgetしてるだけ？
    }

    private static class St3ImmutableStage {

        private final String stageName;

        public St3ImmutableStage(String stageName) {
            this.stageName = stageName;
        }

        public String getStageName() {
            return stageName;
        }

        //        public void setStageName(String stageName) {
        //            this.stageName = stageName;
        //        }
    }
}
