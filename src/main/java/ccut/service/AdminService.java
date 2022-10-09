package ccut.service;

import ccut.common.CommonResponse;
import ccut.model.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

@Service
public interface AdminService extends IService<Admin> {
  CommonResponse<Boolean> adminLogin(Admin paramAdmin);
}
