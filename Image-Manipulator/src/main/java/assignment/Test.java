package assignment;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static java.lang.Math.random;
import static assignment.ImageEffect.*;

public class Test {
    //check to see if each pixel has any red left after apply NoRed. If any pixel does, it will tell me there is red.
    //make it a function so that it can be called for multiple test images.
    static void red_Checker(int [][] pixels){
        for (int i=0;i<pixels.length;i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                if (getRed(pixels[i][j]) != 0) {
                    System.out.println("there is red");
                }
            }
        }
    }
    static void green_Checker(int [][] pixels){
        for (int i=0;i<pixels.length;i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                if (getGreen(pixels[i][j]) != 0) {
                    System.out.println("there is green");
                }
            }
        }
    }
    static void blue_Checker(int [][] pixels){
        for (int i=0;i<pixels.length;i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                if (getBlue(pixels[i][j]) != 0) {
                    System.out.println("there is blue");
                }
            }
        }
    }
    static void redOnly_Checker(int [][] pixels){
        for (int i=0;i<pixels.length;i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                if (getGreen(pixels[i][j])!=0 && getBlue(pixels[i][j])!=0) {
                    System.out.println("there are colors other than red");
                }
            }
        }
    }
    static void greenOnly_Checker(int [][] pixels){
        for (int i=0;i<pixels.length;i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                if (getRed(pixels[i][j])!=0 && getBlue(pixels[i][j])!=0) {
                    System.out.println("there are colors other than green");
                }
            }
        }
    }
    static void blueOnly_Checker(int [][] pixels){
        for (int i=0;i<pixels.length;i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                if (getRed(pixels[i][j])!=0 && getGreen(pixels[i][j])!=0) {
                    System.out.println("there are colors other than blue");
                }
            }
        }
    }

    static void blackAndWhite_Checker(int [][] pixels){
        for (int x = 0; x < pixels[0].length; x++) {
            for (int y = 0; y < pixels.length; y++) {
                int red = getRed(pixels[y][x]);
                int green = getGreen(pixels[y][x]);
                int blue = getBlue(pixels[y][x]);
                //checks to see if each color value is the same, which should be the average of the three RGBS
                if((red!=green)||(green!=blue)||(red!=blue)){
                    System.out.println("this is not a black and white image");
                }
            }
        }
    }

    public static void main(String[] args) {
        /*Mapped getFilters() in order in map to access all filters so I can call each method when testing.
        I used map so I only have to do it once and can access the effects in getFilters() for the rest of my testing.*/
        Map<String, ImageEffect> map = getFilters();

        ImageEffect max_red = map.get("NoRed");
        //created a new image, pixels, which tests "NoRed"
        int [][] pixels_maxred = new int[10][10];
        for (int i=0;i<pixels_maxred.length;i++){
            for(int j=0;j<pixels_maxred[0].length;j++) {
                pixels_maxred[i][j] = makePixel(255, 0, 0);
            }
        }
        //applied "NoRed" effect to pixels matrix using e.apply(). Since NoRed does not have any params, the second param in apply is null.
        max_red.apply(pixels_maxred, null);
        red_Checker(pixels_maxred);

        //checks for red in an image with already no red.
        ImageEffect no_red = map.get("NoRed");
        int [][] pixels_nored = new int[10][10];
        for (int i=0;i<pixels_nored.length;i++) {
            for (int j = 0; j < pixels_nored[0].length; j++) {
            pixels_nored[i][j] = makePixel(0,255,255);
            }
        }
        no_red.apply(pixels_nored, null);
        red_Checker(pixels_nored);

        //checks for red in an image with random amounts of red, green, and blue
        ImageEffect randomRed = map.get("NoRed");
        int [][] pixels_randomred = new int[10][10];
        for (int i=0;i<pixels_randomred.length;i++) {
            for (int j = 0; j < pixels_randomred[0].length; j++) {
                int random_red = (int)(Math.random()*255);
                int random_green = (int)(Math.random()*255);
                int random_blue = (int)(Math.random()*255);
                pixels_randomred[i][j] = makePixel(random_red,random_green,random_blue);
            }
        }
        randomRed.apply(pixels_randomred, null);
        red_Checker(pixels_randomred);
        System.out.println("Done Testing NoRed");

        //Tests for NoGreen
        ImageEffect noGreen = map.get("NoGreen");
        int [][] pixels_noGreen = new int[10][10];
        for (int i=0;i<pixels_noGreen.length;i++) {
            for (int j = 0; j < pixels_noGreen[0].length; j++) {
                pixels_noGreen[i][j] = makePixel(255,0,255);
            }
        }
        noGreen.apply(pixels_noGreen, null);
        green_Checker(pixels_noGreen);

        ImageEffect maxGreen = map.get("NoGreen");
        int [][] pixels_maxGreen = new int[10][10];
        for (int i=0;i<pixels_maxGreen.length;i++) {
            for (int j = 0; j < pixels_maxGreen[0].length; j++) {
                pixels_maxGreen[i][j] = makePixel(0,255,0);
            }
        }
        maxGreen.apply(pixels_maxGreen, null);
        green_Checker(pixels_maxGreen);

        ImageEffect randomGreen = map.get("NoGreen");
        int [][] pixels_randomGreen = new int[10][10];
        for (int i=0;i<pixels_randomGreen.length;i++) {
            for (int j = 0; j < pixels_randomGreen[0].length; j++) {
                int random_redNoGreen = (int)(Math.random()*255);
                int random_greenNoGreen = (int)(Math.random()*255);
                int random_blueNoGreen = (int)(Math.random()*255);
                pixels_randomred[i][j] = makePixel(random_redNoGreen,random_greenNoGreen,random_blueNoGreen);
            }
        }
        randomGreen.apply(pixels_randomGreen, null);
        green_Checker(pixels_randomGreen);
        System.out.println("Done Testing NoGreen");

        //tests for NoBlue
        ImageEffect noBlue = map.get("NoBlue");
        int [][] pixels_noBlue = new int[10][10];
        for (int i=0;i<pixels_noBlue.length;i++) {
            for (int j = 0; j < pixels_noBlue[0].length; j++) {
                pixels_noBlue[i][j] = makePixel(255,255,0);
            }
        }
        noBlue.apply(pixels_noBlue, null);
        blue_Checker(pixels_noBlue);

        ImageEffect maxBlue = map.get("NoBlue");
        int [][] pixels_maxBlue = new int[10][10];
        for (int i=0;i<pixels_maxBlue.length;i++) {
            for (int j = 0; j < pixels_maxBlue[0].length; j++) {
                pixels_maxBlue[i][j] = makePixel(0,0,255);
            }
        }
        maxBlue.apply(pixels_maxBlue, null);
        blue_Checker(pixels_maxBlue);

        ImageEffect randomBlue = map.get("NoBlue");
        int [][] pixels_randomBlue = new int[10][10];
        for (int i=0;i<pixels_randomBlue.length;i++) {
            for (int j = 0; j < pixels_randomBlue[0].length; j++) {
                int random_redNoBlue = (int)(Math.random()*255);
                int random_greenNoBlue = (int)(Math.random()*255);
                int random_blueNoBlue = (int)(Math.random()*255);
                pixels_randomred[i][j] = makePixel(random_redNoBlue,random_greenNoBlue,random_blueNoBlue);
            }
        }
        randomBlue.apply(pixels_randomBlue, null);
        blue_Checker(pixels_randomBlue);
        System.out.println("Done Testing NoBlue");

        //Tests for redOnly
        ImageEffect redOnly = map.get("RedOnly");
        int [][] pixels_redOnly = new int[10][10];
        for (int i=0;i<pixels_redOnly.length;i++) {
            for (int j = 0; j < pixels_redOnly[0].length; j++) {
                pixels_redOnly[i][j] = makePixel(255,255,255);
            }
        }
        redOnly.apply(pixels_redOnly, null);
        redOnly_Checker(pixels_redOnly);

        ImageEffect redOnly_nored = map.get("RedOnly");
        int [][] pixels_redOnlynored = new int[10][10];
        for (int i=0;i<pixels_redOnlynored.length;i++) {
            for (int j = 0; j < pixels_redOnlynored[0].length; j++) {
                pixels_redOnlynored[i][j] = makePixel(0,255,255);
            }
        }
        redOnly_nored.apply(pixels_redOnlynored, null);
        redOnly_Checker(pixels_redOnlynored);

        ImageEffect randomRedOnly = map.get("RedOnly");
        int [][] pixels_randomRedOnly = new int[10][10];
        for (int i=0;i<pixels_randomRedOnly.length;i++) {
            for (int j = 0; j < pixels_randomRedOnly[0].length; j++) {
                int random_redRedOnly = (int)(Math.random()*255);
                int random_greenRedOnly = (int)(Math.random()*255);
                int random_blueRedOnly = (int)(Math.random()*255);
                pixels_randomred[i][j] = makePixel(random_redRedOnly,random_greenRedOnly,random_blueRedOnly);
            }
        }
        randomRedOnly.apply(pixels_randomRedOnly, null);
        redOnly_Checker(pixels_randomRedOnly);
        System.out.println("Done testing RedOnly");

        //Tests for greenOnly
        ImageEffect greenOnly = map.get("GreenOnly");
        int [][] pixels_greenOnly = new int[10][10];
        for (int i=0;i<pixels_greenOnly.length;i++) {
            for (int j = 0; j < pixels_greenOnly[0].length; j++) {
                pixels_greenOnly[i][j] = makePixel(255,255,255);
            }
        }
        greenOnly.apply(pixels_greenOnly, null);
        greenOnly_Checker(pixels_greenOnly);

        ImageEffect greenOnly_nogreen = map.get("GreenOnly");
        int [][] pixels_greenOnlynogreen = new int[10][10];
        for (int i=0;i<pixels_greenOnlynogreen.length;i++) {
            for (int j = 0; j < pixels_greenOnlynogreen[0].length; j++) {
                pixels_greenOnlynogreen[i][j] = makePixel(255,0,255);
            }
        }
        greenOnly_nogreen.apply(pixels_greenOnlynogreen, null);
        greenOnly_Checker(pixels_greenOnlynogreen);

        ImageEffect randomGreenOnly = map.get("GreenOnly");
        int [][] pixels_randomGreenOnly = new int[10][10];
        for (int i=0;i<pixels_randomGreenOnly.length;i++) {
            for (int j = 0; j < pixels_randomGreenOnly[0].length; j++) {
                int random_redGreenOnly = (int)(Math.random()*255);
                int random_greenGreenOnly = (int)(Math.random()*255);
                int random_blueGreenOnly = (int)(Math.random()*255);
                pixels_randomred[i][j] = makePixel(random_redGreenOnly,random_greenGreenOnly,random_blueGreenOnly);
            }
        }
        randomGreenOnly.apply(pixels_randomGreenOnly, null);
        greenOnly_Checker(pixels_randomGreenOnly);
        System.out.println("Done testing GreenOnly");

        //Tests for blueOnly
        ImageEffect blueOnly = map.get("BlueOnly");
        int [][] pixels_blueOnly = new int[10][10];
        for (int i=0;i<pixels_blueOnly.length;i++) {
            for (int j = 0; j < pixels_blueOnly[0].length; j++) {
                pixels_blueOnly[i][j] = makePixel(255,255,255);
            }
        }
        blueOnly.apply(pixels_blueOnly, null);
        blueOnly_Checker(pixels_blueOnly);

        ImageEffect blueOnly_noblue = map.get("BlueOnly");
        int [][] pixels_blueOnlynoblue = new int[10][10];
        for (int i=0;i<pixels_blueOnlynoblue.length;i++) {
            for (int j = 0; j < pixels_blueOnlynoblue[0].length; j++) {
                pixels_blueOnlynoblue[i][j] = makePixel(255,255,0);
            }
        }
        blueOnly_noblue.apply(pixels_blueOnlynoblue, null);
        blueOnly_Checker(pixels_blueOnlynoblue);


        ImageEffect randomBlueOnly = map.get("BlueOnly");
        int [][] pixels_randomBlueOnly = new int[10][10];
        for (int i=0;i<pixels_randomBlueOnly.length;i++) {
            for (int j = 0; j < pixels_randomBlueOnly[0].length; j++) {
                int random_redBlueOnly = (int)(Math.random()*255);
                int random_greenBlueOnly = (int)(Math.random()*255);
                int random_blueBlueOnly = (int)(Math.random()*255);
                pixels_randomred[i][j] = makePixel(random_redBlueOnly,random_greenBlueOnly,random_blueBlueOnly);
            }
        }
        randomBlueOnly.apply(pixels_randomBlueOnly, null);
        blueOnly_Checker(pixels_randomBlueOnly);
        System.out.println("Done testing BlueOnly");

        //Tests for blackAndWhite
        ImageEffect blackAndWhite = map.get("BlackAndWhite");
        int [][] pixels_blackAndWhite = new int[20][20];
        for (int i=0;i<pixels_blackAndWhite.length;i++) {
            for (int j = 0; j < pixels_blackAndWhite[0].length; j++) {
                pixels_blackAndWhite[i][j] = makePixel(255,255,255);
            }
        }
        blackAndWhite.apply(pixels_blackAndWhite, null);
        blackAndWhite_Checker(pixels_blackAndWhite);

        int [][] pixels_noblackAndWhite = new int[20][20];
        for (int i=0;i<pixels_noblackAndWhite.length;i++) {
            for (int j = 0; j < pixels_noblackAndWhite[0].length; j++) {
                pixels_noblackAndWhite[i][j] = makePixel(0,0,0);
            }
        }
        blackAndWhite.apply(pixels_noblackAndWhite, null);
        blackAndWhite_Checker(pixels_noblackAndWhite);

        ImageEffect randombw = map.get("BlackAndWhite");
        int [][] pixels_randombw = new int[10][10];
        for (int i=0;i<pixels_randombw.length;i++) {
            for (int j = 0; j < pixels_randombw[0].length; j++) {
                int random_redbw = (int)(Math.random()*255);
                int random_greenbw = (int)(Math.random()*255);
                int random_bluebw = (int)(Math.random()*255);
                pixels_randomred[i][j] = makePixel(random_redbw,random_greenbw,random_bluebw);
            }
        }
        randombw.apply(pixels_randombw, null);
        blackAndWhite_Checker(pixels_randombw);
        System.out.println("Done testing BlackAndWhite");

        //tests for verticalReflect
        ImageEffect verticalReflect = map.get("VerticalReflect");
        int [][] pixels_verticalReflect = new int[10][10];
        for (int i=0;i<pixels_verticalReflect.length;i++) {
            for (int j = 0; j < pixels_verticalReflect[0].length; j++) {
                int random_vrRed = (int)(Math.random()*255);
                int random_vrGreen = (int)(Math.random()*255);
                int random_vrBlue = (int)(Math.random()*255);
                pixels_verticalReflect[i][j] = makePixel(random_vrRed,random_vrGreen,random_vrBlue);
            }
        }
        //if I apply verticalReflect function twice to reflectedImage, reflectedImage should be the same picture
        //as pixels_verticalReflect since the two reflections should cancel each other out
        int [][] reflectedImage = verticalReflect.apply(pixels_verticalReflect, null);
        verticalReflect.apply(reflectedImage, null);
        for (int x=0;x<reflectedImage.length;x++) {
            for (int y=0; y<reflectedImage[0].length; y++) {
                    if(reflectedImage[x][y]!=pixels_verticalReflect[x][y]){
                        System.out.println("the image is not correctly vertically reflected");
                    }
            }
        }
        System.out.println("Done testing VerticalReflect");

        //Tests for horizontalReflect
        ImageEffect horizontalReflect = map.get("HorizontalReflect");
        int [][] pixels_horizontalReflect = new int[10][10];
        for (int i=0;i<pixels_horizontalReflect.length;i++) {
            for (int j = 0; j < pixels_horizontalReflect[0].length; j++) {
                int random_hrRed = (int)(Math.random()*255);
                int random_hrGreen = (int)(Math.random()*255);
                int random_hrBlue = (int)(Math.random()*255);
                pixels_horizontalReflect[i][j] = makePixel(random_hrRed,random_hrGreen,random_hrBlue);
            }
        }

        int [][] HorizReflectedImage = horizontalReflect.apply(pixels_horizontalReflect, null);
        horizontalReflect.apply(HorizReflectedImage, null);
        for (int x=0;x<HorizReflectedImage.length;x++) {
            for (int y=0; y<HorizReflectedImage[0].length; y++) {
                if(HorizReflectedImage[x][y]!=pixels_horizontalReflect[x][y]){
                    System.out.println("the image is not correctly horizontally reflected");
                }
            }
        }
        System.out.println("Done testing HorizontalReflect");

        //Tests for Grow
        ImageEffect Grow = map.get("Grow");
        int [][] pixels_Grow = new int[10][10];
        for (int i=0;i<pixels_Grow.length;i++) {
            for (int j = 0; j < pixels_Grow[0].length; j++) {
                int random_redGrow = (int)(Math.random()*255);
                int random_greenGrow = (int)(Math.random()*255);
                int random_blueGrow = (int)(Math.random()*255);
                pixels_Grow[i][j] = makePixel(random_redGrow,random_greenGrow,random_blueGrow);
            }
        }
        Grow.apply(pixels_Grow, null);
        for (int i=0;i<pixels_Grow.length-1;i++) {
            for (int j = 0; j < pixels_Grow[0].length-1; j++) {
                if((pixels_Grow[i][j]!= pixels_Grow[i][j+1]) && (pixels_Grow[i+1][j]==pixels_Grow[i+1][j+1]) && (pixels_Grow[i][j]==pixels_Grow[i+1][j+1])){
                    System.out.println("the image is not properly grown");
                }
            }
        }
        System.out.println("Done testing Grow");

        //Tests for Shrink
        //Shrink ignores the last row/column of any odd number of rows/columns
        //since this extra row/column cannot be shrunk by 2
        ImageEffect Shrink = map.get("Shrink");
        int [][] pixels_Shrink = new int[12][12];
        for (int i=0;i<pixels_Shrink.length;i++) {
            for (int j = 0; j < pixels_Shrink[0].length; j++) {
                int random_redShrink = (int)(Math.random()*255);
                int random_greenShrink = (int)(Math.random()*255);
                int random_blueShrink = (int)(Math.random()*255);
                pixels_Shrink[i][j] = makePixel(random_redShrink,random_greenShrink,random_blueShrink);
            }
        }
        int [][] shrunken_Pixels = Shrink.apply(pixels_Shrink, null);
        for (int i=0;i<pixels_Shrink.length-1;i+=2) {
            for (int j = 0; j < pixels_Shrink[0].length-1; j+=2) {
                if((getRed(pixels_Shrink[i][j])+getRed(pixels_Shrink[i+1][j])+getRed(pixels_Shrink[i][j+1])+getRed(pixels_Shrink[i+1][j+1]))/4 != getRed(shrunken_Pixels[i/2][j/2])){
                    System.out.println("the image is not properly shrunk");
                }
            }
        }
        System.out.println("Done Testing Shrink");

        //Tests for Threshold
        ImageEffect Threshold = map.get("Threshold");
        int [][] pixels_Threshold = new int[10][10];
        int value = 127;
        for (int i=0;i<pixels_Threshold.length;i++) {
            for (int j = 0; j < pixels_Threshold[0].length; j++) {
                int random_redThreshold = (int)(Math.random()*255);
                int random_greenThreshold = (int)(Math.random()*255);
                int random_blueThreshold = (int)(Math.random()*255);
                pixels_Threshold[i][j] = makePixel(random_redThreshold,random_greenThreshold,random_blueThreshold);
            }
        }
        ArrayList <ImageEffectParam> params = new ArrayList<>();
        params.add(new ImageEffectParam("Threshold",
                "Description of param.",
                10, 0, 255));
        int [][] thresh = Threshold.apply(pixels_Threshold, params);
        for (int i=0;i<thresh.length;i++) {
            for (int j = 0; j < thresh[0].length; j++) {
                if ((getRed(thresh[i][j]) == 255) && (getRed(pixels_Threshold[i][j]) <= value)) {
                    System.out.println("the over threshold red part of the image is not correct");
                }
                if ((getGreen(thresh[i][j]) == 255) && (getGreen(pixels_Threshold[i][j]) <= value)) {
                    System.out.println("the over threshold green part of the image is not correct");
                }
                if ((getBlue(thresh[i][j]) == 255) && (getBlue(pixels_Threshold[i][j]) <= value)) {
                    System.out.println("the over threshold blue part of the image is not correct");
                }
                if ((getRed(thresh[i][j]) == 0) && (getRed(pixels_Threshold[i][j]) >=  value)) {
                    System.out.println("the under threshold red part of the image is not correct");
                }
                if ((getGreen(thresh[i][j]) == 0) && (getGreen(pixels_Threshold[i][j]) >= value)) {
                    System.out.println("the under threshold green part of the image is not correct");
                }
                if ((getBlue(thresh[i][j]) == 0) && (getBlue(pixels_Threshold[i][j]) >= value)) {
                    System.out.println("the under threshold blue part of the image is not correct");
                }
            }
        }
        System.out.println("Done Testing Threshold");

        //Tests for Brighten

        //Tests for dottedImage
        ImageEffect dottedImage = map.get("dottedImage");
        int [][] pixels_dottedImage = new int[10][10];
        for (int i=0;i<pixels_dottedImage.length;i++) {
            for (int j = 0; j < pixels_dottedImage[0].length; j++) {
                int random_reddI = (int)(Math.random()*255);
                int random_greendI = (int)(Math.random()*255);
                int random_bluedI = (int)(Math.random()*255);
                pixels_Shrink[i][j] = makePixel(random_reddI,random_greendI,random_bluedI);
            }
        }
        int [][] newDottedImage = dottedImage.apply(pixels_dottedImage, null);
        for (int i=0;i<newDottedImage.length;i++) {
            for (int j = 0; j < newDottedImage[0].length; j++) {
                if((j%3==1)&&(i%3==1)){
                    if (newDottedImage[i][j]!=pixels_dottedImage[i][j]) {
                        System.out.println("the colored dots in dottedImage are not correct");
                    }
                }
                else{
                    if((getRed(newDottedImage[i][j])!=0)||(getGreen(newDottedImage[i][j])!=0)||(getGreen(newDottedImage[i][j])!=0)){
                        System.out.println("the black dots in dottedImage are not correct");
                    }
                }
            }
        }
        System.out.println("Done Testing Brighten");
    }









    public static Map<String, ImageEffect> getFilters() {
        String s = ImageEffect.class.getResource(ImageEffect.class.getSimpleName() + ".class").getFile();
        s = s.substring(0, s.lastIndexOf(ImageEffect.class.getSimpleName()) - 1);
        String[] searchPaths = s.split(File.pathSeparator);
        HashMap<String, ImageEffect> effects = new HashMap<>();

        for (int i = 0; i < searchPaths.length; i++) {
            searchPaths[i] = searchPaths[i].replace("%20", " ");
            File path = new File(searchPaths[i]);
            if (!path.isDirectory()) {
                continue;
            }

            File[] classFiles = path.listFiles((dir, name) -> name.endsWith(".class"));
            if (classFiles == null) {
                continue;
            }
            for (File classFile : classFiles) {
                String className = getBase(classFile);
                try {
                    Class<?> c = Class.forName(ImageEffect.class.getPackage().getName() + "." + className);
                    if (ImageEffect.class.isAssignableFrom(c)) {
                        Constructor<?> construct = c.getDeclaredConstructor();
                        construct.setAccessible(true);
                        effects.put(className, (ImageEffect) construct.newInstance());
                    }
                } catch (Exception ignored) {
                }
            }
        }
        return effects;
    }
    private static String getExtension(File file) {
        if (file == null || file.isDirectory()) {
            return "";
        }

        int index = file.getName().lastIndexOf('.');
        if (index == -1) {
            return "";
        }
        return file.getName().substring(index + 1);
    }

    private static String getBase(File file) {
        if (file == null || file.isDirectory()) {
            return "";
        }

        int index = file.getName().lastIndexOf('.');
        if (index == -1) {
            return file.getName();
        }
        return file.getName().substring(0, index);
    }
}

