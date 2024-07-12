package com.njau.wens_detection_api.controller;

import com.njau.wens_detection_api.entity.FileInfo;
import com.njau.wens_detection_api.entity.ResponseInfo;
import com.njau.wens_detection_api.mapper.AddInputInfoMapper;
import com.njau.wens_detection_api.service.FunctionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
public class FunctionController {
    @Autowired
    private FunctionService functionServiceImpl;

    @Autowired
    private AddInputInfoMapper addInputInfoMapper;
    @Autowired
    private HttpServletRequest httpServletRequest;


    @RequestMapping("/detection")
    @ResponseBody
    public ResponseInfo detection(@RequestParam(value = "file") MultipartFile[] files,
                                    @RequestParam(value = "module") String module,
                                    @RequestParam(value = "inf", required = false, defaultValue = "30")String inf,
                                    @RequestParam(value = "value", required = false, defaultValue = "0.5")String value) throws IOException {
        FileInfo fileInfo = new FileInfo(files, module, inf, value);
//        System.out.println(fileInfo);
        ResponseInfo responseInfo = new ResponseInfo(fileInfo.getModule(), fileInfo.getInf());
        functionServiceImpl.detection(fileInfo, responseInfo);
        return responseInfo;
    }

    @PostMapping("/addInputInfo")
    public void inputInfo(){
        System.out.println("111");
        String A4=httpServletRequest.getParameter("A4");
        String M4=httpServletRequest.getParameter("M4");
        String AFI=httpServletRequest.getParameter("AFI");
        String FI=httpServletRequest.getParameter("FI");
        String A=httpServletRequest.getParameter("A");
        String DW=httpServletRequest.getParameter("DW");
        String HW=httpServletRequest.getParameter("HW");
        String AWI=httpServletRequest.getParameter("AWI");
        String AWE=httpServletRequest.getParameter("AWE");
        String C=httpServletRequest.getParameter("C");
        String AWC=httpServletRequest.getParameter("AWC");
        String J=httpServletRequest.getParameter("J");
        String D=httpServletRequest.getParameter("D");
        String P=httpServletRequest.getParameter("P");
        String TZ1=httpServletRequest.getParameter("TZ1");
        String TZ2=httpServletRequest.getParameter("TZ2");
        String U=httpServletRequest.getParameter("U");
        String W=httpServletRequest.getParameter("W");
        String WAEW=httpServletRequest.getParameter("WAEW");
        String PAEW=httpServletRequest.getParameter("PAEW");
        String day=httpServletRequest.getParameter("day");
        String T=httpServletRequest.getParameter("T");
        String H=httpServletRequest.getParameter("H");
        String CO2=httpServletRequest.getParameter("CO2");
        String NH3=httpServletRequest.getParameter("NH3");
        addInputInfoMapper.addinputInfo(A4, M4, AFI, FI, A, DW, HW, AWI, AWE, C, AWC, J,D,P,TZ1,TZ2,U,W,WAEW,PAEW,day,T,H,CO2,NH3);
        System.out.println("添加成功");
    }
}
