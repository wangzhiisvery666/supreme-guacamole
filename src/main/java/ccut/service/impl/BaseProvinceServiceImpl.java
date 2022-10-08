package ccut.service.impl;

import ccut.mapper.BaseProvinceMapper;
import ccut.model.pojo.BaseProvince;
import ccut.service.BaseProvinceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class BaseProvinceServiceImpl extends ServiceImpl<BaseProvinceMapper, BaseProvince> implements BaseProvinceService {}

