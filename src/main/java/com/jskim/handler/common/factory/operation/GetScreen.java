package com.jskim.handler.common.factory.operation;

import com.jskim.handler.getindexhtml.IndexHTMLHandler;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class GetScreen implements LinesGet<Object>{

    private final String queryContent;

    @Override
    public Object get() {
        String screenName = queryContent.split(" ")[1];
        try {
            return Objects.requireNonNull(
                            IndexHTMLHandler.class
                                    .getResourceAsStream("/templates/user" + screenName))
                    .readAllBytes();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
