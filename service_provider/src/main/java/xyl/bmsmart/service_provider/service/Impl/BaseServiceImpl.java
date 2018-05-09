package xyl.bmsmart.service_provider.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import xyl.bmsmart.service_provider.mapper.BaseMapper;
import xyl.bmsmart.service_provider.service.BaseService;

public class BaseServiceImpl<M extends BaseMapper<T>, T> implements BaseService<T> {
    @Autowired
    protected M baseMapper;
}