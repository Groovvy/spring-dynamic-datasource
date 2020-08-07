package com.gitee.groovvy.config;


import com.gitee.groovvy.entity.User;
import com.gitee.groovvy.service.ProcedureStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wanghuaan
 * @date 2020/7/21
 **/
@Component
public class DataSourceContext {

    @Autowired
    private Map<String, ProcedureStrategy> map = new HashMap<>();

    public User searchUserList(String type){
        return map.get(type).searchUserList(type);
    }





//    public Object executeFunction(String func,Class<?>[] parameterTypes,Object[] objects) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
//        Class clazz = codeService.getClass();
//        Object o = clazz.newInstance();
//        Method method = clazz.getMethod(func,parameterTypes);
//        System.out.println(method);
//        return method.invoke(o,objects);
//    }
}
