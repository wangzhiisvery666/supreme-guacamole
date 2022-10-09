package ccut.service;

import ccut.common.CommonResponse;
import ccut.model.VO.StoreApplicationVO;
import ccut.model.pojo.StoreAudit;
import ccut.model.request.StoreAuditRequest;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@Service
public interface StoreAuditService extends IService<StoreAudit> {
  CommonResponse<Boolean> addStoreAudit(StoreAuditRequest paramStoreAuditRequest);
  
  CommonResponse<Boolean> addPicture(MultipartFile paramMultipartFile);
  
  CommonResponse<List<StoreAudit>> getStoreAudit();
  
  CommonResponse<Boolean> approval(int paramInt, String paramString1, String paramString2);
  
  CommonResponse<HashMap<String,Object>> getStatus();
  
  CommonResponse<StoreApplicationVO> putApplication();
  
  CommonResponse<StoreApplicationVO> deleteApplication();

  CommonResponse<HashMap<String, Object>> getStoreAuditByStatus(int approvalStatus, Integer pageSum, Integer size);

  CommonResponse<Boolean> updateStatus();
}

