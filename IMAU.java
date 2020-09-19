import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class IMAU {

    private static String USERNAME = "";
    private static String PASSWD = "";

    public static void main(String[] args) {

        if(args[0].equals("login")){
            login(USERNAME, PASSWD);
        }else if(args[0].equals("logout")){
            logout(USERNAME, PASSWD);
        }else{
            System.err.println("please enter login or logout");
        }

    }

    public static void login(String username, String passwd) {
        try {
            log(username, passwd, true);
        }catch (IOException e){
            System.err.println("Login exception");
        }

    }

    public static void logout(String username, String passwd) {
        try {
            log(username, passwd, false);
        }catch (IOException e){
            System.err.println("Logout exception");
        }
    }

    private static void log(String username, String passwd, boolean action) throws IOException  {


        URL url = new URL("http://login.imau.edu.cn:801/srun_portal_pc.php?ac_id=1&");
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "8859_1");
        String from = "";
        if(action)
            from = "action=login&username="+username+"&password="+passwd+"&ac_id=1&user_ip=&nas_ip=&user_mac=&save_me=0&ajax=1";
        else
            from = "action=logout&username="+username+"&password="+passwd+"&ajax=1";

        out.write(from);
        out.flush();
        out.close();
        String sCurrentLine;
        String sTotalString;
        sCurrentLine = "";
        sTotalString = "";
        InputStream l_urlStream;
        l_urlStream = connection.getInputStream();
        BufferedReader l_reader = new BufferedReader(new InputStreamReader(
                l_urlStream));
        while ((sCurrentLine = l_reader.readLine()) != null) {
            sTotalString += sCurrentLine + "\r\n";

        }
        System.out.println(sTotalString);
    }

}