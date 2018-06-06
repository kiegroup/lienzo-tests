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

import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.wires.IDockingAcceptor;
import com.ait.lienzo.client.core.shape.wires.WiresManager;
import com.ait.lienzo.client.core.shape.wires.WiresShape;
import com.ait.lienzo.client.core.shape.wires.picker.ColorMapBackedPicker;
import org.mockito.Mock;

import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.when;

public class AbstractWiresControlTest {

    protected WiresShape shape;

    protected WiresShape parent;

    protected Layer layer;

    protected WiresManager manager;

    protected ColorMapBackedPicker.PickerOptions pickerOptions = new ColorMapBackedPicker.PickerOptions(false, 0);

    @Mock
    protected WiresParentPickerCachedControl parentPicker;

    @Mock
    protected IDockingAcceptor dockingAcceptor;

    protected static final double SHAPE_SIZE = 10;
    protected static final double PARENT_SIZE = 100;

    public void setUp() {

        layer = new com.ait.lienzo.client.core.shape.Layer();
        manager = com.ait.lienzo.client.core.shape.wires.WiresManager.get(layer);

        shape = new com.ait.lienzo.client.core.shape.wires.WiresShape(new com.ait.lienzo.client.core.shape.MultiPath().rect(0, 0, SHAPE_SIZE, SHAPE_SIZE));
        shape.setWiresManager(manager);
        parent = new com.ait.lienzo.client.core.shape.wires.WiresShape(new com.ait.lienzo.client.core.shape.MultiPath().rect(0, 0, PARENT_SIZE, PARENT_SIZE));
        parent.setWiresManager(manager);
        manager.getMagnetManager().createMagnets(parent);
        manager.setDockingAcceptor(dockingAcceptor);

        shape.setLocation(new com.ait.lienzo.client.core.types.Point2D(0, 0));
        parent.setLocation(new com.ait.lienzo.client.core.types.Point2D(0, 0));

        when(parentPicker.getWiresLayer()).thenReturn(manager.getLayer());
        when(parentPicker.getParent()).thenReturn(parent);
        when(dockingAcceptor.dockingAllowed(parent, shape)).thenReturn(true);
        when(parentPicker.getParentShapePart()).thenReturn(com.ait.lienzo.client.core.shape.wires.PickerPart.ShapePart.BORDER);
        when(parentPicker.getShape()).thenReturn(shape);
        when(parentPicker.getCurrentLocation()).thenReturn(parent.getLocation());
        when(parentPicker.onMove(anyDouble(), anyDouble())).thenReturn(true);
        when(parentPicker.getPickerOptions()).thenReturn(pickerOptions);
    }
}
