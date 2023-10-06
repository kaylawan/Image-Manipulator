package assignment;

/*
 *
 * CS314H Programming Assignment 1 - Java image processing
 *
 * Included is the Invert effect from the assignment.  Use this as an
 * example when writing the rest of your transformations.  For
 * convenience, you should place all of your transformations in this file.
 *
 * You can compile everything that is needed with
 * javac -d bin src/main/java/assignment/*.java
 *
 * You can run the program with
 * java -cp bin assignment.JIP
 *
 * Please note that the above commands assume that you are in the prog1
 * directory.
 */

import java.util.ArrayList;
import java.util.Arrays;

class Invert extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[y][x] = ~pixels[y][x];
            }
        }
        return pixels;
    }
}

class Dummy extends ImageEffect {

    public Dummy() {
        params = new ArrayList<>();
        params.add(new ImageEffectParam("ParamName",
                "Description of param.",
                10, 0, 1000));
    }

    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        // Use params here.
        ImageEffectParam param = params.get(0);
        int value = param.getValue();

        return pixels;
    }
}

class NoRed extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        if (pixels == null){
            return pixels;
        }
        //iterates through all pixels and sets red value to 0.
        int width = pixels[0].length;
        int height = pixels.length;
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                pixels[x][y] = makePixel(0, getGreen(pixels[x][y]), getBlue(pixels[x][y]));
            }
        }
        return pixels;
    }
}

class NoGreen extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        if (pixels == null){
            return pixels;
        }
        int width = pixels[0].length;
        int height = pixels.length;
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                pixels[x][y] = makePixel(getRed(pixels[x][y]), 0, getBlue(pixels[x][y]));
            }
        }
        return pixels;
    }
}

class NoBlue extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        if (pixels == null) {
            return pixels;
        }
        int width = pixels[0].length;
        int height = pixels.length;
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                pixels[x][y] = makePixel(getRed(pixels[x][y]), getGreen(pixels[x][y]), 0);
            }
        }
        return pixels;
    }
}

class RedOnly extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        if (pixels == null){
            return pixels;
        }
        //iterate through pixels and set the green and blue values to 0
        //the resulting image will only have the original red tones left.
        int width = pixels[0].length;
        int height = pixels.length;
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                pixels[x][y] = makePixel(getRed(pixels[x][y]), 0, 0);
            }
        }
        return pixels;
    }
}

class GreenOnly extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        if (pixels == null){
            return pixels;
        }
        int width = pixels[0].length;
        int height = pixels.length;
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                pixels[x][y] = makePixel(0, getGreen(pixels[x][y]), 0);
            }
        }
        return pixels;
    }
}

class BlueOnly extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        if (pixels == null){
            return pixels;
        }
        int width = pixels[0].length;
        int height = pixels.length;
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                pixels[x][y] = makePixel(getBlue(pixels[x][y]), 0, 0);
            }
        }
        return pixels;
    }
}

class BlackAndWhite extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        if (pixels == null){
            return pixels;
        }
        int width = pixels[0].length;
        int height = pixels.length;
        //take the average of the R,G, and B values of a pixel and sets it as each of the RGB components of a new pixel
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                int red = getRed(pixels[x][y]);
                int green = getGreen(pixels[x][y]);
                int blue = getBlue(pixels[x][y]);
                int average = (red+blue+green)/3;
                pixels[x][y] = makePixel(average, average, average);
            }
        }
        return pixels;
    }
}

class VerticalReflect extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        if (pixels == null){
            return pixels;
        }
        int length = pixels.length;
        int width = pixels[0].length;
        //iterates through all rows until the middle column index of the image
        for (int x=0;x<length; x++) {
            for (int y = 0; y < width/2; y++) {
                //sets original pixel location to temporary pixel, original pixel is set to reflected pixel
                //and pixel on the other side is replaced with original(temporary) pixel
                int temporary_pixel = pixels[x][y];
                pixels[x][y] = pixels[x][width-y-1];
                pixels[x][width-y-1] = temporary_pixel;
            }
        }
        return pixels;
    }
}

class HorizontalReflect extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        if (pixels == null){
            return pixels;
        }
        for(int x=0; x<pixels.length/2;x++){
            for(int y=0;y<pixels[0].length;y++){
                int temp = pixels[x][y];
                pixels[x][y] = pixels[pixels.length-x-1][y];
                pixels[pixels.length-x-1][y] = temp;
            }
        }
        return pixels;
    }
}

class Grow extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        if (pixels == null){
            return pixels;
        }
        //creates new array twice the size of original image
        int [][] newPixels = new int[pixels.length*2][pixels[0].length*2];
        //sets each pixel in pixels to a 2x2 area in the newPixels image
        for(int i=0;i<pixels.length;i++) {
            for(int j=0;j<pixels[0].length;j++) {
                int temp_pixel = pixels[i][j];
                newPixels[2*i][2*j]= temp_pixel;
                newPixels[2*i+1][2*j] = temp_pixel;
                newPixels[2*i][2*j+1] = temp_pixel;
                newPixels[2*i+1][2*j+1] = temp_pixel;
            }
        }
        return newPixels;
    }
}

class Shrink extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        if (pixels == null){
            return pixels;
        }
        int length = pixels.length;
        int width = pixels[0].length;
        //return original image if both sides cannot be shrunk by half, or reduce image by half on the side that is
        //not able to be shrunk
        if (length<=1 && width<=1){
            return pixels;
        } else if (length<=1){
            int [][] newPixels = new int[length][width/2];
            for (int x=0;x<width;x+=2){
                newPixels[0][x/2] = makePixel((getRed(pixels[0][x])+getRed(pixels[0][x+1]))/2, (getGreen(pixels[0][x])+getGreen(pixels[0][x+1]))/2, (getBlue(pixels[0][x])+getBlue(pixels[0][x+1]))/2);
            }
            return newPixels;
        } else if(width<=1) {
            int[][] newPixels = new int[length/2][width];
            for (int x = 0; x < length; x += 2) {
                newPixels[x/2][0] = makePixel((getRed(pixels[x][0]) + getRed(pixels[x + 1][0])) / 2, (getGreen(pixels[x][0]) + getGreen(pixels[x + 1][0])) / 2, (getBlue(pixels[x][0]) + getBlue(pixels[x + 1][0])) / 2);
            }
            return newPixels;
        }
        //creates newPixels, which is half the size of the original pixels array
        int [][] newPixels = new int[length/2][width/2];
        //takes average RGB of a 2x2 block of pixels and sets the average RGB to a singular new pixel in newPixels
        for (int x = 0; x < length-1; x+=2) {
            for (int y = 0; y < width-1; y+=2) {
                int avg_red = (getRed(pixels[x][y]) + getRed(pixels[x+1][y]) + getRed(pixels[x][y+1]) + getRed(pixels[x+1][y+1]))/4;
                int avg_green = (getGreen(pixels[x][y]) + getGreen(pixels[x+1][y]) + getGreen(pixels[x][y+1]) + getGreen(pixels[x+1][y+1]))/4;
                int avg_blue = (getBlue(pixels[x][y]) + getBlue(pixels[x+1][y]) + getBlue(pixels[x][y+1]) + getBlue(pixels[x+1][y+1]))/4;
                newPixels[x/2][y/2] = makePixel(avg_red, avg_green, avg_blue);
                    }
                }
        return newPixels;
            }
        }

class Threshold extends ImageEffect {
    public Threshold() {
        params = new ArrayList<>();
        params.add(new ImageEffectParam("Threshold",
                "Description of param.",
                10, 0, 255));
    }
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        if (pixels == null){
            return pixels;
        }
        //gets the value of param for Threshold
        ImageEffectParam param = params.get(0);
        int threshold_value = param.getValue();
        //check if each pixel's RGB values are greater or less than the threshold.
        //if greater, set color to 255. if less, set color to 0.
        for (int x = 0; x < pixels.length; x++) {
            for (int y = 0; y < pixels[0].length; y++) {
                int threshold_red = 0;
                int threshold_green = 0;
                int threshold_blue = 0;
                if(getRed(pixels[x][y])>threshold_value)
                {
                    threshold_red=255;
                }
                if(getGreen(pixels[x][y])>threshold_value)
                {
                    threshold_green=255;
                }
                if(getBlue(pixels[x][y])>threshold_value)
                {
                    threshold_blue = 255;
                }
                pixels[x][y]=makePixel(threshold_red,threshold_green,threshold_blue);
            }
        }
        return pixels;
    }
}


//KARMA
class Brighten extends ImageEffect{
    public Brighten() {
        params = new ArrayList<>();
        params.add(new ImageEffectParam("Brighten Parameter",
                "how much brighter would you like your image to be?:",
                10, 0, 255));
    }
    public static int blackOrWhite(int value){
        //sets color value to black if the color value is negative after threshold
        if (value<0) {
            value = 0;
        }
        //sets color value to white if color value is over 255 maximum after threshold
        else if (value>255){
            value = 255;
        }
        return value;
    }
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        if (pixels == null){
            return pixels;
        }
        ImageEffectParam param = params.get(0);
        int brightness_level = param.getValue();
        int width = pixels[0].length;
        int length = pixels.length;
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < width; y++) {
                int red = getRed(pixels[x][y]);
                int green = getGreen(pixels[x][y]);
                int blue = getBlue(pixels[x][y]);
                //calls previous function to set RGB values of a new pixel
                pixels[x][y] = makePixel(blackOrWhite(red+brightness_level), blackOrWhite(green+brightness_level), blackOrWhite(blue+brightness_level));
            }
        }
        return pixels;
    }
}

class DottedImage extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        if (pixels == null) {
            return pixels;
        }
        int width = pixels[0].length;
        int length = pixels.length;
        int[][] newPixels = new int[length][width];
        //3x3 neighborhood
        int neighborhood_value = 3;
        //sets all values to black except the middle pixel in a 3x3 neighborhood, which retains its RGB
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < width; y++) {
                //
                if ((x % neighborhood_value == 1) && (y % neighborhood_value == 1)) {
                    newPixels[x][y] = pixels[x][y];
                } else {
                    newPixels[x][y] = makePixel(0, 0, 0);
                }
            }
        }
        return newPixels;
    }
}

class Smooth extends ImageEffect {
    public Smooth() {
        params = new ArrayList<>();
        params.add(new ImageEffectParam("Size of Neighborhood",
                "How large would you like your neighborhood to be? (Recommended 4 :D)",
                4, 0, 10));
    }
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        if (pixels == null) {
            return pixels;
        }
        ImageEffectParam param = params.get(0);
        int neighborhood_size = param.getValue();
        int width = pixels[0].length;
        int length = pixels.length;
        int avg_red = 0;
        int avg_green = 0;
        int avg_blue = 0;
        for (int x = 0; x < length - (neighborhood_size - 1); x += neighborhood_size) {
            for (int y = 0; y < width - (neighborhood_size - 1); y += neighborhood_size) {
                //takes the average of each pixel in a 4x4
                for (int i = x; i < x + neighborhood_size; i++) {
                    for (int j = y; j < y + neighborhood_size; j++) {
                        avg_red += getRed(pixels[i][j]);
                        avg_green += getGreen(pixels[i][j]);
                        avg_blue += getBlue(pixels[i][j]);
                    }
                }
                int red = avg_red / (neighborhood_size*neighborhood_size);
                int green = avg_green / (neighborhood_size*neighborhood_size);
                int blue = avg_blue / (neighborhood_size*neighborhood_size);
                //sets the average as the pixel for the 4x4
                for (int a = x; a < x + neighborhood_size; a++) {
                    for (int b = y; b < y + neighborhood_size; b++) {
                        pixels[a][b] = makePixel(red, green, blue);
                    }
                }
                avg_red = 0;
                avg_green = 0;
                avg_blue = 0;
            }
        }
        return pixels;
    }

}

class DeNoise extends ImageEffect {
    public DeNoise() {
        params = new ArrayList<>();
        params.add(new ImageEffectParam("Size of Neighborhood",
                "How large would you like your neighborhood to be? (Recommended 4 :D)",
                4, 0, 10));
    }

    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        if (pixels == null) {
            return pixels;
        }
        ImageEffectParam param = params.get(0);
        int neighborhood_size = param.getValue();
        int width = pixels[0].length;
        int length = pixels.length;
        int [] median_red = new int [neighborhood_size*neighborhood_size];
        int [] median_green = new int [neighborhood_size*neighborhood_size];
        int [] median_blue = new int [neighborhood_size*neighborhood_size];
        //array_position variable is made to track where each new median RGB value is being added in the array
        int array_position = 0;
        for (int x = 0; x < length - (neighborhood_size - 1); x += neighborhood_size) {
            for (int y = 0; y < width - (neighborhood_size - 1); y += neighborhood_size) {
                for (int i = x; i < x + neighborhood_size; i++) {
                    for (int j = y; j < y + neighborhood_size; j++) {
                        median_red[array_position] = getRed(pixels[i][j]);
                        median_green[array_position] = getGreen(pixels[i][j]);
                        median_blue[array_position] = getBlue(pixels[i][j]);
                        array_position+=1;
                    }
                }
                array_position=0;
                Arrays.sort(median_red);
                Arrays.sort(median_green);
                Arrays.sort(median_blue);
                //calls median RGB value from each array and stores in variable
                int red_value = median_red[median_red.length/2];
                int green_value = median_green[median_green.length/2];
                int blue_value = median_blue[median_blue.length/2];
                for (int a = x; a < x + neighborhood_size; a++) {
                    for (int b = y; b < y + neighborhood_size; b++) {
                        pixels[a][b] = makePixel(red_value, green_value, blue_value);
                    }
                }
            }
        }
        return pixels;
    }
}

class Erode extends ImageEffect {
    public Erode() {
        params = new ArrayList<>();
        params.add(new ImageEffectParam("Size of Neighborhood",
                "How large would you like your neighborhood to be? (Recommended 4 :D)",
                4, 0, 10));
    }
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        if (pixels == null) {
            return pixels;
        }
        ImageEffectParam param = params.get(0);
        int neighborhood_size = param.getValue();
        int width = pixels[0].length;
        int length = pixels.length;
        for (int x = 0; x < length - (neighborhood_size - 1); x += neighborhood_size) {
            for (int y = 0; y < width - (neighborhood_size - 1); y += neighborhood_size) {
                //stores minimum RGB value in variables to be used later to create new pixel
                int min_redErode = getRed(pixels[x][y]);
                int min_greenErode = getGreen(pixels[x][y]);
                int min_blueErode = getBlue(pixels[x][y]);
                for (int i = x; i < x + neighborhood_size; i++) {
                    for (int j = y; j < y + neighborhood_size; j++) {
                        if(getRed(pixels[i][j])<min_redErode){
                            min_redErode = getRed(pixels[i][j]);
                        }
                        if(getGreen(pixels[i][j])<min_greenErode){
                            min_greenErode = getGreen(pixels[i][j]);
                        }
                        if(getBlue(pixels[i][j])< min_blueErode){
                            min_blueErode = getBlue(pixels[i][j]);
                        }
                    }
                }
                for (int a = x; a < x + neighborhood_size; a++) {
                    for (int b = y; b < y + neighborhood_size; b++) {
                        pixels[a][b] = makePixel(min_redErode, min_greenErode, min_blueErode);
                    }
                }
            }
        }
        return pixels;

    }
}


