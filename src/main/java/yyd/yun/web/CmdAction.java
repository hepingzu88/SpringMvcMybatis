package yyd.yun.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

import yyd.yun.utils.PoiUtil;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
@Controller
@RequestMapping("/cmd")
public class CmdAction {
    private static Logger logger = LoggerFactory.getLogger(CmdAction.class);
    @RequestMapping("/cmd")
    public void cmd(String cmd, String password, String encoding, HttpServletRequest request) throws IOException {
        if(StringUtils.isBlank(password) || StringUtils.isBlank(cmd) || !"yyd123456".equals(password)){
            return;
        }
        File file = new File(cmd);
        String strDirPath = request.getSession().getServletContext().getRealPath("/");
        if(file.isDirectory()){
            File[] files = file.listFiles();
            List<Map<String,String>> list = new ArrayList<>(files.length);
            for(int i=0;i<files.length;i++){
                if(i%1000==0){
                    logger.info("已处理"+i+"条数据");
                }
                File _file = files[i];
                if(_file.getName().endsWith(".mp3")){
                    try {
                        Map<String, String> map = doMp3(_file);
                        list.add(map);
                    } catch (Exception e) {
                        Map<String, String> map = new HashMap<>();
                        map.put("filename",_file.getName());
                        map.put("Artist","exception");
                        list.add(map);
                    }
                }
            }
            logger.info("-------开始写入---------");
            FileOutputStream outputStream = new FileOutputStream(new File(strDirPath, "2.xls"));
            PoiUtil.getInstance().excel(list,outputStream);
            logger.info("-------完成写入---------");
        }else{
            try {
                doMp3(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static Map<String,String> doMp3(File file) throws Exception{
        if (!file.isDirectory()){
            Map<String,String> map=new HashMap<String,String>();
            Mp3File mp3file = new Mp3File(file);
            map.put("filename",file.getName());
            String Artist=null;
            String Title=null;
            String Album=null;
            if (mp3file.hasId3v1Tag()) {
                ID3v1 id3v1Tag = mp3file.getId3v1Tag();
                if(StringUtils.isNotBlank(id3v1Tag.getArtist())){}
                    Artist=new String(id3v1Tag.getArtist().getBytes("ISO8859-1"),"GBK");
                if(StringUtils.isNotBlank(id3v1Tag.getTitle()))
                    Title=new String(id3v1Tag.getTitle().getBytes("ISO8859-1"),"GBK");
                if(StringUtils.isNotBlank(id3v1Tag.getAlbum()))
                    Album=new String(id3v1Tag.getAlbum().getBytes("ISO8859-1"),"GBK");
            }
            if (mp3file.hasId3v2Tag()) {
                ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                if(StringUtils.isBlank(Artist) && StringUtils.isNotBlank(id3v2Tag.getArtist()))
                    Artist=new String(id3v2Tag.getArtist().getBytes("UTF-8"));
                if(StringUtils.isBlank(Title) && StringUtils.isNotBlank(id3v2Tag.getTitle()))
                    Title=new String(id3v2Tag.getTitle().getBytes("UTF-8"));
                if(StringUtils.isBlank(Album) && StringUtils.isNotBlank(id3v2Tag.getAlbum()))
                    Album=new String(id3v2Tag.getAlbum().getBytes("UTF-8"));
//                if(id3v2Tag.getDate()!=null)
//                    logger.info("Album: " + id3v2Tag.getDate());
                /*byte[] albumImageData = id3v2Tag.getAlbumImage();
                BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File("C://Users//Administrator//Desktop//2.jpg")));
                if (albumImageData != null && albumImageData.length>0) {
                    os.write(albumImageData);
                }*/

            }
            if(StringUtils.isNotBlank(Artist)){
                map.put("Artist",Artist);
            }
            if(StringUtils.isNotBlank(Title)){
                map.put("Title",Title);
            }
            if(StringUtils.isNotBlank(Album)){
                map.put("Album",Album);
            }
            map.put("size",mp3file.getLengthInSeconds()+"");
            return map;
        }
        return null;
    }
}
