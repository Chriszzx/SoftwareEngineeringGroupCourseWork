package com.trafficmon;

import java.util.ArrayList;
import java.util.List;

public class Eventlog {
    static final List<ZoneBoundaryCrossing> eventLog = new ArrayList<>();

    public List<ZoneBoundaryCrossing> getInstance()
    {
        return eventLog;
    }
}
