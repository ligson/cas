package org.ca.cas.offlineca.api;

import org.ca.cas.offlineca.dto.*;
import org.ligson.fw.core.facade.annotation.Api;
import org.ligson.fw.core.facade.base.result.Result;

/**
 * Created by ligson on 2016/5/20.
 * 离线CA管理员接口
 */
public interface OfflineAdminApi {
    /***
     * 增加管理员接口
     *
     * @param requestDto
     * @return
     */
    @Api(name = "增加管理员接口")
    Result<OfflineAdminAddResponseDto> addAdmin(OfflineAdminAddRequestDto requestDto);

    /***
     * 删除管理员接口
     *
     * @param requestDto
     * @return
     */
    @Api(name = "删除管理员接口")
    Result<OfflineAdminDeleteResponseDto> deleteAdmin(OfflineAdminDeleteRequestDto requestDto);

    /***
     * 修改管理员接口
     *
     * @param requestDto
     * @return
     */
    @Api(name = "修改管理员接口")
    Result<OfflineAdminModifyResponseDto> modifyAdmin(OfflineAdminModifyRequestDto requestDto);

    /****
     * 管理员查询接口
     *
     * @param requestDto
     * @return
     */
    @Api(name = "管理员查询接口")
    Result<OfflineAdminQueryResponseDto> queryAdmin(OfflineAdminQueryRequestDto requestDto);
}
