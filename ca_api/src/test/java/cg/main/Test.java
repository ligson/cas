package cg.main;

import cg.model.ApiCollection;
import cg.model.ApiModel;
import cg.model.ParamModel;
import org.ca.cas.cert.api.CertApi;
import org.ca.cas.offlineca.api.OfflineAdminApi;
import org.ca.cas.offlineca.api.OfflineCaApi;
import org.ca.cas.offlineca.vo.OfflineAdmin;
import org.ca.cas.user.api.UserApi;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ligson on 2016/5/24.
 */
public class Test {
    public static void main(String[] args) {
        ApiCollection collection = new ApiCollection(OfflineCaApi.class);
        System.out.println(collection.getClazz().getName());
        //System.out.println(collection);
        for (ApiModel apiModel : collection.getApiModelList()) {
            System.out.println("------------------------");
            System.out.println("接口名称：" + apiModel.getApi().name());
            System.out.println("接口方法申明：" + apiModel.getMethod());
            for (ParamModel paramModel : apiModel.getRequestDto().getParamModels()) {
                System.out.println("`" + paramModel.getField().getName() + "`:");
                if (paramModel.getParam() != null) {
                    System.out.print(paramModel.getParam().name() + ",");
                    System.out.print(paramModel.getParam().required() ? "必备" : "可选");
                    System.out.println(",描述：" + paramModel.getParam().description());
                }
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("collection", collection);
        TemplateEngine.write("API.ftl", map, new File("./doc/" + collection.getClazz().getSimpleName() + ".md"));

    }
}
