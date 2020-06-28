package com.silin.mongdb.controller;

import com.silin.mongdb.constant.CookieConstant;
import com.silin.mongdb.constant.RedisConstant;
import com.silin.mongdb.entity.AdminUser;
import com.silin.mongdb.enums.ResultEnum;
import com.silin.mongdb.exception.SilinException;
import com.silin.mongdb.form.AdminUserForm;
import com.silin.mongdb.repository.AdminUserRepository;
import com.silin.mongdb.utils.CookieUtil;
import com.silin.mongdb.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Create by silin
 * Date:  2020/3/28 16:43
 */

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminUserController {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @GetMapping("/loginAdmin")
    public String loginAdmin(@RequestParam("phone") String phone,
                             @RequestParam("password") String password,
                             HttpServletResponse response) {
//        AdminUser sellerInfo = adminUserRepository.findByPhone(phone);

        List<AdminUser> adminUserList = adminUserRepository.findAll();
        AdminUser adminUser = new AdminUser();
        for(AdminUser adminUserFor : adminUserList){
            if(phone.equals(adminUserFor.getPhone())){
                BeanUtils.copyProperties(adminUserFor, adminUser);
            }
        }

        log.info("商家信息={}", adminUser);
        if (adminUser != null && adminUser.getPassword().equals(password)) {
            String token = UUID.randomUUID().toString();
            log.info("登录成功的token={}", token);
            Integer expire = RedisConstant.EXPIRE;
            //3. 设置token至cookie
            CookieUtil.set(response, CookieConstant.TOKEN, token, expire);
            return "登录成功";
        } else {
            throw new SilinException(ResultEnum.LOGIN_FAIL);
        }
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String, Object> map) {
        //1. 从cookie里查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //2. 清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }
        map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url", "/silin/admin/login");
        return new ModelAndView("common/success", map);
    }

    /*
     * 页面相关
     * */

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<AdminUser> categoryList = adminUserRepository.findAll();

        map.put("categoryList", categoryList);
        return new ModelAndView("admin/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "id", required = false) Long id,
                              Map<String, Object> map) {
//        SellerInfo sellerInfo = repository.findBySellerId(sellerId);

        if(!"".equals(id)) {
            AdminUser adminUser = adminUserRepository.findById(id).orElse(null);
            map.put("category", adminUser);
        }
        return new ModelAndView("admin/index", map);
    }

    /**
     * 保存/更新
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid AdminUserForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        log.info("SellerForm={}", form);
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/silin/admin/index");
            return new ModelAndView("common/error", map);
        }
        AdminUser adminUser = new AdminUser();
        System.out.println("13456:" + form.getId());
        try {
            if (!"".equals(form.getId())) {
                System.out.println("223:" + form.getId());
//                sellerInfo = adminUserRepository.findBySellerId(form.getSellerId());
                List<AdminUser> adminUserList = adminUserRepository.findAll();
                for(AdminUser adminUserFor : adminUserList){
                    if(form.getId().equals(adminUserFor.getId())){
                        BeanUtils.copyProperties(adminUserFor, adminUser);
                        BeanUtils.copyProperties(form, adminUser);
                        System.out.println("223" + adminUser.getId());
                        break;
                    }
                }
            }else{
                BeanUtils.copyProperties(form, adminUser);
                adminUser.setId(Long.valueOf(KeyUtil.genUniqueKey()));
                adminUser.setCreateTime(new Date());
                adminUser.setUpdateTime(new Date());
            }
            System.out.println("123:" + adminUser.getId());
            adminUserRepository.save(adminUser);
        } catch (SilinException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/silin/admin/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/silin/admin/list");
        return new ModelAndView("common/success", map);
    }


    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam(value = "id", required = false) Long id,
                              Map<String, Object> map) {

        adminUserRepository.deleteById(id);


        List<AdminUser> categoryList = adminUserRepository.findAll();

        map.put("categoryList", categoryList);
        return new ModelAndView("admin/list", map);
    }

    @GetMapping("/login")
    public ModelAndView login(){

        return new ModelAndView("common/loginView");
    }
}
