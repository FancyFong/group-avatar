import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: fangdaji
 * @date: 2019/3/23 15:59
 * @description:
 */
public class GenerateGroupAvatarUtil {

    /**
     * 图片格式：JPG
     */
    public static final String PICTRUE_FORMATE_JPG = "JPG";

    /**
     * 生成组合头像 (返回Base64字符串)
     *
     * @param urls 用户图像
     * @throws IOException
     */
    public String getCombinationOfHead(List<String> urls)
            throws IOException, URISyntaxException {
        List<URL> paths = new ArrayList<>();
        int size = 9;
        if (urls.size() < size) {
            size = urls.size();
        }
        for (int i = 0; i < size; i++) {
            paths.add(new URL(urls.get(i)));
        }

        List<BufferedImage> bufferedImages = new ArrayList<BufferedImage>();
        // 压缩图片所有的图片生成尺寸同意的 为 50x50

        int imageSize = 33;
        if (paths.size() <= 4) {
            imageSize = 50;
        }

        for (int i = 0; i < paths.size(); i++) {
            bufferedImages.add(resize2(paths.get(i), imageSize, imageSize, true));
        }

        int width = 112; // 这是画板的宽高

        int height = 112; // 这是画板的高度

        // BufferedImage.TYPE_INT_RGB可以自己定义可查看API

        BufferedImage outImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        // 生成画布
        Graphics g = outImage.getGraphics();

        Graphics2D g2d = (Graphics2D) g;

        // 设置背景色
        g2d.setBackground(new Color(231, 231, 231));
        //g2d.setBackground(new Color(231, 0, 4));

        // 通过使用当前绘图表面的背景色进行填充来清除指定的矩形。
        g2d.clearRect(0, 0, width, height);

        // 开始拼凑 根据图片的数量判断该生成那种样式的组合头像目前为4中
        int j = 1;
        int k = 1;
        for (int i = 1; i <= bufferedImages.size(); i++) {
            if (bufferedImages.size() == 9) {
                if (i <= 3) {
                    g2d.drawImage(bufferedImages.get(i - 1), 33 * i + 3 * i - 33, 4, null);
                } else if (i <= 6) {
                    g2d.drawImage(bufferedImages.get(i - 1), 33 * j + 3 * j - 33, 41, null);
                    j++;
                } else {
                    g2d.drawImage(bufferedImages.get(i - 1), 33 * k + 3 * k - 33, 77, null);
                    k++;
                }
            } else if (bufferedImages.size() == 8) {
                if (i <= 2) {
                    g2d.drawImage(bufferedImages.get(i - 1), 33 * i + 4 * i - 18, 4, null);
                } else if (i <= 5) {
                    g2d.drawImage(bufferedImages.get(i - 1), 33 * j + 3 * j - 33, 41, null);
                    j++;
                } else {
                    g2d.drawImage(bufferedImages.get(i - 1), 33 * k + 3 * k - 33, 77, null);
                    k++;
                }
            } else if (bufferedImages.size() == 7) {
                if (i <= 1) {
                    g2d.drawImage(bufferedImages.get(i - 1), 39, 4, null);
                } else if (i <= 4) {
                    g2d.drawImage(bufferedImages.get(i - 1), 33 * j + 3 * j - 33, 41, null);
                    j++;
                } else {
                    g2d.drawImage(bufferedImages.get(i - 1), 33 * k + 3 * k - 33, 77, null);
                    k++;
                }
            } else if (bufferedImages.size() == 6) {
                if (i <= 3) {
                    g2d.drawImage(bufferedImages.get(i - 1), 33 * i + 3 * i - 33, 15, null);
                } else {
                    g2d.drawImage(bufferedImages.get(i - 1), 33 * j + 3 * j - 33, 58, null);
                    j++;
                }
            } else if (bufferedImages.size() == 5) {
                if (i <= 2) {
                    g2d.drawImage(bufferedImages.get(i - 1), 33 * i + 4 * i - 18, 15, null);
                } else {
                    g2d.drawImage(bufferedImages.get(i - 1), 33 * j + 3 * j - 33, 58, null);
                    j++;
                }
            } else if (bufferedImages.size() == 4) {
                if (i <= 2) {
                    g2d.drawImage(bufferedImages.get(i - 1), 50 * i + 4 * i - 50, 4, null);
                } else {
                    g2d.drawImage(bufferedImages.get(i - 1), 50 * j + 4 * j - 50, 58, null);
                    j++;
                }
            } else if (bufferedImages.size() == 3) {
                if (i <= 1) {
                    g2d.drawImage(bufferedImages.get(i - 1), 31, 4, null);
                } else {
                    g2d.drawImage(bufferedImages.get(i - 1), 50 * j + 4 * j - 50, 58, null);
                    j++;
                }

            } else if (bufferedImages.size() == 2) {

                g2d.drawImage(bufferedImages.get(i - 1), 50 * i + 4 * i - 50,
                        31, null);

            } else if (bufferedImages.size() == 1) {

                g2d.drawImage(bufferedImages.get(i - 1), 31, 31, null);

            }

            // 需要改变颜色的话在这里绘上颜色。可能会用到AlphaComposite类
        }


        String format = PICTRUE_FORMATE_JPG;

        // TODO 也可以保存到本地路径
        // String outPath = "E:\\b.jpg";
        // ImageIO.write(outImage, format, new File(outPath));

        // 生成Base64字符串
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(outImage, format, bos);
            byte[] imageBytes = bos.toByteArray();
            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "data:image/jpeg;base64," + imageString;
    }

    /**
     * 图片缩放
     *
     * @param filePath 图片路径
     * @param height   高度
     * @param width    宽度
     * @param bb       比例不对时是否需要补白
     */
    private BufferedImage resize2(URL filePath, int height, int width,
                                  boolean bb) throws URISyntaxException {
        try {
            double ratio = 0; // 缩放比例

            DataInputStream dis = new DataInputStream(filePath.openStream());

            //File f = new File(dis);
            BufferedImage bi = ImageIO.read(dis);
            Image itemp = bi.getScaledInstance(width, height,
                    Image.SCALE_SMOOTH);
            // 计算比例
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
                if (bi.getHeight() > bi.getWidth()) {
                    ratio = (new Integer(height)).doubleValue()
                            / bi.getHeight();
                } else {
                    ratio = (new Integer(width)).doubleValue() / bi.getWidth();
                }
                AffineTransformOp op = new AffineTransformOp(
                        AffineTransform.getScaleInstance(ratio, ratio), null);
                itemp = op.filter(bi, null);
            }
            if (bb) {
                // copyimg(filePath, "D:\\img");
                BufferedImage image = new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, width, height);
                if (width == itemp.getWidth(null)) {
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                } else {
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                }
                g.dispose();
                itemp = image;
            }
            return (BufferedImage) itemp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
