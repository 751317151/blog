package com.blog.service.impl;

import org.springframework.stereotype.Service;
import com.blog.dao.LinkDao;
import com.blog.pojo.Link;
import com.blog.service.LinkService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class LinkServiceImpl implements LinkService {

    @Resource
    private LinkDao linkDao;

    @Override
    public List<Link> getAllLink() {
        return linkDao.getAllLink();
    }

    @Override
    public void addLink(Link link) {
        linkDao.addLink(link);
    }

    @Override
    public void deleteLinkById(Integer id) {
        linkDao.deleteLinkById(id);
    }

    @Override
    public void updateLink(Link link) {
        linkDao.updateLink(link);
    }

    @Override
    public Link getLinkById(int linkId) {
        return linkDao.getLinkById(linkId);
    }

}
