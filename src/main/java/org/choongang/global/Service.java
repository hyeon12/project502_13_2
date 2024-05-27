package org.choongang.global;

public interface Service<T> {
    //사용자가 입력한 데이터를 넘겨주면 처리
    void process(T form);
}
