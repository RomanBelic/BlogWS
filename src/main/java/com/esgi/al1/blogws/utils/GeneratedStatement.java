package com.esgi.al1.blogws.utils;

import java.util.List;

/**
 * Created by Romaaan on 20/03/2017.
 */
public class GeneratedStatement {

    private final List<SqlParam> lstParams;
    private final String paramStr;

    public GeneratedStatement(List<SqlParam> lstParams, String paramStr) {
        this.lstParams = lstParams;
        this.paramStr = paramStr;
    }

    public List<SqlParam> getLstParams() {
        return lstParams;
    }

    public String getParamStr() {
        return paramStr;
    }

    public SqlParam getLastParam(){
        return (lstParams.size() > 0) ? lstParams.get(lstParams.size() - 1) : new SqlParam(0, null);
    }

}
