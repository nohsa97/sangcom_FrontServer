package capstone.sangcom.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/report")
public class UserReportController {

    @GetMapping("/board")
    public String reportedBoardForm() {
        return "user/boardReportList";
    }

    @GetMapping("/reply")
    public String reportedReplyForm() {
        return "user/replyReportList";
    }
}
