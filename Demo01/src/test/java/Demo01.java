import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName: Demo01
 * @Description:
 * @author: zwx
 * @Date: 2020/1/6 22:28
 * @version: V1.0
 */
public class Demo01 {

    /**
     * source为源地址，destination为合并之后的文件地址，videoName为合并后视频的名字，num为视频数量
     * @param source
     * @param destination
     * @param num
     * @throws IOException
     */
    public static void MergeVideos(File source, File destination, String videoName, int num) throws IOException {
        FileOutputStream out = new FileOutputStream(destination + "\\" + videoName);
        FileInputStream in = null;
        for(int i = 1 ; i <= num; i++){
            String videoPath = source + "\\" + i;
            File file = new File(videoPath);
            in = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int len = 0;
            while((len = in.read(bytes)) > 0){
                out.write(bytes,0,len);
            }
        }
        in.close();
        out.close();
    }

    public static void main(String[] args) throws IOException{
        MergeVideos(new File("E:\\videocache\\.三个代表重要思想.m3u8.d"),
                new File("E:\\video"), "video", 209);
    }


}
