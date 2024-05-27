package org.choongang.main;

import org.choongang.global.Controller;
import org.choongang.global.ControllerLocator;
import org.choongang.global.Router;
import org.choongang.global.constants.Menu;
import org.choongang.main.controllers.MainController;
import org.choongang.member.controllers.JoinController;
import org.choongang.member.controllers.LoginController;
import org.choongang.member.controllers.MemberControllerLocator;

import java.sql.SQLOutput;

//각각의 메뉴 컨트롤러 객체를 찾아 공통적인 템플릿 메서드 run() 실행
public class MainRouter implements Router {
    //기능에 대한 부분이기 때문에 매번 객체 생성X, 한번 생성하고 공유
    //싱글톤패턴 S (메뉴 선택할때마다 객체 생성X)
    private static Router instance;

    private MainRouter() {}

    public static Router getInstance() {
        if (instance == null) {
            instance = new MainRouter();
        }

        return instance;
    }

    //싱글톤 패턴 E

    @Override
    public void change(Menu menu) {
        ControllerLocator memlocator = MemberControllerLocator.getInstance();

        Controller controller = null;
        switch(menu){
            //메뉴를 재선택할때마다 컨트롤 객체를 재생성? -> 비효율적!
            //case LOGIN: controller = new LoginController(); (X)
            //특정 객체를 한 번 만들고, 필요할때마다 찾아서 가져오는 방법으로
            case JOIN: controller = memlocator.find(Menu.JOIN);break;
            case LOGIN: controller = memlocator.find(Menu.LOGIN); break;
            default: controller = new MainController();
        }

        controller.run(); // common(), show(), prompt()
    }

    @Override
    public void start() {
    //q,exit,quit 이 아니면 종료되지 않고 무한 반복될 수 있도록
        while(true){
            change(Menu.MAIN); //첫 화면 - 메인 컨트롤러 출력 화면
        }

    }
}
