class CategorizeBox {
    public String categorizeBox(int length, int width, int height, int mass) {
        
        boolean bulky = false;
        boolean heavy = false;
        
        long v = (long) length * width * height;
        
        if (length >= 10000 || width >= 10000 || height >= 10000 || v >= 1000000000) 
            bulky = true;
        if (mass >= 100) 
            heavy = true;
        
        if (bulky && heavy) 
            return "Both";
        else if (bulky) 
            return "Bulky";
        else if (heavy) 
            return "Heavy";
        else {
            return "Neither";
        }
    }
}