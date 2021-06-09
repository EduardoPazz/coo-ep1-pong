import java.awt.Color;

public class Wall
{
    private double cx;
    private double cy;
    private double width;
    private double height;
    private Color color;
    private String id;
    
    public Wall(final double cx, final double cy, final double width, final double height, final Color color, final String id) {
        this.cx = cx;
        this.cy = cy;
        this.width = width;
        this.height = height;
        this.color = color;
        this.id = id;
    }
    
    public void draw() {
        GameLib.setColor(this.color);
        GameLib.fillRect(this.cx, this.cy, this.width, this.height);
    }
    
    public double getWidth() {
        return this.width;
    }
    
    public double getHeight() {
        return this.height;
    }
    
    public double getCx() {
        return this.cx;
    }
    
    public double getCy() {
        return this.cy;
    }
    
    public String getId() {
        return this.id;
    }
}