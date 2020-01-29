package com.ait.lienzo.test.stub.overlays;

import java.util.HashMap;
import java.util.Map;

import com.ait.lienzo.test.annotation.StubClass;

/**
 * @author Dmitrii Tikhomirov
 * Created by treblereel 11/8/19
 */
@StubClass("elemental2.core.JsMap")
public class JsMap<KEY, VALUE> {

    public int size = 0;


    Map<KEY, VALUE> map = new HashMap<>();

    public VALUE get(KEY key) {
        return map.get(key);
    }

    public JsMap<KEY, VALUE> set(KEY key, VALUE value) {
        map.put(key, value);
        this.size = map.size();
        return this;
    }

    public boolean has(KEY key) {
        return map.containsKey(key);
    }
}
