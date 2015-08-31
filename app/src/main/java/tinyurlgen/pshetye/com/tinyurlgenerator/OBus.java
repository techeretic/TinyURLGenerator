package tinyurlgen.pshetye.com.tinyurlgenerator;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by pshetye on 8/30/15.
 */
public class OBus {
    private static Bus sBus;

    public static Bus getBus() {
        if (sBus == null) {
            sBus = new Bus(ThreadEnforcer.MAIN);
        }
        return sBus;
    }
}
