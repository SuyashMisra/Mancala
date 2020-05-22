import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;


public class MancalaDesign implements Interface
{
   public Shape getMancalaShape()
   {
      return new RoundRectangle2D.Double(25, 70,100,
    		 200, 40, 40);
   }

   public Shape getPitShape()
   {
	   return new Ellipse2D.Double(5, 5,110, 
	    		  145);
   }
   
   public Shape [] getPitStoneShapes(int stoneAmount)
   {
	   if (stoneAmount == 0)
		   return new Shape [] { new Rectangle2D.Double(0,0,0,0) };
	   
	   ArrayList<Ellipse2D.Double> shapeList = 
		   new ArrayList<Ellipse2D.Double>();
	   
	   int dimension = (int) (Math.sqrt(stoneAmount) +2 );
	   int width = 110 / (dimension) - 10;
	   int height = 145 / (dimension) - 20;
	   
	   for (int i = 0; i < stoneAmount; i++)
	   {
		   int xMod = (i % dimension);
		   int yMod = (i / dimension);
		   int x = xMod * (width + 4) + 18;
		   int y = yMod * (height + 4) + 30;
		   shapeList.add(new Ellipse2D.Double(x, y, width, height));
	   }
	   
	   Ellipse2D.Double [] rectList = new Ellipse2D.Double[shapeList.size()];
	   rectList = shapeList.toArray(rectList);
	   return rectList;
   }
      
   public Shape [] getMancalaStoneShapes(int stoneAmount)
   {
	   if (stoneAmount == 0)
		   return new Shape [] { new Rectangle2D.Double(0,0,0,0) };
	   
	   ArrayList<Ellipse2D.Double> shapeList = 
		   new ArrayList<Ellipse2D.Double>();
	   
	   int dimension = (int) (Math.sqrt(stoneAmount) + 3);
	   int width = 100/ (dimension) - 7;
	   int height =200 / (dimension) - 7;
	   
	   for (int i = 0; i < stoneAmount; i++)
	   {
		   int xMod = (i % dimension);
		   int yMod = (i / dimension);
		   int x = xMod * (width + 4) + 40;
		   int y = yMod * (height + 4) + 80;
		   shapeList.add(new Ellipse2D.Double(x, y, width, height));
	   }
	   
	   Ellipse2D.Double [] rectList = new Ellipse2D.Double[shapeList.size()];
	   rectList = shapeList.toArray(rectList);
	   return rectList;
   }   
   public Color getColor()
   {
      return Color.BLACK;
   }
   public Color getFillColor()
   {
	   return Color.CYAN;
   }

}