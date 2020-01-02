package cn.bookStore.admin.notice.handler;

import cn.bookStore.admin.notice.service.IAdminNoticeService;
import cn.bookStore.commons.beans.Notice;
import cn.bookStore.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/5/25
 */
@RequestMapping("/admin/notices/")
@Controller
public class AdminNoticeHandler {

    @Autowired
    private IAdminNoticeService noticeService;

    @RequestMapping("/noticesManager.do")
    public String noticesManage(Model model, Notice notice, @RequestParam(defaultValue = "1") int pageIndex) {
        PageModel pageModel = new PageModel();
        pageModel.setPageSize(8);
        pageModel.setPageIndex(pageIndex);
        int count = noticeService.findNoticeCount(notice, pageModel);
        pageModel.setRecordCount(count);
        List<Notice> notices = noticeService.findNotice(notice, pageModel);
        model.addAttribute("notices", notices);
        model.addAttribute("pageModel", pageModel);
        return "/admin/notices/list.jsp";
    }

    @RequestMapping("updateByNoticeId.do")
    public String updateByNoticeId(Integer id, Notice notice,Model model, String type) {
        notice.setN_id(id);
        Date date=new Date();
        Timestamp ts = new Timestamp(date.getTime());
        notice.setN_time(ts);
        // System.out.println(notice);
        if ("admin".equals(type)) {

            notice = noticeService.findNoticeById(id);
            model.addAttribute("notice", notice);
            return "/admin/notices/edit.jsp";
        }
        int row=noticeService.updateNoticeById(notice);
        if (row>0)
            return "/admin/notices/noticesManager.do";
        else
            return "/client/fail.jsp";

    }
    @RequestMapping("/deleteNotice.do")
    public String deleteNotice(int id){
        int row=noticeService.deleteNoticeById(id);
        if (row>0)
            return "/admin/notices/noticesManager.do";
        else
            return "/client/fail.jsp";
    }
    @RequestMapping("/addNotice.do")
    public String addNotice(Notice notice){
        Date date=new Date();
        Timestamp ts = new Timestamp(date.getTime());
        notice.setN_time(ts);
        int row =noticeService.addNotice(notice);
        if (row>0)
            return "/admin/notices/noticesManager.do";
        else
            return "/client/fail.jsp";
    }
}
