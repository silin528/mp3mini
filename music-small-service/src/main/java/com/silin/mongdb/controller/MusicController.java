package com.silin.mongdb.controller;

import com.silin.mongdb.VO.MusicVO;
import com.silin.mongdb.VO.ResultVO;
import com.silin.mongdb.entity.Music;
import com.silin.mongdb.entity.Singer;
import com.silin.mongdb.repository.MusicRepository;
import com.silin.mongdb.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Create by silin
 * Date:  2020/3/7 18:16
 */
@RestController
@Slf4j
@RequestMapping("/music/test")
public class MusicController {

    @Autowired
    private MusicRepository musicRepository;

    //
    @RequestMapping("/audio")
    public ResultVO music(@RequestParam("type")Integer type,
                          @RequestParam("p")Integer p) throws ParseException {

        List<Music> musicList = musicRepository.findAll();

        List<MusicVO> musicVOList = new ArrayList<MusicVO>();
        for(Music music : musicList){
            MusicVO musicVO = new MusicVO();

            BeanUtils.copyProperties(music, musicVO);
            System.out.println(music.getDate().getTime());
            System.out.println(new Timestamp(music.getDate().getTime()));

            musicVO.setId(music.getId().toString());
            musicVO.setCreated(music.getCreated().getTime()  / 1000L );
            musicVO.setDate(music.getDate().getTime()  / 1000L);

            musicVOList.add(musicVO);

            log.info("id:{}", music.getId());

        }
        Map<String, Object> mapPage = new HashMap<String, Object>();
        mapPage.put("p", p);
        mapPage.put("limit", 8);
        mapPage.put("total", 10);
        mapPage.put("total_page", 2);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("audios", musicVOList);
        map.put("page", mapPage);
        map.put("i_resource","http://localhost:8888/silin/");
        map.put("a_resource","http://localhost:8888/silin/");


        return ResultVOUtil.success(map);
    }

    @GetMapping("/audio/detail")
    public ResultVO musicInfo(@RequestParam("id")String id)throws Exception{

        log.info("id,{}",id);
        Music music = musicRepository.findById(Long.valueOf(id)).orElse(null);

        MusicVO musicVO = new MusicVO();
        BeanUtils.copyProperties(music, musicVO);
        musicVO.setCreated(music.getCreated().getTime()  / 1000L );
        musicVO.setDate(music.getDate().getTime()  / 1000L);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("audio", musicVO);
        map.put("i_resource","http://localhost:8888/silin/");
        map.put("a_resource","http://localhost:8888/silin/");
        return ResultVOUtil.success(map);
    }

    @RequestMapping("/music/save")
    public void saveMusic(){

        Music music = new Music();
        music.setId(105L);
        music.setSinger_id(13128032L);
        music.setCover("audio/cover/1555666089806425137");
        music.setName("去年夏天");
        music.setType(0);
        music.setUrl("audio/url/1555666088570890088");
        music.setCreated(new Date());

        Singer singer = new Singer();
        singer.setName("王大毛");
        singer.setAvatar("audio/cover/1555666089806425137");

        music.setSinger(singer);

        musicRepository.save(music);

//        1585055280130658189
//        1585055280130658300  111

//        1585055747431400997
//        1585054171023898400

    }

}
