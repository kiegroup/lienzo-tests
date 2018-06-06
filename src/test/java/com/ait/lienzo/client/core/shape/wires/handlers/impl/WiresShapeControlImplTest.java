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
package com.ait.lienzo.client.core.shape.wires.handlers.impl;

import com.ait.lienzo.client.core.shape.Group;
import com.ait.lienzo.client.core.shape.IDirectionalMultiPointShape;
import com.ait.lienzo.client.core.shape.MultiPathDecorator;
import com.ait.lienzo.client.core.shape.PolyLine;
import com.ait.lienzo.client.core.shape.wires.*;
import com.ait.lienzo.client.core.shape.wires.handlers.*;
import com.ait.lienzo.client.core.types.Point2DArray;
import com.ait.lienzo.test.LienzoMockitoTestRunner;
import com.ait.tooling.nativetools.client.collection.NFastArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

@RunWith(LienzoMockitoTestRunner.class)
public class WiresShapeControlImplTest extends AbstractWiresControlTest {
    private WiresShapeControlImpl wiresShapeControl;

    @Mock
    private WiresMagnetsControl m_magnetsControl;

    @Mock
    private WiresDockingControl m_dockingAndControl;

    @Mock
    private WiresContainmentControl m_containmentControl;

    @Mock
    private WiresConnectorHandler connectorHandler;

    @Mock
    private WiresConnectorControl connectorControl;

    private IDirectionalMultiPointShape line;

    @Mock
    private MultiPathDecorator headDecorator;

    @Mock
    private MultiPathDecorator tailDecorator;

    @Mock
    private WiresShape childWiresShape = mock(WiresShape.class);

    @Mock
    private MagnetManager.Magnets magnets = mock(MagnetManager.Magnets.class);

    @Mock
    private WiresMagnet magnet = mock(WiresMagnet.class);

    @Mock
    private WiresConnection connection = mock(WiresConnection.class);

    @Mock
    private NFastArrayList<WiresConnection> connections = new NFastArrayList<>(connection);

    @Mock
    private WiresConnector connector = mock(WiresConnector.class);

    @Mock
    private String connectorUUID = "UUID";

    @Mock
    private Group connectorGroup = mock(Group.class);

    @Mock
    private com.ait.lienzo.client.core.types.Point2D controlPointLienzo = new com.ait.lienzo.client.core.types.Point2D(100, 100);

    @Mock
    private Point2DArray controlPointsLienzo = new Point2DArray(controlPointLienzo);

    @Before
    public void setup() {
        super.setUp();
        line = new PolyLine(0, 0, 10, 10, 100, 100);
        shape.getChildShapes().add(childWiresShape);

        when(childWiresShape.getMagnets()).thenReturn(magnets);
        when(magnets.size()).thenReturn(1);
        when(magnets.getMagnet(0)).thenReturn(magnet);
        when(magnet.getConnectionsSize()).thenReturn(connections.size());
        when(magnet.getConnections()).thenReturn(connections);
        when(connection.getConnector()).thenReturn(connector);
        when(connector.getGroup()).thenReturn(connectorGroup);
        when(connectorGroup.uuid()).thenReturn(connectorUUID);
        when(connector.getControlPoints()).thenReturn(controlPointsLienzo);
        when(connector.getWiresConnectorHandler()).thenReturn(connectorHandler);
        when(connectorHandler.getControl()).thenReturn(connectorControl);
        when(connector.getLine()).thenReturn(line);
        when(connector.getHeadDecorator()).thenReturn(headDecorator);
        when(connector.getTailDecorator()).thenReturn(tailDecorator);
        when(parentPicker.onMove(10, 10)).thenReturn(false);

        wiresShapeControl = new WiresShapeControlImpl(parentPicker, m_magnetsControl, m_dockingAndControl, m_containmentControl);
    }

    @Test
    public void moveTest() {
        wiresShapeControl.onMoveStart(0, 0);
        verify(connectorControl).onMoveStart(0, 0);

        wiresShapeControl.onMove(10, 10);
        verify(connectorControl).move(10, 10, true, true);
    }
}
