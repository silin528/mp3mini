package com.silin.mongdb.controller;

import cn.hutool.core.bean.BeanUtil;
import com.silin.mongdb.VO.ResultVO;
import com.silin.mongdb.entity.User;
import com.silin.mongdb.enums.ResultEnum;
import com.silin.mongdb.exception.SilinException;
import com.silin.mongdb.form.UserForm;
import com.silin.mongdb.repository.UserRepository;
import com.silin.mongdb.utils.KeyUtil;
import com.silin.mongdb.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Create by silin
 * Date:  2019/6/27 23:58
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //创建订单
    @PostMapping("/save")
    public ResultVO create(@Valid UserForm userForm,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("参数不正确, userForm={}", userForm);
            throw new SilinException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        List<User> userList = userRepository.findAll();
        User user = new User();
        for(User userfor : userList){
            if(userForm.getOpenid().equals(userfor.getOpenid())){
                user.setId(userfor.getId());
            }else{
                user.setId(Long.valueOf(KeyUtil.genUniqueKey()));
            }
        }
        user.setUsername(userForm.getUsername());
        user.setOpenid(userForm.getOpenid());
//        user.setPhone(userForm.getPhone());


        return ResultVOUtil.success(userRepository.save(user));
    }

    @GetMapping("/getUserInfo")
    public ResultVO getUserInfo(@RequestParam("openid") String openid) {
        List<User> userList = userRepository.findAll();
        User user = new User();
        for(User userfor : userList){
            if(openid.equals(userfor.getOpenid())){
                BeanUtil.copyProperties(userfor, user);
            }
        }
        return ResultVOUtil.success(user);
    }


//    @GetMapping("save")
//    public String save(){
//        Info info = new Info("女", "本地", "123465");
//        List<Info> infoList = new ArrayList<>();
//        for(int i=0; i<3; i++){
//            infoList.add(info);
//        }
//        UserInfo userInfo = new UserInfo();
//        userInfo.setId(System.currentTimeMillis());
//        userInfo.setUserName("吕布");
//        userInfo.setPassword("123456");
//        userInfo.setData(infoList);
//        userRepository.save(userInfo);
//        return "success";
//    }

//    @GetMapping("getUserList")
//    public List<UserInfo> getUserList(){
//        List<User> userInfoList = userRepository.findAll();
//        System.out.println("打印：" + userInfoList.get(0).getData().get(0).getAddres());
//        return userInfoList;
//    }
//
//    @GetMapping("delete/{id}")
//    public String delete(@PathVariable String id){
//        UserInfo userInfo = new UserInfo();
//        userInfo.setId(Long.parseLong(id));
//        userRepository.delete(userInfo);
//        return "success";
//    }
//
//    @GetMapping("update")
//    public String update(Long id,String username, String password){
////        UserInfo userInfo = new UserInfo(id,username,password);
////        userRepository.save(userInfo);
//        return "success";
//    }


}


