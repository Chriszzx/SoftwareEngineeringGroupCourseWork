package com.trafficmon;

import java.util.ArrayList;
import java.util.List;

/*
    Singleton Class for eventlog where stores all boundary-crossing events.
 */

public class Eventlog {
    static final List<ZoneBoundaryCrossing> eventLog = new ArrayList<>();

    public List<ZoneBoundaryCrossing> getInstance()
    {
        return eventLog;
    }
}
