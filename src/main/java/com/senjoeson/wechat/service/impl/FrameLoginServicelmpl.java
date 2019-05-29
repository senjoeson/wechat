package com.senjoeson.wechat.service.impl;

import com.senjoeson.wechat.utils.enums.URLEnum;
import com.senjoeson.wechat.utils.tools.CommonTools;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameLoginServicelmpl extends LoginServiceImpl {
    @Override
    public boolean getQR(String qrPath) {
        qrPath = qrPath + File.separator + "QR.jpg";
        String qrUrl = URLEnum.QRCODE_URL.getUrl() + core.getUuid();
        HttpEntity entity = myHttpClient.doGet(qrUrl, null, true, null);
        try {
            byte[] bytes = EntityUtils.toByteArray(entity);
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
            JFrame jf = new JFrame();
            JPanel jp = new JPanel() {
                public void paint(Graphics g) {
                    super.paint(g);
                    g.drawImage(image, 0, 0, null);
                }
            };
            jf.getContentPane().add(jp);
            jf.setSize(image.getWidth(), image.getHeight());
            jf.setVisible(true);
            try {
                CommonTools.printQr(qrPath); // 打开登陆二维码图片
            } catch (Exception e) {
                LOG.info(e.getMessage());
            }

        } catch (Exception e) {
            LOG.info(e.getMessage());
            return false;
        }

        return true;
    }


}