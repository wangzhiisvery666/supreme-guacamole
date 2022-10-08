package ccut.service;

import ccut.model.pojo.lunBo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LunBoService extends IService<lunBo> {
  List<String> getUrl();
}
