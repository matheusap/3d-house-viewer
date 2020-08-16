package lab.pkg4;

import java.util.logging.Level;

/**
 *
 * @author Matheus Pacheco
 */
public class CS355LWJGL 
{
    
    public static void main(String[] args) 
  {
    LWJGLSandbox main = null;
    try 
    {
      main = new LWJGLSandbox();
      main.create(new StudentLWJGLController());
      main.run();
    }
    catch(Exception ex) 
    {
      ex.printStackTrace();
    }
    finally {
      if(main != null) 
      {
        main.destroy();
      }
    }
  }
    
}
