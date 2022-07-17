package com.jskim.handler.common;

import com.jskim.handler.common.mapping.MappingHandler;
import com.jskim.handler.common.mapping.mapper.GetMapping;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@Slf4j
public class DispatcherServlet implements Runnable{

    private final Socket connection;


    public DispatcherServlet(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try(InputStream in = connection.getInputStream();
            OutputStream out = connection.getOutputStream()) {
            MappingHandler mappingHandler = new GetMapping(in, out);
            mappingHandler.process();
        }catch (Exception exception){
            log.error(exception.getMessage());
        }
    }
}
