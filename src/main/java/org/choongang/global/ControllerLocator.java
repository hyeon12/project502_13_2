package org.choongang.global;

import org.choongang.global.constants.Menu;

public interface ControllerLocator {
    //어떤 컨트롤러가 올지 모른다..! -> 설계 다형성을 이용
    Controller find(Menu menu);
}
