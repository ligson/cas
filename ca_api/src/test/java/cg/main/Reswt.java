package cg.main;

import org.ligson.fw.core.facade.base.dto.BaseQueryPageResponseDto;
import org.ligson.fw.core.facade.base.result.Result;


/**
 * Created by ligson on 2016/5/24.
 */
public class Reswt {
    public static void main(String[] args) {
        Result<BaseQueryPageResponseDto> result = Result.getSuccessResult(new BaseQueryPageResponseDto());
        System.out.println(result.getClass().getComponentType());
    }
}
