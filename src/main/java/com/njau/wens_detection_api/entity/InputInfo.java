package com.njau.wens_detection_api.entity;


import java.sql.Timestamp;

public class InputInfo {
    private String A4;
    private String M4;
    private String AFI;
    private String FI;
    private String A;
    private String DW;
    private String HW;
    private String AWI;
    private String AWE;
    private String C;
    private String AWC;
    private String J;
    private String D;
    private String P;
    private String TZ1;
    private String TZ2;
    private String U;
    private String W;
    private String WAEW;
    private String PAEW;
    private String day;
    private String T;
    private String H;
    private String CO2;
    private String NH3;
    private String date;
    private Timestamp time;

    public String getA4() {
        return A4;
    }

    public void setA4(String a4) {
        A4 = a4;
    }

    public String getM4() {
        return M4;
    }

    public void setM4(String m4) {
        M4 = m4;
    }

    public String getAFI() {
        return AFI;
    }

    public void setAFI(String AFI) {
        this.AFI = AFI;
    }

    public String getFI() {
        return FI;
    }

    public void setFI(String FI) {
        this.FI = FI;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getDW() {
        return DW;
    }

    public void setDW(String DW) {
        this.DW = DW;
    }

    public String getHW() {
        return HW;
    }

    public void setHW(String HW) {
        this.HW = HW;
    }

    public String getAWI() {
        return AWI;
    }

    public void setAWI(String AWI) {
        this.AWI = AWI;
    }

    public String getAWE() {
        return AWE;
    }

    public void setAWE(String AWE) {
        this.AWE = AWE;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getAWC() {
        return AWC;
    }

    public void setAWC(String AWC) {
        this.AWC = AWC;
    }

    public String getJ() {
        return J;
    }

    public void setJ(String j) {
        J = j;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    public String getP() {
        return P;
    }

    public void setP(String p) {
        P = p;
    }

    public String getTZ1() {
        return TZ1;
    }

    public void setTZ1(String TZ1) {
        this.TZ1 = TZ1;
    }

    public String getTZ2() {
        return TZ2;
    }

    public void setTZ2(String TZ2) {
        this.TZ2 = TZ2;
    }

    public String getU() {
        return U;
    }

    public void setU(String u) {
        U = u;
    }

    public String getW() {
        return W;
    }

    public void setW(String w) {
        W = w;
    }

    public String getWAEW() {
        return WAEW;
    }

    public void setWAEW(String WAEW) {
        this.WAEW = WAEW;
    }

    public String getPAEW() {
        return PAEW;
    }

    public void setPAEW(String PAEW) {
        this.PAEW = PAEW;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getT() {
        return T;
    }

    public void setT(String t) {
        T = t;
    }

    public String getH() {
        return H;
    }

    public void setH(String h) {
        H = h;
    }

    public String getCO2() {
        return CO2;
    }

    public void setCO2(String CO2) {
        this.CO2 = CO2;
    }

    public String getNH3() {
        return NH3;
    }

    public void setNH3(String NH3) {
        this.NH3 = NH3;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public InputInfo(String a4, String m4, String AFI, String FI, String a, String DW, String HW, String AWI, String AWE, String c, String AWC, String j, String d, String p, String TZ1, String TZ2, String u, String w, String WAEW, String PAEW, String day, String t, String h, String CO2, String NH3, String date, Timestamp time) {
        A4 = a4;
        M4 = m4;
        this.AFI = AFI;
        this.FI = FI;
        A = a;
        this.DW = DW;
        this.HW = HW;
        this.AWI = AWI;
        this.AWE = AWE;
        C = c;
        this.AWC = AWC;
        J = j;
        D = d;
        P = p;
        this.TZ1 = TZ1;
        this.TZ2 = TZ2;
        U = u;
        W = w;
        this.WAEW = WAEW;
        this.PAEW = PAEW;
        this.day = day;
        T = t;
        H = h;
        this.CO2 = CO2;
        this.NH3 = NH3;
        this.date = date;
        this.time = time;
    }
}
