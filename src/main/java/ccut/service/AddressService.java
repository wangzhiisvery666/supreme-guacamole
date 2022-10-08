package ccut.service;

import ccut.common.CommonResponse;
import ccut.model.pojo.Address;
import ccut.model.request.RequestAddress;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AddressService extends IService<Address> {
  CommonResponse<Boolean> addAdress(RequestAddress paramRequestAddress, HttpServletRequest paramHttpServletRequest);
  
  CommonResponse<Boolean> updateAdress(Address paramAddress);
  
  CommonResponse<Boolean> deleteAdress(Address paramAddress);
  
  CommonResponse<List<Address>> getAdress(HttpServletRequest paramHttpServletRequest);
  
  Address getDefaultAddress(int paramInt);
  
  CommonResponse<Boolean> getAddressSum(HttpServletRequest paramHttpServletRequest);
}

