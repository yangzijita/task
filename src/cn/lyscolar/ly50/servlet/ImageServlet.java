package cn.lyscolar.ly50.servlet;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/getcode")
public class ImageServlet extends HttpServlet {
    private static final long serialVersionUID=1L;
    public ImageServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //1、定义BufferedImage对象
        BufferedImage image=new BufferedImage(80,30,BufferedImage.TYPE_INT_RGB);
        //获得Graphics对象
        Graphics g=image.getGraphics();
        //Color对象
        Color c=new Color(130,200,165);
        g.setColor(c);
        g.fillRect(0,0,80,30);

        //字符数组
        char [] ch="ABCDEFGHRGKLMNOPKRSTUVWXYZ0123456789".toCharArray();
        //通过Random产生随机验证码信息
        Random r =new Random();
        int len=ch.length;
        int index;
        //变长字符串对象StringBuffer();
        StringBuffer buffer=new StringBuffer();
        //使用Graphics绘制图片
        for(int i=0;i<4;i++){
            //获得随机索引
            index=r.nextInt(len);
            //73  165 119
            g.setColor(new Color(r.nextInt(73),r.nextInt(165),r.nextInt(119)));
            g.drawString(ch[index]+"",(i*20)+3,20);
            //变长字符串对象追加
            buffer.append(ch[index]);


        }
        //5、记录验证码信息到session中
        request.getSession().setAttribute("code",buffer.toString());
        System.out.println("验证码："+buffer.toString());
        //使用ImageIO输出图片
//        ImageIO.write(image,"JPG",resp.getOutputStream());
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(response.getOutputStream());
        encoder.encode(image);
    }
}
