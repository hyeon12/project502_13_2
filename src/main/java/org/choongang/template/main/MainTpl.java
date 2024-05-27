package org.choongang.template.main;

import org.choongang.template.Template;

public class MainTpl implements Template {
    //메인쪽 템플릿
    @Override
    public String getTpl() {
        StringBuffer sb = new StringBuffer(2000);
        sb.append("메뉴를 선택하세요.\n")
                .append("1. 회원가입\n")
                .append("2. 로그인\n")
                .append("---------------------------\n");

        return sb.toString();
    }
}
