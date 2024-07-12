package com.njau.wens_detection_api.service;

import com.njau.wens_detection_api.entity.FileInfo;
import com.njau.wens_detection_api.entity.ResponseInfo;

public interface FunctionService {
    ResponseInfo detection(FileInfo fileInfo, ResponseInfo responseInfo);
}
