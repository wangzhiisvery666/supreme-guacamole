package ccut.service;

import ccut.common.CommonResponse;
import ccut.model.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AdminService extends IService<Admin> {
  CommonResponse<Boolean> adminLogin(Admin paramAdmin);
}
