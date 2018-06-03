package top.wikl.service_provider.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import top.wikl.service_provider.mapper.BaseMapper;
import top.wikl.service_provider.service.BaseService;

public class BaseServiceImpl<M extends BaseMapper<T>, T> implements BaseService<T> {
    @Autowired
    protected M baseMapper;
}