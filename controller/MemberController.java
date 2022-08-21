package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
//    private final MemberService memberService = new MemberService();

        //하나를 생성해서 공용으로 쓰기
        //스프링 컨테이너에 등록 -> 딱 하나만 등록 가능, 부가적인 효과를 볼 수 있음.
        private final MemberService memberService; //

        @Autowired //MemberController가 생성 될 때 스프링빈에 있는 memberService의 객체를 넣어줌 -> 의존관계를 주입
        public MemberController(MemberService memberService) {
            this.memberService = memberService;
        }


        //회원 웹 기능 - 회원가입
        @GetMapping("/members/new") //2.회원관리 버튼을 누르면 /members/new로 오고 createMemberForm.html을 보여줌
        public String createForm() {
            return "members/createMemberForm";
        }

        @PostMapping("/members/new") //3. 등록버튼을 누르면 post방식으로 넘어옴
        public String create(MemberForm form) {
            Member member =  new Member();
            member.setName(form.getName()); //4.getName으로 setName꺼내오기

            memberService.join(member); //5. member가입
            return "redirect:/";
        }

        @GetMapping("/members")
        public String list(Model model) {
            List<Member> members = memberService.findMembers();
            model.addAttribute("members", members);
            return "members/memberList";
        }
}
