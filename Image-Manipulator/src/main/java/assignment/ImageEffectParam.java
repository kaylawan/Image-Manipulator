package assignment;

/**
 * This class provides an abstraction for integer parameters
 * which are used in the ImageEffect class.
 */
public class ImageEffectParam {
    private final String name;
    private final String description;
    private final int defaultValue;
    private final int maxValue;
    private final int minValue;

    private int value;

    public ImageEffectParam(String name, String description,
                            int defaultValue, int minValue,
                            int maxValue) {
        this.name = name;
        this.description = description;
        this.defaultValue = defaultValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDefaultValue() {
        return Integer.toString(defaultValue);
    }

    public int getMaxValue() {
        return maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getValue() {
        return value;
    }
}
