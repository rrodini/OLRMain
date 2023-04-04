package org.olr.nonadmin;

public enum GameType {
    PR("PR", "Presentation Round"),       // 0
    OLR("OLR", "Open Lightning Round"),   // 1
    CDR ("CDR", "Countdown Round")  ;     // 2
    String longName;
    String shortName;
    GameType(String shortName, String longName) {
        this.shortName = shortName;
        this.longName = longName;
    }
    String getShortName() {
        return shortName;
    }
    String getLongName() {
        return longName;
    }
    String getLongNameFromNumber(int no) {
        switch (no) {
            case 0: return GameType.PR.getLongName();
            case 1: return GameType.OLR.getLongName();
            case 2: return GameType.CDR.getLongName();
            default: return "Unknown game type";
        }
    }
    String getShortNameFromNumber(int no) {
        switch (no) {
            case 0: return GameType.PR.getShortName();
            case 1: return GameType.OLR.getShortName();
            case 2: return GameType.CDR.getShortName();
            default: return "Unknown game type";
        }
    }
}

