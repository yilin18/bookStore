package cn.bookStore.admin.notice.service;

import cn.bookStore.commons.beans.Notice;
import cn.bookStore.utils.PageModel;

import java.util.List;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/5/25
 */
public interface IAdminNoticeService {

    List<Notice> findNotice(Notice notice, PageModel pageModel);

    int findNoticeCount(Notice notice, PageModel pageModel);

    Notice findNoticeById(Integer id);

    int updateNoticeById(Notice notice);

    int deleteNoticeById(int id);

    int addNotice(Notice notice);
}
