import java.io.IOException;

public class Main {



    public static void main(String[] args) {
        ShowRunner runner = new ShowRunner();
        try {
            runner.startTheShow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
