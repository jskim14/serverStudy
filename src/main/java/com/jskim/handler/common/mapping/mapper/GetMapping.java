package com.jskim.handler.common.mapping.mapper;

import com.jskim.handler.common.code.TYPE;
import com.jskim.handler.common.factory.GetFactory;
import com.jskim.handler.common.mapping.MappingHandler;
import com.jskim.handler.common.method.HTTP_METHOD;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class GetMapping extends MappingHandler {

    private TYPE _type;
    private byte[] responseBody;
    private final StringBuffer responseContent = new StringBuffer();

    public GetMapping(InputStream inputStream, OutputStream outputStream) {
        super(inputStream, outputStream);
    }

    @Override
    protected String readRequestContent(BufferedReader bufferedReader) throws IOException {
        return bufferedReader.readLine();
    }

    @Override
    protected BufferedReader getBufferedReaderForRequest(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
    }

    @Override
    protected void doProcess(String requestContent) throws Exception {
        if(isGetForScreen(requestContent)) {
            responseBody = (byte[]) GetFactory.get(TYPE.SCREEN, requestContent).get()
            ;
            _type = TYPE.SCREEN;
        }

        if (!isGetForScreen(requestContent)){
            responseContent.append(GetFactory.get(TYPE.QUERY_STRING, requestContent).get());
            _type = TYPE.QUERY_STRING;
        }

    }

    private boolean isGetForScreen(String requestContent) {
        return requestContent != null && HTTP_METHOD.GET.isContain(requestContent) && requestContent.contains(".html");
    }

    @Override
    protected void responseHandling(OutputStream outputStream) {

    }
}
