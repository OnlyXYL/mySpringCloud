package top.wikl.service_provider.controller;

import top.wikl.service_provider.config.RemoteProperties;

import javax.annotation.Resource;

public class BaseController {
    @Resource
    RemoteProperties remoteProperties;
}
