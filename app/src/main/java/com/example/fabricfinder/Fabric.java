package com.example.fabricfinder;

import android.media.Image;

import java.util.List;

/**
 * @author Minka Firth
 */
public class Fabric {

    private final Image fabricPicture;
    private final int width;
    private final int height;
    private final int colour;
    private final List<String> tags;

    public Fabric(Image fabricPicture, int width, int height, int colour, List<String> tags) {
        this.fabricPicture = fabricPicture;
        this.width = width;
        this.height = height;
        this.colour = colour;
        this.tags = tags;
    }

    public Image getFabricPicture() {
        return fabricPicture;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getColour() {
        return colour;
    }

    public List<String> getTags() {
        return tags;
    }
}
