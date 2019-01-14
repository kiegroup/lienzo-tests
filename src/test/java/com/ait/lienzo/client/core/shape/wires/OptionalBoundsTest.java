/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
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

package com.ait.lienzo.client.core.shape.wires;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class OptionalBoundsTest {

    @Test
    public void testInstance() {
        OptionalBounds allBounds = OptionalBounds.create(1.1d, 2.2d, 3.3d, 4.4d);
        assertTrue(allBounds.hasMinX());
        assertTrue(allBounds.hasMinY());
        assertTrue(allBounds.hasMaxX());
        assertTrue(allBounds.hasMaxY());
        assertEquals(1.1d, allBounds.getMinX(), 0d);
        assertEquals(2.2d, allBounds.getMinY(), 0d);
        assertEquals(3.3d, allBounds.getMaxX(), 0d);
        assertEquals(4.4d, allBounds.getMaxY(), 0d);

        OptionalBounds minBounds = OptionalBounds.createMinBounds(1.1d, 2.2d);
        assertTrue(minBounds.hasMinX());
        assertTrue(minBounds.hasMinY());
        assertFalse(minBounds.hasMaxX());
        assertFalse(minBounds.hasMaxY());
        assertEquals(1.1d, minBounds.getMinX(), 0d);
        assertEquals(2.2d, minBounds.getMinY(), 0d);
        assertNull(minBounds.getMaxX());
        assertNull(minBounds.getMaxY());

        OptionalBounds emptyBounds = OptionalBounds.createEmptyBounds();
        assertFalse(emptyBounds.hasMinX());
        assertFalse(emptyBounds.hasMinY());
        assertFalse(emptyBounds.hasMaxX());
        assertFalse(emptyBounds.hasMaxY());
        assertNull(emptyBounds.getMinX());
        assertNull(emptyBounds.getMinY());
        assertNull(emptyBounds.getMaxX());
        assertNull(emptyBounds.getMaxY());
    }
}
