import java.io.*;
import java.lang.*;
import java.awt.*;

class GraphicChopstick {
  private Point center;
  private Point coordStart;
  private Point coordEnd;
  private Color color;
  private int ID;
  private int angle;
  private int phID;

  GraphicChopstick(int ID, Point center, Point coordStart, Point coordEnd) {
    this.ID = ID;
    this.center = center;
    this.phID = -1;
    setColor(phID);

    this.angle = 72 * ID + 36;
    this.coordStart = new Point(rotation(coordStart.x, coordStart.y, 
						 center.x, center.y, angle));
    this.coordStart.y += 15;

    this.coordEnd = new Point(rotation(coordEnd.x, coordEnd.y, 
					       center.x, center.y, angle));
    this.coordEnd.y += 15;
  }
  
  public void draw(Graphics g) {
    g.setColor(color);
    g.drawLine(coordStart.x, coordStart.y, coordEnd.x, coordEnd.y);
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

 private Point rotation(int x, int y, int bX, int bY, int angle) {
    Point p = new Point();
    double a = Math.PI/(180.0/angle);
    p.x = (int)  (x * Math.cos(a)- y * Math.sin(a) - Math.cos(a) * bX + Math.sin(a) * bY + bX);
    p.y = (int)
      (x * Math.sin(a)  + y * Math.cos(a)- Math.sin(a) * bX- Math.cos(a) * bY+ bY);

    return(p);
  }
}
  
