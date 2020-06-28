package com.silin.mongdb.controller;

import com.silin.mongdb.entity.Category;
import com.silin.mongdb.form.CategoryForm;
import com.silin.mongdb.repository.CategoryRepository;
import com.silin.mongdb.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Create by silin
 * Date:  2020/3/20 22:59
 */
@RestController
@RequestMapping("/admin/category")
@Slf4j
public class AdminCategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * 类目列表
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<Category> categoryList = categoryRepository.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("category/list", map);
    }

    /**
     * 展示
     * @param id
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "id", required = false) String id,
                              Map<String, Object> map) {
        if (id != null) {

            Category category = categoryRepository.findById(Long.valueOf(id)).orElse(null);
            map.put("category", category);
        }

        return new ModelAndView("category/index", map);
    }

    /**
     * 保存/更新
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@RequestParam("id")String id,
                            @Valid CategoryForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/silin/admin/category/index");

            return new ModelAndView("common/error", map);
        }

        Category category = new Category();
        log.info("id:{}是不是空", id);
        try {
            if (form.getId() != null) {
                category = categoryRepository.findById(Long.valueOf(form.getId())).orElse(null);
                BeanUtils.copyProperties(form, category);
                log.info("走修改！！！");
            }else{
                form.setId(Long.valueOf(KeyUtil.genUniqueKey()));
                BeanUtils.copyProperties(form, category);
                log.info("走新增！！！");
            }
            categoryRepository.save(category);

        } catch (Exception e) {
            map.put("msg", e.getMessage());
            map.put("url", "/silin/admin/category/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/silin/admin/category/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam(value = "id", required = false) String id,
                              Map<String, Object> map) {
        if (id != null) {
            categoryRepository.deleteById(Long.valueOf(id));
        }
        List<Category> categoryList = categoryRepository.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("category/list", map);
    }

}
