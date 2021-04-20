import java.io.FileReader;
import java.io.IOException;

class AvoidTryCatchFinallyCheck {
    AvoidTryCatchFinallyCheck(AvoidTryCatchFinallyCheck mc) {
    }

    public void openFileWithFInally() {
        FileReader reader = null;
        try { // Noncompliant
            reader = new FileReader("someFile");
            int i = 0;
            while (i != -1) {
                i = reader.read();
                System.out.println((char) i);
            }
        } catch (IOException e) {
            //do something clever with the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //do something clever with the exception
                }
            }
            System.out.println("--- File End ---");
        }
    }


    public void openFileWithoutFInally() {
        FileReader reader = null;
        try {
            reader = new FileReader("someFile");
            int i = 0;
            while (i != -1) {
                i = reader.read();
                System.out.println((char) i);
            }
        } catch (IOException e) {
            //do something clever with the exception
        }
    }

}