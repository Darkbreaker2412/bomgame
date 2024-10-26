package MenuSetUp;

public class DimensionSize {
    public static final int originalTileSize = 16;
    public static final int scale = 3;
    public static final int tileSize = originalTileSize * scale;
    public static final int maxScreenCol = 12;
    public static final int maxScreenRow = 12;
    public static final int screenWidth = maxScreenCol * tileSize;
    public static final int screenHeight = maxScreenRow * tileSize;
}
