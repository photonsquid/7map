package com.sevenmap.ui.gfx;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.sevenmap.ui.utils.FileUtils;

public class Material {
    private static final int BYTES_PER_PIXEL = 4;
    private int texID;
    private String path;
    private int width;
    private int height;

    /**
     * Create a new Material and load its texture from an image.
     * @param path path to the image
     */
    public Material(String path) {
        this.path = path;
    }

    // setters and getters
    
    public int getTexID() {
        return texID;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // other methods

    /**
     * Load data from the image at the provided path.
     * @apiNote this method now fully supports png format and alpha channel transparency
     */
    public void create() {
        BufferedImage bi = FileUtils.loadImage(path);
        width = bi.getWidth();
        height = bi.getHeight();
        int[] pixels = new int[width * height];
        bi.getRGB(0, 0, width, height, pixels, 0, width);

        ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * BYTES_PER_PIXEL);

        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = pixels[y * width + x];
                buffer.put((byte) ((pixel >> 16) & 0xFF)); // Red
                buffer.put((byte) ((pixel >> 8) & 0xFF));  // Green
                buffer.put((byte) (pixel & 0xFF));         // Blue
                buffer.put((byte) ((pixel >> 24) & 0xFF)); // Alpha
            }
        }

        buffer.flip();

        // We need to create a new texture ID and bind the buffer to it

        texID = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texID);

        // wrap mode
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
        
        // scaling and filtering
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
    }

    /**
     * Destroy the material and free its child elements.
     */
    public void destroy() {
        GL11.glDeleteTextures(texID);
    }

}
