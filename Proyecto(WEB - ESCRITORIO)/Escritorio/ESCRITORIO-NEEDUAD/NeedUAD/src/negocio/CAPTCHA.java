package negocio;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

/**
 * Clase para procesamiento de CAPTCHA.
 * 
 * @author JOSEA
 */
public class CAPTCHA {

    private JFileChooser fileChooser;
    private ImageIcon imageIcon;
    private File selectedFile;
    private String rutaImagen;
    private int rostros;

    private CascadeClassifier faceCascade;
    private Mat image;

    public CAPTCHA() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        this.faceCascade = new CascadeClassifier("c:/opencv/haarcascade_frontalface_alt.xml");
    }

    /**
     * Devuelve la cantidad de rostros que se encontraron en la imagen.
     *
     * @return
     */


    public void detectarRostros(String ruta, JLabel label) {
        this.image = Imgcodecs.imread(ruta);

        MatOfRect faces = new MatOfRect();
        faceCascade.detectMultiScale(image, faces);

        this.rostros = faces.toArray().length;

        for (Rect rect : faces.toArray()) {
            Point center = new Point(rect.x + rect.width / 2, rect.y + rect.height / 2);
            Imgproc.circle(image, center, Math.max(rect.width, rect.height) / 2, new Scalar(0, 0, 255), 4);
        }

        mostrarImagen(Mat2BufferedImage(image), label);
    }

    private BufferedImage Mat2BufferedImage(Mat image) {
        int bufferedImageType = BufferedImage.TYPE_INT_RGB;

        BufferedImage bufferedImage = new BufferedImage(image.cols(), image.rows(), bufferedImageType);

        byte[] data = new byte[image.cols() * image.rows() * (int) image.elemSize()];
        image.get(0, 0, data);

        int[] rgbData = new int[image.cols() * image.rows()];
        for (int i = 0; i < data.length; i += 3) {
            int blue = data[i] & 0xFF;
            int green = data[i + 1] & 0xFF;
            int red = data[i + 2] & 0xFF;

            rgbData[i / 3] = (red << 16) | (green << 8) | blue;
        }

        bufferedImage.setRGB(0, 0, image.cols(), image.rows(), rgbData, 0, image.cols());

        return bufferedImage;
    }

    public String buscarImagen() {
        this.rutaImagen = "";
        this.fileChooser = new JFileChooser();

        int result = fileChooser.showOpenDialog(null);

        if (result != JFileChooser.CANCEL_OPTION) {
            this.selectedFile = fileChooser.getSelectedFile();
            this.rutaImagen = selectedFile.getAbsolutePath();
        }
        return this.rutaImagen;
    }
    public int getRostros() {
    return rostros;
}

    public void mostrarImagen(BufferedImage imagenDetectada, JLabel label) {
        try {
            BufferedImage imagen = imagenDetectada;
            this.imageIcon = new ImageIcon(imagen);
            Image scaledImage = this.imageIcon.getImage().getScaledInstance(label.getWidth(), label.getHeight(),
                    Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(scaledImage));
            label.setHorizontalAlignment(SwingConstants.CENTER);
        } catch (Exception e) {
            System.out.println("Error al mostrar imagen: " + e.getMessage());
        }
    }
    

    public boolean capturarDesdeCamara(JLabel label) {
    VideoCapture videoCapture = new VideoCapture(0);

    

    Mat frame = new Mat();
    videoCapture.read(frame);

    if (!frame.empty()) {
        mostrarImagen(Mat2BufferedImage(frame), label);

        if(detectarRostrosEnCamara(frame, label)){
            videoCapture.release();
        System.out.println("Funciona el capturarDesdeCamara()");
            return true;
        }
    }

    return false;
}

public boolean detectarRostrosEnCamara(Mat image, JLabel label) {
    MatOfRect faces = new MatOfRect();
    faceCascade.detectMultiScale(image, faces);

    int numRostros = faces.toArray().length;

    if (numRostros > 0) {
        
        rostros++;
        for (Rect rect : faces.toArray()) {
            Point center = new Point(rect.x + rect.width / 2, rect.y + rect.height / 2);
            Imgproc.circle(image, center, Math.max(rect.width, rect.height) / 2, new Scalar(0, 0, 255), 4);
        }

        System.out.println("Penultimo caras :"+rostros);
        mostrarImagen(Mat2BufferedImage(image), label);
        return true;
    } else {
        System.out.println("No se encontraron rostros en la imagen capturada desde la cámara.");
        return false;
    }
}

}
