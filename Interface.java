import java.awt.Color;
import java.awt.Shape;

public interface Interface
{
   
   public Color getColor();
   public Color getFillColor();
   public Shape getPitShape();
   public Shape getMancalaShape();
   public Shape [] getPitStoneShapes(int stoneAmount);
   public Shape [] getMancalaStoneShapes(int stoneAmount);
}