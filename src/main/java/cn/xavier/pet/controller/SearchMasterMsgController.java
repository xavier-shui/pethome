package cn.xavier.pet.controller;

import cn.xavier.basic.util.AjaxResponse;
import cn.xavier.basic.util.LoginContext;
import cn.xavier.basic.util.PageList;
import cn.xavier.pet.domain.Pet;
import cn.xavier.pet.domain.SearchMasterMsg;
import cn.xavier.pet.query.SearchMasterMsgQuery;
import cn.xavier.pet.service.ISearchMasterMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/searchMasterMsg")
public class SearchMasterMsgController {

    @Autowired
    private ISearchMasterMsgService seachMasterMsgService;


    /**
     * Publish ajax response 发布寻主消息
     *
     * @param searchMasterMsg search master msg
     * @return the ajax response
     */
    @PostMapping("/publish")
    public AjaxResponse publish(@RequestBody SearchMasterMsg searchMasterMsg, HttpServletRequest request) {
        seachMasterMsgService.publish(searchMasterMsg, LoginContext.getLoginInfo(request));
        return AjaxResponse.of();
    }

    /**
     * List page list
     * 根据request可以查出loginInfo判断是主站用户还是后台管理员
     * 主站用户只能看自己所有的
     * 平台人员可以看所有， 状态由前端传
     * 店长和店员可以看自己店铺的， 状态由前端传
     *
     * @param query   query
     * @param request request
     * @return the page list
     */
    @PostMapping("/list")
    public PageList<SearchMasterMsg> list(@RequestBody SearchMasterMsgQuery query, HttpServletRequest request) {
        return seachMasterMsgService.queryPage(query, LoginContext.getLoginInfo(request));
    }

    /**
     * 后台处理寻主消息
     *
     * @param pet pet
     * @return the ajax response
     */
    @PutMapping("/handle")
    public AjaxResponse handle(@RequestBody Pet pet, HttpServletRequest request) {
        seachMasterMsgService.handle(pet, LoginContext.getLoginInfo(request));
        return AjaxResponse.of();
    }

    @GetMapping
    public List<SearchMasterMsg> getAll() {
        return seachMasterMsgService.findAll();
    }

}
