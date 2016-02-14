public class DeletedEntry extends MyHashEntry 
{
  private static DeletedEntry entry = null;
 
      private DeletedEntry() 
      {
        super(-1, -1);
      }
 
      public static DeletedEntry getUniqueDeletedEntry() 
      {
            if (entry == null)
            {
             entry = new DeletedEntry();
            }
            return entry;
      }
}