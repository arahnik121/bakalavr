package util;

import App.Model.Aircraft;

import java.util.UUID;

public class Data {
    public static final String ID_1 = UUID.randomUUID().toString();
    public static final String ID_2 = UUID.randomUUID().toString();
    public static final String ID_3 = UUID.randomUUID().toString();
    public static final String ID_4 = UUID.randomUUID().toString();

    public static final Aircraft R1;
    public static final Aircraft R2;
    public static final Aircraft R3;
    public static final Aircraft R4;

    static {
        R1 = new Aircraft(ID_1, 0, 0, 1, 1, " ");
        R2 = new Aircraft(ID_2, 1, 1, 1, 1, " ");
        R3 = new Aircraft(ID_3, 0, 0, 1, 1, " ");
        R4 = new Aircraft(ID_4, 2, 2, 1, 1, " ");
    }

}
