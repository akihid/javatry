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

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of method. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author sato_akihide
 */
public class Step04MethodTest extends PlainTestCase {

    // ===================================================================================
    //                                                                         Method Call
    //                                                                         ===========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_method_call_basic() {
        String sea = supplySomething();
        log(sea); // your answer? =>　over
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_call_many() {
        String sea = functionSomething("mystic");
        consumeSomething(supplySomething());
        runnableSomething();
        log(sea); // your answer? => mysmys
    }

    private String functionSomething(String name) {//何かを受け取って何かを返す
        String replaced = name.replace("tic", "mys");
        log("in function: {}", replaced);
        return replaced;
    }

    private String supplySomething() {//供給する、引数無しで値を返す
        String sea = "over";
        log("in supply: {}", sea);
        return sea;
    }

    private void consumeSomething(String sea) {//消費する、呼ぶだけ
        log("in consume: {}", sea.replace("over", "mystic"));
    }

    private void runnableSomething() {//実行できるもの
        String sea = "outofshadow";
        log("in runnable: {}", sea);
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_object() {
        St4MutableStage mutable = new St4MutableStage();
        int sea = 904;
        boolean land = false;
        helloMutable(sea - 4, land, mutable); //(900, false, mutable)
        if (!land) {
            sea = sea + mutable.getStageName().length(); // 904 + 6;
        }
        log(sea); // your answer? => 910
    }

    private int helloMutable(int sea, Boolean land, St4MutableStage piari) {
        sea++;
        land = true;
        piari.setStageName("mystic");
        return sea;
    }

    private static class St4MutableStage {

        private String stageName;

        public String getStageName() {
            return stageName;
        }

        public void setStageName(String stageName) {
            this.stageName = stageName;
        }
    }

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private int inParkCount;
    private boolean hasAnnualPassport;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_instanceVariable() {
        hasAnnualPassport = true;
        int sea = inParkCount; // 0
        offAnnualPassport(hasAnnualPassport);
        for (int i = 0; i < 100; i++) {//100回足すだけ
            goToPark();
        }
        log(inParkCount);
        ++sea;
        sea = inParkCount;
        log(sea); // your answer? => 100
    }

    private void offAnnualPassport(boolean hasAnnualPassport) { //ここでいじってるのは引数の変数であって、インスタンス変数ではない。
        hasAnnualPassport = false;
    }

    private void goToPark() {
        if (hasAnnualPassport) {
            ++inParkCount;
        }
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    // write instance variables here
    /**
     * Make private methods as followings, and comment out caller program in test method:
     * <pre>
     * o replaceAtoB(): has one argument as String, returns argument replaced "A" with "B" as String 
     * o replaceCtoB(): has one argument as String, returns argument replaced "C" with "B" as String 
     * o addPrefix(): has two arguments as String, returns combined first argument with ":" with second argument 
     * o isAvailableLogging(): no argument, returns private instance variable "availableLogging" initialized as true (also make it)  
     * o showSea(): has one argument as String argument, no return, show argument by log()
     * </pre>
     * (privateメソッドを以下のように定義して、テストメソッド内の呼び出しプログラムをコメントアウトしましょう):
     * <pre>
     * o replaceAtoB(): 一つのString引数、引数を "A" を "B" に置き換えらたStringを戻す 
     * o replaceCtoB(): 一つのString引数、引数を "C" を "B" に置き換えらたStringを戻す 
     * o addPrefix(): 二つのString引数、第一引数と ":" と第二引数を連結したものを戻す 
     * o isAvailableLogging(): 引数なし、privateのインスタンス変数 "availableLogging" (初期値:true) を戻す (それも作る)  
     * o showSea(): 一つのString引数、戻り値なし、引数をlog()で表示する
     * </pre>
     */
    boolean availableLogging = true;

    public void test_method_making() {
        // comment out after making these methods
        String replaced = replaceCtoB(replaceAtoB("ABC"));
        String sea = addPrefix("broadway", replaced);
        if (isAvailableLogging()) {
            showSea(sea);
        }
    }

    private String replaceAtoB(String str) {
        return str.replace("A", "B");
    }

    private String replaceCtoB(String str) {
        return str.replace("C", "B");
    }

    private String addPrefix(String prefix, String str) {//引数名をわかりやすいものにするべき。first,secondなどはわかりにくい
        return prefix + ":" + str;
    }

    private boolean isAvailableLogging() {
        return availableLogging;
    }

    private void showSea(String sea) {
        log(sea);
    }

    // write methods here
}
