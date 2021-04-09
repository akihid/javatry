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
public enum DaysType {
    ONE_DAY(1), TWO_DAY(2), FOUR_DAY(4);

    private int days;

    private DaysType(int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }
}