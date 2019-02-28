public class AspectValue {
    private String aspect;
    private int value;

    public AspectValue(String aspect, int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Values must not be negative!");
        }
        if(aspect == null){
            throw new IllegalArgumentException("Aspect must be defined!");
        }
        this.aspect = aspect;
        this.value = value;
    }

    public String toString() {
        return this.aspect + this.value;
    }

    public void changeValue(int x) {
    this.value += x;
    }

    public void delete() {
        this.value = 0;
        this.aspect = null;
    }

}

