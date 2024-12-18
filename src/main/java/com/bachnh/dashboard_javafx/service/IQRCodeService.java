package com.bachnh.dashboard_javafx.service;

import com.bachnh.dashboard_javafx.data.ResponseData;
import com.bachnh.dashboard_javafx.dto.request.QrCodeContent;
import com.bachnh.dashboard_javafx.dto.request.QrCodeRequest;
import com.bachnh.dashboard_javafx.dto.response.DocRenderResponse;
import com.bachnh.dashboard_javafx.dto.response.QrCodeResponse;

public interface IQRCodeService {
    ResponseData<DocRenderResponse>generateQRCode(QrCodeRequest request);
    ResponseData<QrCodeResponse> readQRCode(QrCodeContent request);

}
