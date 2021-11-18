package cn.xavier.system.controller;

import cn.xavier.basic.util.AjaxResponse;
import cn.xavier.basic.util.PageList;
import cn.xavier.system.domain.DictionaryDetail;
import cn.xavier.system.query.DictionaryDetailQuery;
import cn.xavier.system.service.IDictionaryDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典明细的RestFul接口
 *
 * @author Zheng -Wei Shui
 * @date 11 /18/2021
 */
@RestController
@RequestMapping("/dicDetail")
@Api(tags = "数据字典明细接口",description = "数据字典明细接口详细描述")
public class DictionaryDetailController {

    @Autowired
    private IDictionaryDetailService dictionaryDetailService;

    /**
     * 新增或修改数据字典明细
     *
     * @return the ajax response
     */
    @PutMapping
    @ApiOperation(value = "数据字典明细添加或修改",notes = "如果有id就是修改否则就是添加")
    public AjaxResponse addOrUpdate(@RequestBody DictionaryDetail dictionaryDetail) {
        if (dictionaryDetail.getId() == null) {
            dictionaryDetailService.add(dictionaryDetail);
        } else {
            dictionaryDetailService.update(dictionaryDetail);
        }
        return null;
    }

    /**
     * Remove by id , {}内为占位符
     * 一个参数，pathvariable可以不写value
     * @return the ajax response
     */
    @DeleteMapping("/{id}")
    public AjaxResponse removeById(@PathVariable Long id) {
        dictionaryDetailService.remove(id);
        return null;
    }

    /**
     * 查全部
     *
     * @return
     */
    @GetMapping
    public List<DictionaryDetail> queryAll() {
        return dictionaryDetailService.findAll();
    }

    /**
     * 根据类型id查
     *
     * @return
     */
    @GetMapping("/{typeId}")
    public List<DictionaryDetail> queryByTypeId(@PathVariable Long typeId) {
        return dictionaryDetailService.findByTypeId(typeId);
    }

    /**
     * 分页 + 高级查询
     *
     * @param
     * @return
     */
    @PostMapping("/list")
    public PageList<DictionaryDetail> queryPage(@RequestBody DictionaryDetailQuery dictionaryDetailQuery) {
         return dictionaryDetailService.queryPage(dictionaryDetailQuery);
    }

    /**
     * 批量删除数据字典明细
     *
     * @param ids ids
     * @return the ajax response
     */
    @PatchMapping
    public AjaxResponse batchRemove(@RequestBody List<Long> ids) {
        dictionaryDetailService.batchRemove(ids);
        return null;
    }

}
