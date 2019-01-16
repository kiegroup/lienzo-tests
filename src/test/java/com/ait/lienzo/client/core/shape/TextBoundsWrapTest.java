/*
 *
 *    Copyright (c) 2017 Ahome' Innovation Technologies. All rights reserved.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */
package com.ait.lienzo.client.core.shape;

import com.ait.lienzo.client.core.types.BoundingBox;
import com.ait.lienzo.shared.core.types.TextAlign;
import com.ait.lienzo.test.LienzoMockitoTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(LienzoMockitoTestRunner.class)
public class TextBoundsWrapTest extends BaseTextTest {

    @Test
    public void testTextBoundsWrap() {
        testTextBoundsWrap("very long text that should wrap",
                           OFFSET_X,
                           OFFSET_Y,
                           new Object[]{
                                   new DrawnText("very long ",
                                                 OFFSET_X,
                                                 OFFSET_Y + 0.8),
                                   new DrawnText("text that ",
                                                 OFFSET_X,
                                                 OFFSET_Y + 1.8),
                                   new DrawnText("should    ",
                                                 OFFSET_X,
                                                 OFFSET_Y + 2.8),
                                   new DrawnText("wrap      ",
                                                 OFFSET_X,
                                                 OFFSET_Y + 3.8)
                           });
    }

    @Test
    public void testTextBoundsWrapOneWord() {
        testTextBoundsWrap("very",
                           OFFSET_X,
                           OFFSET_Y,
                           new Object[]{
                                   new DrawnText("very      ",
                                                 OFFSET_X,
                                                 OFFSET_Y + 0.8)
                           });
    }

    @Test
    public void testTextBoundsWrapWhiteSpace() {
        testTextBoundsWrap("   ",
                           OFFSET_X,
                           OFFSET_Y,
                           new Object[]{});
    }

    private void testTextBoundsWrap(final String text,
                                    final double x,
                                    final double y,
                                    final Object[] results) {
        BoundingBox bbox = new BoundingBox().addX(0).addY(0).addX(10).addY(10);
        Text wrapped = new Text(text).setX(x).setY(y);
        Text tested = spy(wrapped);

        tested.setWrapper(new TextBoundsWrap(tested,
                                             bbox));
        tested.setTextAlign(TextAlign.LEFT);

        when(tested.getLineHeight(context)).thenReturn(1.0);
        tested.getBoundingBox();
        assertEquals(bbox.getWidth(),
                     tested.getBoundingBox().getWidth(),
                     0.0001);

        tested.drawWithTransforms(context,
                                  1,
                                  bbox);

        assertArrayEquals(results,
                          drawnTexts.toArray());
    }
}
