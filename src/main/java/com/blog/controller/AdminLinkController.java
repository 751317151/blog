package com.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.blog.pojo.Link;
import com.blog.service.LinkService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/adminLinkController")
public class AdminLinkController {

    @Resource
    private LinkService linkService;

    @ResponseBody
    @RequestMapping("/addLink")
    public boolean addLink(@RequestBody Link link) {
        if (link.getLinkUrl().equals("")) {
            return false;
        } else {
            linkService.addLink(link);
            return true;
        }
    }

    @ResponseBody
    @RequestMapping("/deleteLinkById")
    public Boolean deleteLinkById(@RequestParam("id") Integer deleteLinkId) {
        linkService.deleteLinkById(deleteLinkId);
        return true;
    }

    @ResponseBody
    @RequestMapping("/modifyLink")
    public boolean modifyLink(@RequestBody Link link) {
        if (link.getId() == null) {
            return false;
        } else {
            linkService.updateLink(link);
            return true;
        }
    }



}
