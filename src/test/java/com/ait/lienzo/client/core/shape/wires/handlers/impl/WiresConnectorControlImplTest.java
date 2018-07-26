/*
 *
 *    Copyright (c) 2018 Ahome' Innovation Technologies. All rights reserved.
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
package com.ait.lienzo.client.core.shape.wires.handlers.impl;

import com.ait.lienzo.client.core.Context2D;
import com.ait.lienzo.client.core.shape.AbstractDirectionalMultiPointShape;
import com.ait.lienzo.client.core.shape.IPrimitive;
import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.Shape;
import com.ait.lienzo.client.core.shape.wires.*;
import com.ait.lienzo.client.core.shape.wires.handlers.WiresConnectorControl;
import com.ait.lienzo.client.core.shape.wires.handlers.WiresControlFactory;
import com.ait.lienzo.client.core.shape.wires.handlers.WiresControlPointHandler;
import com.ait.lienzo.client.core.shape.wires.handlers.WiresHandlerFactory;
import com.ait.lienzo.client.core.types.*;
import com.ait.lienzo.client.core.util.ScratchPad;
import com.ait.lienzo.test.LienzoMockitoTestRunner;
import com.ait.tooling.nativetools.client.event.HandlerRegistrationManager;
import com.google.gwt.event.shared.HandlerRegistration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.Iterator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(LienzoMockitoTestRunner.class)
public class WiresConnectorControlImplTest {

    private WiresConnectorControlImpl wiresConnectorControl;

    @Mock
    private WiresManager wiresManager;

    @Mock
    private WiresLayer wiresLayer;

    @Mock
    private WiresConnector connector;

    @Mock
    private AbstractDirectionalMultiPointShape line;

    @Mock
    private Shape<?> lineShape;

    @Mock
    private ScratchPad scratchPad;

    @Mock
    private PathPartList pathPartList;

    @Mock
    private Context2D context;

    @Mock
    private Point2D location;

    @Mock
    private BoundingBox boundingBox;

    @Mock
    private ImageData backImage;

    @Mock
    private IControlHandleList pointHandles;

    @Mock
    private HandlerRegistrationManager handlerRegistration;

    @Mock
    private WiresControlFactory controlFactory;

    @Mock
    private WiresConnectorControl connectorControl;

    @Mock
    private WiresConnection headConnection;

    @Mock
    private IPrimitive headControl;

    @Mock
    private IPrimitive pointControl;

    @Mock
    private Shape headShape;

    @Mock
    private WiresConnection tailConnection;

    @Mock
    private IPrimitive tailControl;

    @Mock
    private Shape tailShape;

    @Mock
    private Shape pointShape;

    @Mock
    private WiresHandlerFactory wiresHandlerFactory;

    @Mock
    private WiresControlPointHandler connectorControlHandler;

    private Point2D head;

    private Point2D tail;

    private Point2DArray lineArray;

    @Mock
    private Iterator<IControlHandle> pointHandlesIterator;

    @Mock
    private IControlHandle pointHandle;

    @Mock
    private Layer layer;

    @Mock
    private HandlerRegistration controlPointRegistration;

    @Before
    public void setup() {
        wiresConnectorControl = new WiresConnectorControlImpl(connector, wiresManager);

        head = new Point2D(0, 0);
        tail = new Point2D(100, 0);
        lineArray = new Point2DArray(head, tail);

        when(line.getPoint2DArray()).thenReturn(lineArray);
        when(line.getScratchPad()).thenReturn(scratchPad);
        when(line.getPathPartList()).thenReturn(pathPartList);
        when(line.getComputedLocation()).thenReturn(location);
        when(line.getBoundingBox()).thenReturn(boundingBox);
        when(line.asShape()).thenReturn(lineShape);
        when(lineShape.getPathPartList()).thenReturn(pathPartList);
        when(connector.getLine()).thenReturn(line);
        when(scratchPad.getContext()).thenReturn(context);
        when(context.getImageData(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(backImage);
        when(connector.getPointHandles()).thenReturn(pointHandles);
        when(pointHandles.getHandlerRegistrationManager()).thenReturn(handlerRegistration);
        when(pointHandles.size()).thenReturn(3);
        when(wiresManager.getLayer()).thenReturn(wiresLayer);
        when(wiresManager.getControlFactory()).thenReturn(controlFactory);
        when(wiresManager.getWiresHandlerFactory()).thenReturn(wiresHandlerFactory);
        when(controlFactory.newConnectorControl(connector, wiresManager)).thenReturn(connectorControl);
        when(connector.getHeadConnection()).thenReturn(headConnection);
        when(headConnection.getControl()).thenReturn(headControl);
        when(headControl.asShape()).thenReturn(headShape);
        when(pointControl.asShape()).thenReturn(pointShape);
        when(connector.getTailConnection()).thenReturn(tailConnection);
        when(tailConnection.getControl()).thenReturn(tailControl);
        when(tailControl.asShape()).thenReturn(tailShape);
        when(connector.getControl()).thenReturn(wiresConnectorControl);
        when(wiresHandlerFactory.newControlPointHandler(connector, wiresManager)).thenReturn(connectorControlHandler);
        when(line.getLayer()).thenReturn(layer);
        when(pointHandles.iterator()).thenReturn(pointHandlesIterator);
        when(pointHandlesIterator.hasNext()).thenReturn(true, true, true, false);
        when(pointHandlesIterator.next()).thenReturn(pointHandle);
        when(pointHandle.getControl()).thenReturn(headControl, pointControl, tailControl);
        when(pointShape.addNodeMouseDoubleClickHandler(connectorControlHandler)).thenReturn(controlPointRegistration);
        when(pointShape.addNodeDragStartHandler(connectorControlHandler)).thenReturn(controlPointRegistration);
        when(pointShape.addNodeDragEndHandler(connectorControlHandler)).thenReturn(controlPointRegistration);
        when(pointShape.addNodeDragMoveHandler(connectorControlHandler)).thenReturn(controlPointRegistration);
        when(headShape.addNodeMouseDoubleClickHandler(connectorControlHandler)).thenReturn(controlPointRegistration);
        when(headShape.addNodeDragStartHandler(connectorControlHandler)).thenReturn(controlPointRegistration);
        when(headShape.addNodeDragEndHandler(connectorControlHandler)).thenReturn(controlPointRegistration);
        when(headShape.addNodeDragMoveHandler(connectorControlHandler)).thenReturn(controlPointRegistration);
        when(tailShape.addNodeMouseDoubleClickHandler(connectorControlHandler)).thenReturn(controlPointRegistration);
        when(tailShape.addNodeDragStartHandler(connectorControlHandler)).thenReturn(controlPointRegistration);
        when(tailShape.addNodeDragEndHandler(connectorControlHandler)).thenReturn(controlPointRegistration);
        when(tailShape.addNodeDragMoveHandler(connectorControlHandler)).thenReturn(controlPointRegistration);
    }

    @Test
    public void testAddControlPointToLineAndRemove() {
        final double x = 10;
        final double y = 0;
        final int index = 1;

        when(wiresManager.getControlPointsAcceptor()).thenReturn(IControlPointsAcceptor.ALL);

        //add
        wiresConnectorControl.addControlPoint(x, y, index);
        verify(connector, times(1)).addControlPoint(eq(x),
                                                    eq(y),
                                                    eq(index));

        //remove
        wiresConnectorControl.destroyControlPoint(1);
        verify(connector, times(1)).destroyControlPoints(eq(new int[] {1}));
    }

    @Test
    public void testCannotAddOrRemoveControlPoints() {
        final double x = 10;
        final double y = 0;
        final int index = 1;

        when(wiresManager.getControlPointsAcceptor()).thenReturn(IControlPointsAcceptor.NONE);

        //add
        wiresConnectorControl.addControlPoint(x, y, index);
        verify(connector, never()).addControlPoint(eq(x),
                                                    eq(y),
                                                    eq(index));

        //remove
        wiresConnectorControl.destroyControlPoint(1);
        verify(connector, never()).destroyControlPoints(eq(new int[] {1}));
    }

    @Test
    public void testMoveConnectionPoints() {
        when(connector.getControlPoints()).thenReturn(new Point2DArray(new Point2D(0, 0),
                                                                       new Point2D(1, 1),
                                                                       new Point2D(3, 3)));
        when(wiresManager.getControlPointsAcceptor()).thenReturn(IControlPointsAcceptor.NONE);
        assertTrue(wiresConnectorControl.moveControlPoint(0, new Point2D(1, 1)));
        assertFalse(wiresConnectorControl.moveControlPoint(1, new Point2D(1, 1)));
        assertTrue(wiresConnectorControl.moveControlPoint(2, new Point2D(1, 1)));
        when(wiresManager.getControlPointsAcceptor()).thenReturn(IControlPointsAcceptor.ALL);
        assertTrue(wiresConnectorControl.moveControlPoint(1, new Point2D(1, 1)));
    }
}