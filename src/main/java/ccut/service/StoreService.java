package ccut.service;

import ccut.common.CommonResponse;
import ccut.model.VO.SStoreVO;
import ccut.model.pojo.Store;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface StoreService extends IService<Store> {
  /**
   * 官方 获取所有店铺信息
   * @return : 所有店铺 List<Store>
   */
  CommonResponse<List<Store>> getAllStore();

  /**
   * 商家获取 的 SStoreVO
   * @return  SStoreVO
   */
  CommonResponse<SStoreVO> storeGetStore();

  /**
   * 按照 店铺id来修改 店铺的状态
   * @param paramInt : 店铺id
   * @param paramString ：修改信息
   * @return ：饭回boolean
   */

  CommonResponse<Boolean> putStoreStatus(int paramInt, String paramString);

//  CommonResponse<Boolean> PutStoreInfo();
}
