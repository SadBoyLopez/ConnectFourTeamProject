import java.io.*;
import java.net.*;
import java.util.Observable;
import java.util.Observer;
public class Connect4Main extends Observable
{
    public static void main(String[] args)
    {
        ConnectFourClientGUI gui = new ConnectFourClientGUI();
        gui.setVisible(true);
    }
}
