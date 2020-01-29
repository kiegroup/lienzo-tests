package com.ait.lienzo.test.stub.overlays;

import java.util.Random;

import com.ait.lienzo.test.annotation.StubClass;
import elemental2.dom.Element;

@StubClass("elemental2.dom.DomGlobal")
public class DomGlobal {

    public static final elemental2.dom.HTMLDocument document = new elemental2.dom.HTMLDocument();

    public static int requestAnimationFrame(
            elemental2.dom.DomGlobal.RequestAnimationFrameCallbackFn callback, Element element) {
        return new Random().nextInt();
    }

    public static final double setTimeout(
            SetTimeoutCallbackFn callback, double delay, Object... args) {
        return setTimeout(callback, delay, args);
    }

    public interface SetTimeoutCallbackFn {
        void onInvoke(Object... p0);
    }

}
