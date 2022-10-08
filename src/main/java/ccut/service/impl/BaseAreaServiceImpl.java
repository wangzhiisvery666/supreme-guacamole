package ccut.service.impl;

import ccut.mapper.BaseAreaMapper;
import ccut.model.pojo.BaseArea;
import ccut.service.BaseAreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BaseAreaServiceImpl extends ServiceImpl<BaseAreaMapper, BaseArea> implements BaseAreaService {}

