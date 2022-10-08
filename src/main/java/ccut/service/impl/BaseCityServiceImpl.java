package ccut.service.impl;

import ccut.mapper.BaseCityMapper;
import ccut.model.pojo.BaseCity;
import ccut.service.BaseCityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BaseCityServiceImpl extends ServiceImpl<BaseCityMapper, BaseCity> implements BaseCityService {}

