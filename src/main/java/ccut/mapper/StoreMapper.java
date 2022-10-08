package ccut.mapper;

import ccut.model.pojo.Store;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreMapper extends BaseMapper<Store> {
  List<Store> getAllStore();
  
  int updateStatus(@Param("id") int paramInt, @Param("message") String paramString);
}
