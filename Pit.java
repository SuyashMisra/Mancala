public class Pit
{
   private int stoneAmount;
   
   public Pit(int initialAmount)
   {
      stoneAmount = initialAmount; 
   }
   public void addStone()
   {
      stoneAmount++;
   }
   public void setStones(int stones)
   {
	   stoneAmount = stones;
   }
   public int takeAll()
   {
      int result = stoneAmount;
      stoneAmount = 0;
      return result;
   }
   public int getAmount()
   {
      return stoneAmount;
   }
}