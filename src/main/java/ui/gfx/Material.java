package ui.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import ui.utils.FileUtils;

public class Material {
    private int texID;
    private String path;
    private int width;
    private int height;

    public Material(String path) {
        this.path = path;
    }

    // setters and getters

    public int getTextureID() {return texID;}

    // other methods

    /**
     * Load data from the image at the provided path.
     */
    public void create() {
        BufferedImage bi = FileUtils.loadImage(path);
        width = bi.getWidth();
        height = bi.getHeight();
        int[] pixels = bi.getRGB(0, 0, width, height, null, 0, width);

        // TODO: understand what the f*ck this does because I basically just copy pasted it
        // cf http://y2u.be/0cN3hJ6LphM at 17:31

        ByteBuffer b = BufferUtils.createByteBuffer((width * height) * 3);
        texID = GL11.glGenTextures();

        for (int i = 0; i < pixels.length; i++) {
            byte rr = (byte) ((pixels[i] >> 16) & 0xFF);
            byte gg = (byte) ((pixels[i] >> 8) & 0xFF);
            byte bb = (byte) ((pixels[i]) & 0xFF);

            b.put(rr);
            b.put(gg);
            b.put(bb);
        }
        b.flip();

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texID); // bind

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, width, height, 0, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, b);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0); // unbind
    }

    public void destroy() {
        GL11.glDeleteTextures(texID);
    }

}
