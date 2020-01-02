package cn.bookStore.admin.notice.service;

import cn.bookStore.admin.notice.dao.IAdminNoticeDao;
import cn.bookStore.commons.beans.Notice;
import cn.bookStore.utils.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/5/25
 */
@Service

public class AdminNoticeServiceImpl implements IAdminNoticeService {
    @Resource
    private IAdminNoticeDao noticeDao;


    @Override
    public List<Notice> findNotice(Notice notice, PageModel pageModel) {
        Map<String,Object> map=new HashMap<>();
        map.put("notice", notice);
        map.put("pageModel", pageModel);
        return noticeDao.selectNotice(map);
    }

    @Override
    public int findNoticeCount(Notice notice, PageModel pageModel) {
        Map<String,Object> map=new HashMap<>();
        map.put("notice", notice);
        map.put("pageModel", pageModel);
        return noticeDao.selectNoticeCount(map);
    }

    @Override
    public Notice findNoticeById(Integer id) {
        return noticeDao.selectNoticeById(id);
    }

    @Override
    public int updateNoticeById(Notice notice) {
        return noticeDao.updateNoticeById(notice);
    }

    @Override
    public int deleteNoticeById(int id) {
        return noticeDao.deleteNoticeById(id);
    }

    @Override
    public int addNotice(Notice notice) {
        return noticeDao.addNotice(notice);
    }
}
