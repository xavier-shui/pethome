package cn.xavier.system.controller;

import cn.xavier.basic.util.AjaxResponse;
import cn.xavier.basic.util.PageList;
import cn.xavier.system.domain.DictionaryType;
import cn.xavier.system.query.DictionaryTypeQuery;
import cn.xavier.system.service.IDictionaryTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典类型的RestFul接口
 *
 * @author Zheng -Wei Shui
 * @date 11 /18/2021
 */
@RestController
@RequestMapping("/dicType")
@Api(tags = "数据字典类型接口",description = "数据字典类型接口详细描述")
public class DictionaryTypeController {

    @Autowired
    private IDictionaryTypeService dictionaryTypeService;

    /**
     * 新增或修改数据字典类型
     *
     * @return the ajax response
     */
    @PutMapping
    @ApiOperation(value = "数据字典类型添加或修改",notes = "如果有id就是修改否则就是添加")
    public AjaxResponse addOrUpdate(@RequestBody DictionaryType dictionaryType) {
        if (dictionaryType.getId() == null) {
            dictionaryTypeService.add(dictionaryType);
        } else {
            dictionaryTypeService.update(dictionaryType);
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
        dictionaryTypeService.remove(id);
        return null;
    }

    /**
     * 查全部
     *
     * @return
     */
    @GetMapping
    public List<DictionaryType> queryAll() {
        return dictionaryTypeService.findAll();
    }

    /**
     * 分页 + 高级查询
     *
     * @param
     * @return
     */
    @PostMapping("/list")
    public PageList<DictionaryType> queryPage(@RequestBody DictionaryTypeQuery dictionaryTypeQuery) {
         return dictionaryTypeService.queryPage(dictionaryTypeQuery);
    }

    /**
     * 批量删除数据字典类型
     *
     * @param ids ids
     * @return the ajax response
     */
    @PatchMapping
    public AjaxResponse batchRemove(@RequestBody List<Long> ids) {
        dictionaryTypeService.batchRemove(ids);
        return null;
    }

}
