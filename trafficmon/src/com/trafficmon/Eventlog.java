package com.trafficmon;

import java.util.ArrayList;
import java.util.List;

/*
    Singleton Class for eventlog where stores all boundary-crossing events.
 */
 class Eventlog {
    private static final List<ZoneBoundaryCrossing> eventLog = new ArrayList<>();

    List<ZoneBoundaryCrossing> getInstance()
    {
        return eventLog;
    }
}
