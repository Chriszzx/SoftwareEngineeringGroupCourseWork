package com.trafficmon;


/*
    this is the class where stores new charging standard
 */

class Specification {

    int Current(long first, long time){

        int charge = 0;
        if (time > 240) { charge = 12; }

        if (time <= 240 ) {
            if (first < 840) {
                charge = 6;
            } else {
                charge = 4;
            }
        }
        return charge;
    }
}

