package org.choongang.global;

import org.choongang.global.constants.Menu;

/**
 * 사용자가 입력한 메뉴 번호, 문구에 따라 해당하는 컨트롤러로 연결(이동) 연결=메뉴변경
 * 메뉴 상수 형태로
 */
public interface Router {
    void change(Menu menu); //입력한 메뉴로 변경
    void start(); //해당 컨트롤 객체 만들고, 시작
}
