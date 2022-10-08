package ccut.service;

import ccut.common.CommonResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Service
public interface FileService {
  CommonResponse<String> sendAvatar(MultipartFile paramMultipartFile, HttpServletRequest paramHttpServletRequest);
}
