package com.silin.mongdb.controller;


import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.silin.mongdb.entity.Category;
import com.silin.mongdb.entity.Music;
import com.silin.mongdb.entity.Singer;
import com.silin.mongdb.repository.CategoryRepository;
import com.silin.mongdb.repository.MusicRepository;
import com.silin.mongdb.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by silin
 * Date:  2020/3/6 20:56
 */

@Controller
@RequestMapping("/admin/music")
@Slf4j
public class AdminMusicController {

    @Value("${file.savepath}")
    public String path;

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * 列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<Music> musicPage = musicRepository.findAll(request);
        map.put("musicPage", musicPage);
        map.put("currentPage", page);
        map.put("size", size);
        log.info("访问list");
        log.info("map:{}", map.get("musicPage"));
        return new ModelAndView("music/list", map);
    }

    //音乐文件上传
    @PostMapping(value = "/save" )
    public ModelAndView fileMusicUpload(
                                  @RequestParam("id")Long id,
                                  @RequestParam("musicfile")MultipartFile musicfile,
                                  @RequestParam("coverfile")MultipartFile coverfile,
                                  @RequestParam("avatarfile")MultipartFile avatarfile,
                                  @RequestParam("name")String name,
                                  @RequestParam("lrc")String lrc,
                                  @RequestParam("type")Integer type,
//                                  @RequestParam("url")String url,
                                  @RequestParam("singerName")String singerName
//                                  @RequestParam("singerAvatar")String singerAvatar
    ) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        Music music = new Music();

        String musicfileName = musicfile.getOriginalFilename();
        String coverfileName = musicfile.getOriginalFilename();
        String avatarfileName = musicfile.getOriginalFilename();

        if (!StringUtils.isEmpty(id)) {
            music = musicRepository.findById(id).orElse(null);
        }else{
            music.setId(Long.valueOf(KeyUtil.genUniqueKey()));
            music.setSinger_id(Long.valueOf(KeyUtil.genUniqueKey()));
            music.setName(name);
            music.setCover("audio/cover/" + coverfileName);
            music.setType(type);
            music.setUrl("audio/url/" + musicfileName);
            music.setCreated(new Date());
            music.setDate(new Date());
            music.setLrc(lrc);
            Singer singer = new Singer();
            singer.setName(singerName);
            singer.setAvatar("audio/avatar/" + avatarfileName);
            music.setSinger(singer);
        }

        musicRepository.save(music);

        if (musicfile.isEmpty()) {
            map.put("msg", "文件为空");
            map.put("url", "/silin/admin/music/index");
            return new ModelAndView("common/error", map);
        }


        File musicdest = new File(path + "/url/" + musicfileName);
        File coverdest = new File(path + "/cover/" + coverfileName);
        File avaetardest = new File(path + "/avatar/" + avatarfileName);
        File coverdest1 = new File(path + "/cover/" + coverfileName + "-cover");
        File avaetardest1 = new File(path + "/avatar/" + avatarfileName + "-avatar");

//        File avaterdest = new File(path + "/cover/" + avaterfileName);

        if (!musicdest.getParentFile().exists()) {
            musicdest.getParentFile().mkdir();
        }
        if (!coverdest.getParentFile().exists()) {
            coverdest.getParentFile().mkdir();
        }
        if (!avaetardest.exists()) {
            avaetardest.getParentFile().mkdir();
        }
        if (!coverdest1.getParentFile().exists()) {
            coverdest1.createNewFile();
        }
        if (!avaetardest1.exists()) {
            avaetardest1.createNewFile();
        }
        try {
            musicfile.transferTo(musicdest); //保存文件
            coverfile.transferTo(coverdest); //保存文件
            avatarfile.transferTo(avaetardest); //保存文件
            BufferedInputStream in = FileUtil.getInputStream(coverdest);
            BufferedOutputStream out = FileUtil.getOutputStream(coverdest1);
            IoUtil.copy(in, out, IoUtil.DEFAULT_BUFFER_SIZE);

            BufferedInputStream in1 = FileUtil.getInputStream(avaetardest);
            BufferedOutputStream out1 = FileUtil.getOutputStream(avaetardest1);
            IoUtil.copy(in1, out1

                    , IoUtil.DEFAULT_BUFFER_SIZE);

        } catch (Exception e) {
            e.printStackTrace();
        }


        map.put("url", "/silin/admin/music/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "id", required = false) Long id,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(id)) {
            Music music = musicRepository.findById(id).orElse(null);
            map.put("music", music);
        }

//        查询所有的类目
        List<Category> categoryList = categoryRepository.findAll();
        map.put("categoryList", categoryList);

        return new ModelAndView("music/index", map);
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam(value = "id", required = false) String id,
                               Map<String, Object> map) {
        if (id != null) {
            musicRepository.deleteById(Long.valueOf(id));
        }

        int page = 1;
        int size = 10;
        PageRequest request = PageRequest.of(page - 1, size);
        Page<Music> musicPage = musicRepository.findAll(request);
        map.put("musicPage", musicPage);
        map.put("currentPage", page);
        map.put("size", size);
        log.info("访问list");
        log.info("map:{}", map.get("musicPage"));
        return new ModelAndView("music/list", map);
    }



}
