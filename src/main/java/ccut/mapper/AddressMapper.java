package ccut.mapper;

import ccut.model.pojo.Address;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AddressMapper extends BaseMapper<Address> {
  int updatePermissionByUserId(@Param("uid") Integer paramInteger);
  
  @Select({"select * from address WHERE user_id=#{uid} order by  permission desc"})
  List<Address> selectOrderByPermission(@Param("uid") Integer paramInteger);
}

