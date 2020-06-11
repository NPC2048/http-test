package com.liangyuelong.test.elapsedtime;

public interface Executor {

    void execute();

    default String name() {
        return this.toString();
    }

}
