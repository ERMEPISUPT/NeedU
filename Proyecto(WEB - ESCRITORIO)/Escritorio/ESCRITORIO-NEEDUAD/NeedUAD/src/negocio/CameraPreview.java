
package negocio;
import java.awt.Graphics2D;
import javax.swing.*;
import org.opencv.core.*;
import org.opencv.videoio.VideoCapture;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.DataBufferByte;

public class CameraPreview {

    private VideoCapture capture;
    private Mat frame;
    private boolean cameraActive;
    private JLabel targetLabel;

    public CameraPreview(JLabel targetLabel) {
        this.targetLabel = targetLabel;
        initializeCamera();
    }

    private void initializeCamera() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        capture = new VideoCapture(0);
        frame = new Mat();

        if (capture.isOpened()) {
            cameraActive = true;

            Runnable frameGrabber = () -> {
                while (cameraActive) {
                    capture.read(frame);

                    if (!frame.empty()) {
                        Image imageToShow = Mat2BufferedImage(frame);
                        updateImageView(targetLabel, imageToShow);
                    }
                }
            };

            Thread thread = new Thread(frameGrabber);
            thread.setDaemon(true);
            thread.start();
        } else {
            System.out.println("No se pudo abrir la cámara.");
        }
    }
    private BufferedImage scaleImageToLabelSize(BufferedImage image, int width, int height) {
        if (image == null) {
            return null;
        }
        
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }
    
    private void updateImageView(JLabel label, Image image) {
        BufferedImage scaledImage = scaleImageToLabelSize((BufferedImage) image, label.getWidth(), label.getHeight());
        SwingUtilities.invokeLater(() -> label.setIcon(new ImageIcon(scaledImage)));
    }

    private BufferedImage Mat2BufferedImage(Mat frame) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (frame.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = frame.channels() * frame.cols() * frame.rows();
        byte[] b = new byte[bufferSize];
        frame.get(0, 0, b);
        BufferedImage image = new BufferedImage(frame.cols(), frame.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return image;
    }

    public void stopCamera() {
        if (capture != null && capture.isOpened()) {
            cameraActive = false;
            capture.release();
        }
    }
}
