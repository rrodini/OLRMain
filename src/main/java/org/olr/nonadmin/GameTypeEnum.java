package org.olr.nonadmin;

public enum GameTypeEnum {
    PR("Presentation Round"), // 0
    OLR("Open Lightning Round"), // 1
    CDR("Countdown Round"); // 2

    private String longName;

    GameTypeEnum(String name) {
        longName = name;
    }

    public String getLongName() {
        return this.longName;
    }

    public String getLongNameFromNumber(int n) {
        switch (n) {
    case 0:
            return PR.getLongName();
    case 1:
            return OLR.getLongName();
    case 2:
            return CDR.getLongName();
        }
        return "Unknown game";
    }

    public String getShortNameFromNumber(int n) {
        switch (n) {
            case 0:
                return "PR";
            case 1:
                return "OLR";
            case 2:
                return "CDR";
        }
        return "Unknown game";
    }
}
