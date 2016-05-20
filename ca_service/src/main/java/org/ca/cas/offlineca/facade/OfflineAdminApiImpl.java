package org.ca.cas.offlineca.facade;

import org.ca.cas.offlineca.api.OfflineAdminApi;
import org.ca.cas.offlineca.biz.OfflineAdminAddBiz;
import org.ca.cas.offlineca.biz.OfflineAdminDeleteBiz;
import org.ca.cas.offlineca.biz.OfflineAdminModifyBiz;
import org.ca.cas.offlineca.biz.OfflineAdminQueryBiz;
import org.ca.cas.offlineca.dto.*;
import org.ligson.fw.core.facade.base.result.Result;

import javax.annotation.Resource;

/**
 * Created by ligson on 2016/5/20.
 */
public class OfflineAdminApiImpl implements OfflineAdminApi {
    @Resource
    private OfflineAdminAddBiz offlineAdminAddBiz;
    @Resource
    private OfflineAdminDeleteBiz offlineAdminDeleteBiz;
    @Resource
    private OfflineAdminModifyBiz offlineAdminModifyBiz;
    @Resource
    private OfflineAdminQueryBiz offlineAdminQueryBiz;

    @Override
    public Result<OfflineAdminAddResponseDto> addAdmin(OfflineAdminAddRequestDto requestDto) {
        return offlineAdminAddBiz.operation(requestDto);
    }

    @Override
    public Result<OfflineAdminDeleteResponseDto> deleteAdmin(OfflineAdminDeleteRequestDto requestDto) {
        return offlineAdminDeleteBiz.operation(requestDto);
    }

    @Override
    public Result<OfflineAdminModifyResponseDto> modifyAdmin(OfflineAdminModifyRequestDto requestDto) {
        return offlineAdminModifyBiz.operation(requestDto);
    }

    @Override
    public Result<OfflineAdminQueryResponseDto> queryAdmin(OfflineAdminQueryRequestDto requestDto) {
        return offlineAdminQueryBiz.operation(requestDto);
    }
}
