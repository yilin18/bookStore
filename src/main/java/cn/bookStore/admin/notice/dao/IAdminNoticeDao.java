package cn.bookStore.admin.notice.dao;

import cn.bookStore.commons.beans.Notice;

import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/5/25
 */
public interface IAdminNoticeDao {
    List<Notice> selectNotice(Map<String, Object> map);

    int selectNoticeCount(Map<String, Object> map);

    Notice selectNoticeById(Integer id);

    int updateNoticeById(Notice notice);

    int deleteNoticeById(int id);

    int addNotice(Notice notice);
}
