package com.jskim.handler.common.mapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class MappingHandler {

    private final InputStream inputStream;
    private final OutputStream outputStream;

    public MappingHandler(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public void process() throws Exception {
        BufferedReader bufferedReader = getBufferedReaderForRequest(inputStream);
        String requestContent = readRequestContent(bufferedReader);

        getHeaderInfo(bufferedReader);
        doProcess(requestContent);
        responseHandling(outputStream);


    }

    protected abstract String readRequestContent(BufferedReader bufferedReader) throws IOException;

    protected abstract BufferedReader getBufferedReaderForRequest(InputStream inputStream);

    private void getHeaderInfo(BufferedReader bufferedReader) throws Exception{
        String request = "";

        do{
            request = bufferedReader.readLine();
        }
        while (!request.isEmpty());
    }

    protected abstract void doProcess(String request) throws Exception;

    protected abstract void responseHandling(OutputStream outputStream);

}
