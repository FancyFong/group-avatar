import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: fangdaji
 * @date: 2019/3/23 15:53
 * @description: 生成群头像
 */
public class GenerateGroupAvatar {


    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> urlList = setUrlList();
        GenerateGroupAvatarUtil generateGroupAvatarUtil = new GenerateGroupAvatarUtil();
        String base64Str = generateGroupAvatarUtil.getCombinationOfHead(urlList);
        System.out.println(base64Str);
    }


    /**
     * 组合图片集合
     *
     * @return
     */
    private static List<String> setUrlList() {
        String imgUrl1 = "https://b-ssl.duitang.com/uploads/item/201409/23/20140923094045_BNYji.thumb.1900_0.png";
        String imgUrl2 = "https://b-ssl.duitang.com/uploads/item/201603/02/20160302145713_THR2s.thumb.1000_0.jpeg";
        String imgUrl3 = "https://b-ssl.duitang.com/uploads/item/201603/02/20160302145702_VFJYx.thumb.1000_0.jpeg";
        String imgUrl4 = "https://b-ssl.duitang.com/uploads/item/201603/02/20160302145630_zWJkK.thumb.1000_0.jpeg";
        String imgUrl5 = "https://b-ssl.duitang.com/uploads/item/201603/02/20160302145606_5afV8.thumb.1000_0.jpeg";
        String imgUrl6 = "https://b-ssl.duitang.com/uploads/item/201407/16/20140716132526_TcyTY.thumb.1900_0.jpeg";
        String imgUrl7 = "https://b-ssl.duitang.com/uploads/item/201809/17/20180917211758_nrgny.thumb.1000_0.jpg";
        String imgUrl8 = "https://b-ssl.duitang.com/uploads/item/201809/17/20180917211759_yojox.thumb.1000_0.jpg";
        String imgUrl9 = "https://b-ssl.duitang.com/uploads/item/201812/20/20181220144131_VYRE8.thumb.1000_0.jpeg";

        List<String> urlList = new ArrayList<>();
        urlList.add(imgUrl1);
        urlList.add(imgUrl2);
        urlList.add(imgUrl3);
        urlList.add(imgUrl4);
        urlList.add(imgUrl5);
        urlList.add(imgUrl6);
        urlList.add(imgUrl7);
        urlList.add(imgUrl8);
        urlList.add(imgUrl9);

        return urlList;
    }

}
