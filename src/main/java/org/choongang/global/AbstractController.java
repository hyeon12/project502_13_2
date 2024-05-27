package org.choongang.global;

import org.choongang.global.constants.Menu;
import org.choongang.main.MainRouter;
import org.choongang.template.Templates;

import java.util.Scanner;
import java.util.function.Predicate;

public abstract class AbstractController implements Controller{

    //하위쪽에서도 이용할 수 있으므로 protected
    protected Scanner sc;

    public AbstractController(){
        sc = new Scanner(System.in); //입력받는 부분
    }
    /**
     * 상단 공통 출력 부분 (고정된 부분)
     */
    public void common(){
        System.out.print(Templates.getInstance().doubleLine());
        System.out.println("학생 관리 프로그램 Ver1.0");
        System.out.println(Templates.getInstance().doubleLine());
    }

    /**
     * 입력 항목
     * - 문자 : q, exit, quit - 종료
     * - 숫자 : 메뉴 항목
     */
    public void prompt(){
        System.out.print("메뉴 선택: ");
        String menu = sc.nextLine();
        if(menu.equals("q") || menu.equals("quit") || menu.equals("exit")){
            System.out.println("종료 합니다.");
            System.exit(0); // 0 - 정상종료, 1 - 비정상 종료
        }

        try {
            int m = Integer.parseInt(menu); // 숫자만 입력
            change(m); //메뉴변경 - 숫자 입력 시
        }catch (Exception e){ //숫자 이외 문자 입력 시
            e.printStackTrace();
            System.out.println("메뉴는 숫자로 입력하세요.");
        }
    }

    /**
     * 템플릿 메서드 패턴 구현 : 특정 절차가 고정되어 있는 경우
     */
    @Override
    public void run() {//실행절차부분 - 절대 바뀌면 안되는 절차이므로 final
        common();
        show();//재정의 - 각 컨트롤마다 다르게 정의 (추상클래스를 상속받는 프롬프터가 각각 다르게 구성)
        prompt();
    }

    private void change(int menuNo){
        Menu menu = null;
        switch(menuNo){
            case 1: menu = Menu.JOIN; break; //회원가입
            case 2: menu = Menu.LOGIN; break; //로그인
            default: menu = Menu.MAIN; //메인메뉴


        }
        //메뉴 컨트롤러 변경 처리 - Router
        MainRouter.getInstance().change(menu);
    }

    //메뉴 컨트롤러 변경 처리 - Router
    //new MainRouter().change(menu);
    //메뉴 전환할때마다 객체 생성? 메모리 소비가 많아지고 성능 저하
    // -> 싱글톤 형태로 한 번만 생성하여 공유하는 방식을 사용한다!

    /**
     * 입력과 검증을 함께 진행
     * @param message : 항목 메세지
     * @param predicate : 판별식
     */
    protected String promptWithValidation(String message, Predicate<String> predicate) {
        String str = null;
        do {
            System.out.print(message);
            str = sc.nextLine();
        } while (!predicate.test(str)); //판별식이 실패했을 때는 반복 실행(다시 입력)
        return str;
    }
}
