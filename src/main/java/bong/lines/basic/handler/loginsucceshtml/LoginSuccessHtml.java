package bong.lines.basic.handler.loginsucceshtml;

import bong.lines.basic.comm.LoginDto;
import bong.lines.basic.handler.getindexhtml.IndexHTMLHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Objects;

public class LoginSuccessHtml extends Thread {

    private static final Logger log = LoggerFactory.getLogger(IndexHTMLHandler.class);

    private Socket connection;

    public LoginSuccessHtml(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}, Host Address : {}", connection.getInetAddress(), connection.getPort(), connection.getInetAddress().getHostAddress());

        try(InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

            String line = " ";

            byte[] body = null;

            do {
                line = bufferedReader.readLine();

                if(line != null && line.contains("GET") && line.contains("user/create")){
                    System.out.println("line = " + line);
                    String address = line.split(" ")[1]
                            .replace("/", "")
                            .replace("user" ,"");
                    String screenName = address.split("\\?")[0];
                    String[] login = address.split("\\?")[1].split("&");

                    LoginDto loginDto = new LoginDto(login[0].split("=")[1],login[1].split("=")[1],login[2].split("=")[1],login[3].split("=")[1]);
                    System.out.println("loginDto.toString() = " + loginDto.toString());

                    body = Objects.requireNonNull(
                                    LoginSuccessHtml.class
                                            .getResourceAsStream("/templates/user/" + screenName + ".html"))
                            .readAllBytes();
                }
            } while (body == null);

            DataOutputStream dos = new DataOutputStream(out);
            response200Header(dos, body.length);
            responseBody(dos, body);
        }catch (Exception exception){
            log.error(exception.getMessage());
            exception.printStackTrace();
        }
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent){
        try{
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8 \r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        }catch (Exception exception){
            log.error(exception.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body){
        try{
            dos.write(body, 0, body.length);
            dos.writeBytes("\r\n");
            dos.flush();
        }catch (Exception exception){
            exception.printStackTrace();
            log.error(exception.getMessage());
        }
    }

}
