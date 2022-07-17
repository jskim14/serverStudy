package com.jskim.comm;

@FunctionalInterface
public interface WebHandlerInf<ParamT> {
    void run(ParamT paramT);
}
