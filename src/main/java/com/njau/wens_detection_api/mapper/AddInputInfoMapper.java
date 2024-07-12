package com.njau.wens_detection_api.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AddInputInfoMapper{
    int addinputInfo(@Param("A4") String A4, @Param("M4") String M4, @Param("AFI") String AFI, @Param("FI") String FI, @Param("A") String A, @Param("DW") String DW, @Param("HW") String HW, @Param("AWI") String AWI, @Param("AWE") String AWE, @Param("C") String C,@Param("AWC") String AWC, @Param("J") String J, @Param("D") String D, @Param("P") String P, @Param("TZ1") String TZ1, @Param("TZ2") String TZ2,@Param("U") String U, @Param("W") String W,@Param("WAEW") String WAEW, @Param("PAEW") String PAEW, @Param("day") String day, @Param("T") String T, @Param("H") String H, @Param("CO2") String CO2, @Param("NH3") String NH3);
}
