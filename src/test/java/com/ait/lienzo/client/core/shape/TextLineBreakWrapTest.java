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
public class TextLineBreakWrapTest extends BaseTextTest {

    @Test
    public void testTextLineBreakWrapN() {
        testLineBreakWrap("very long text \nthat should wrap",
                          OFFSET_X,
                          OFFSET_Y,
                          new Object[]{
                                  new DrawnText("very long text ",
                                                OFFSET_X,
                                                OFFSET_Y + 0.8),
                                  new DrawnText("that should wrap",
                                                OFFSET_X,
                                                OFFSET_Y + 1.8)
                          });
    }

    @Test
    public void testTextLineBreakWrapRN() {
        testLineBreakWrap("very long text \r\nthat should wrap",
                          OFFSET_X,
                          OFFSET_Y,
                          new Object[]{
                                  new DrawnText("very long text ",
                                                OFFSET_X,
                                                OFFSET_Y + 0.8),
                                  new DrawnText("that should wrap",
                                                OFFSET_X,
                                                OFFSET_Y + 1.8)
                          });
    }

    @Test
    public void testTextLineBreakWrapNoLineBreaks() {
        testLineBreakWrap("very long text",
                          OFFSET_X,
                          OFFSET_Y,
                          new Object[]{
                                  new DrawnText("very long text",
                                                OFFSET_X,
                                                OFFSET_Y + 0.8)
                          });
    }

    private void testLineBreakWrap(final String text,
                                   final double x,
                                   final double y,
                                   final Object[] results) {
        final Text wrapped = new Text(text).setX(x).setY(y);
        final Text tested = spy(wrapped);
        tested.setWrapper(new TextLineBreakWrap(tested));
        tested.setTextAlign(TextAlign.LEFT);

        when(tested.getLineHeight(context)).thenReturn(1.0);

        tested.drawWithTransforms(context,
                                  1,
                                  new BoundingBox());

        assertArrayEquals(results,
                          drawnTexts.toArray());
    }
}
