package com.jskim.handler.common.factory;

import com.jskim.handler.common.code.TYPE;
import com.jskim.handler.common.factory.operation.GetQueryString;
import com.jskim.handler.common.factory.operation.GetScreen;
import com.jskim.handler.common.factory.operation.LinesGet;

public class GetFactory {

    public static LinesGet<Object> get(TYPE _type, String queryContent) throws Exception {
        switch (_type) {
            case QUERY_STRING:
                return new GetQueryString(queryContent);
            case SCREEN:
                return new GetScreen(queryContent);
            default:
                throw new Exception();
        }
    }
}
