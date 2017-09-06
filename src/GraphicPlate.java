import java.io.*;
import java.lang.*;
import java.awt.*;

class GraphicPlate {
  private Point center;
  private Point coords;
  private Color color;
  private int phID;
  private int angle;
  private int size;
  private int ident;

private Point move(int x, int y, int bX, int bY, int angle) {
    double rotation = Math.PI/(180.0/angle);
    Point p = new Point();
    p.y = (int)
      (x * Math.sin(rotation)  + y * Math.cos(rotation)- Math.sin(rotation) * bX- Math.cos(rotation) * bY+ bY);
    p.x = (int)  (x * Math.cos(rotation)- y * Math.sin(rotation) - Math.cos(rotation) * bX + Math.sin(rotation) * bY + bX);

    return(p);
  }
  GraphicPlate(int ident, Point center, Point coords, int size) {
    this.ident = ident;
    this.center = center;
    this.size = size;

    this.phID = -1;
    setColor(phID);

    this.angle = 72 * ident;
    this.coords = new Point(move(coords.x, coords.y, 
					     center.x, center.y, angle));
    this.coords.x -= 10;
    this.coords.y += 5;
  }
    
  public void setColor(int phID) {
    this.phID = phID;
    
    if(phID == -1) {
      this.color = Color.black;
    } else if(phID == 0) {      
      this.color = Color.red;
    } else if(phID == 1) {      
      this.color = Color.blue;
    } else if(phID == 2) {      
      this.color = Color.green;
    } else if(phID == 3) {      
      this.color = Color.yellow;
    } else if(phID == 4) {      
      this.color = Color.white;
    }
  }

  public void draw(Graphics g) {
    g.setColor(color);
    g.fillOval(coords.x, coords.y, size, size);
  }

}
  
