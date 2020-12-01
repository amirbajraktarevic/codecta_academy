package Characters;

 public class MickeyMouse implements DisneyCharacter {
     public MickeyMouse(){
         displayName();
         displayInfo();
     }
    public String displayName() {
        return "Mickey Mouse";
    }

    public String displayInfo() {
        return "Hello it's me, Mickey Mouse. I'm really happy that you chose me to play with!";
    }
}
