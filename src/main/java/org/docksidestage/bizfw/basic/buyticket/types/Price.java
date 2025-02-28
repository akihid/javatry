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
public enum Price {
    ONE_DAY(7400), TWO_DAY(13200), FOUR_DAY(22400), NIGHT_ONLY_TWO_DAY(7400);

    private final int value;

    private Price(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
