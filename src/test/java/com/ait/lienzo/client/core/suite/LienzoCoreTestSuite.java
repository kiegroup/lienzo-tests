/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ait.lienzo.client.core.suite;

import com.ait.lienzo.client.core.image.ImageElementProxyTest;
import com.ait.lienzo.client.core.image.ImageStripsTest;
import com.ait.lienzo.client.core.image.ImageTest;
import com.ait.lienzo.client.core.shape.MultiPathTest;
import com.ait.lienzo.client.core.shape.PolyLineTest;
import com.ait.lienzo.client.core.shape.TextBoundsWrapTest;
import com.ait.lienzo.client.core.shape.TextLineBreakWrapTest;
import com.ait.lienzo.client.core.types.BoundingBoxTest;
import com.ait.lienzo.client.widget.LienzoHandlerManagerTest;
import com.ait.lienzo.client.widget.LienzoPanelTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BoundingBoxTest.class,
        ImageElementProxyTest.class,
        ImageStripsTest.class,
        ImageTest.class,
        LienzoHandlerManagerTest.class,
        LienzoPanelTest.class,
        MultiPathTest.class,
        PolyLineTest.class,
        TextBoundsWrapTest.class,
        TextLineBreakWrapTest.class
})
public class LienzoCoreTestSuite {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }
}
