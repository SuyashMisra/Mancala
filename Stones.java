public class Stones
{
   private int stoneAmount;
   public Stones()
   {
      stoneAmount = 0;
   }
   
   public void addStones(int amount)
   {
      stoneAmount += amount;
   }
   
   public int getAmount()
   {
      return stoneAmount;
   }
   
   public void setStones(int stone)
   {
	   stoneAmount = stone;
   }
}