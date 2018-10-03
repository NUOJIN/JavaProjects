//cheng758
//luo00034
import java.awt.*;

public class Bounds {

	// The extrema of our rectangle
	private Vec2 min;
	private Vec2 max;

	// Getters, no setters. Use extend instead.
	public Vec2 getMin(){ return min; }
	public Vec2 getMax(){ return max; }

	// Default min and max to null, so that they are
	// initialized on the first call to extend.
	public Bounds(){
		min = null;
		max = null;
	}

	// TODO
	// Check if a point is outside the bounds of the box
	// Ignore z axis for this function
	public boolean isOutside(double x, double y){
		if(x<min.x||x>max.x||y<min.y||y>max.y)
			return true;
		return false;
	}

	// TODO
	// Extend the size of the box to include a new point
	public void extend(double x, double y){
		if(min==null||max==null){
			min = new Vec2(x,y);
			max = new Vec2(x,y);
		}
		else if(isOutside(x,y)){
			if(x>max.x)
				max.x = x;
			if(x<min.x)
				min.x = x;
			if(y>max.y)
				max.y = y;
			if(y<min.y)
				min.y = y;
		}
		// If we haven't set min or max yet, do so now.

	}

	// TODO
	// Returns the distance from the box surface to a point
	// Return 0 if the point is inside the box!
	public double exteriorDistance(double x, double y){
		if(!isOutside(x,y))
			return 0;
		else{
			if(x>=max.x&&y>=max.y)
				return max.distance(new Vec2(x,y));
			else if(y>max.y&&x<max.x&&x>min.x)
				return y-max.y;
			else if(y>=max.y&&x<=min.x){
				Vec2 temp = new Vec2(min.x,max.y);
				return temp.distance(new Vec2(x,y));
			}
			else if(x<min.x&&y<max.y&&y>min.y)
				return min.x - x;
			else if(x<=min.x&&y<=min.y)
				return min.distance(new Vec2(x,y));
			else if(y<min.y&&x>min.x&&x<max.x)
				return min.y - y;
			else if(x>=max.x&&y<=min.y){
				Vec2 temp = new Vec2(max.x,min.y);
				return temp.distance(new Vec2(x,y));
			}
			else //if(x>max.x&&y>min.y&&y<max.y)
				return x - max.x;
		}
	}

	// TODO
	// Extend the size of the box to include a new bounds
	public void extend(Bounds b){
		this.extend(b.getMax().x,b.getMax().y);
		this.extend(b.getMin().x,b.getMin().y);
	}

	// Draw a black opaque rectangle
	public void paint(Graphics2D g){
		g.setColor(Color.black);
		g.drawRect((int)min.x, (int)min.y, (int)(max.x-min.x), (int)(max.y-min.y));
	}

} // end class Bounds
