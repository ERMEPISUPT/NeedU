package negocio;


import java.io.IOException;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class clsNTomcat {

    public void apagarTomcat() {
        try {
            
            String usuario = "root";
            String host = "161.132.47.59";
            String password = "nHb281o54#FC5*"; 

            JSch jsch = new JSch();

            Session session = jsch.getSession(usuario, host, 22);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no"); 

            session.connect();

            
            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            
            
            String comando = "systemctl stop tomcat9.service";

            channel.setCommand(comando);

            channel.connect();

            while (!channel.isClosed()) {
                Thread.sleep(1000);
            }

            channel.disconnect();
            session.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void encenderTomcat() {
        try {
            String usuario = "root";
            String host = "161.132.47.59";
            String password = "nHb281o54#FC5*"; 

            
            JSch jsch = new JSch();

            
            Session session = jsch.getSession(usuario, host, 22);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no"); 

            session.connect();

            ChannelExec channel = (ChannelExec) session.openChannel("exec");

            String comando = "systemctl start tomcat9.service";

            channel.setCommand(comando);

            channel.connect();

            while (!channel.isClosed()) {
                Thread.sleep(1000);
            }

            channel.disconnect();
            session.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public String obtenerEstadoTomcat() {
        String estado = "Desconocido";

        try {
            String usuario = "root";
            String host = "161.132.47.59";
            String password = "nHb281o54#FC5*"; 

            JSch jsch = new JSch();

            Session session = jsch.getSession(usuario, host, 22);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no"); 

            session.connect();

            ChannelExec channel = (ChannelExec) session.openChannel("exec");

            String comando = "systemctl is-active tomcat9.service";

            channel.setCommand(comando);

            channel.connect();

            while (!channel.isClosed()) {
                Thread.sleep(1000);
            }

            java.io.InputStream in = channel.getInputStream();
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(in));
            estado = reader.readLine();

            channel.disconnect();
            session.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return estado;
    }

}
