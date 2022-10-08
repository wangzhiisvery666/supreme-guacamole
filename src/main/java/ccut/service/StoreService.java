package ccut.service;

import ccut.common.CommonResponse;
import ccut.model.pojo.Store;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface StoreService extends IService<Store> {
  CommonResponse<List<Store>> getAllStore();
  
  CommonResponse<Boolean> putStoreStatus(int paramInt, String paramString);
}
