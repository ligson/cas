package org.ca.cas.offlineca.api;

import org.ca.cas.offlineca.dto.*;
import org.ligson.fw.core.facade.base.result.Result;

/**
 * Created by ligson on 2016/5/20.
 */
public interface OfflineAdminApi {
    Result<OfflineAdminAddResponseDto> addAdmin(OfflineAdminAddRequestDto requestDto);

    Result<OfflineAdminDeleteResponseDto> deleteAdmin(OfflineAdminDeleteRequestDto requestDto);

    Result<OfflineAdminModifyResponseDto> modifyAdmin(OfflineAdminModifyRequestDto requestDto);

    Result<OfflineAdminQueryResponseDto> queryAdmin(OfflineAdminQueryRequestDto requestDto);
}
