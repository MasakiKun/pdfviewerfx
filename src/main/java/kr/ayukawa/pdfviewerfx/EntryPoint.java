package kr.ayukawa.pdfviewerfx;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.File;

public class EntryPoint extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		final File pdfFile = new File(ClassLoader.getSystemResource("sample_pdf.pdf").toURI());

		BufferedImage bufferedImage = null;
		Image image = null;
		try(PDDocument doc = PDDocument.load(pdfFile)) {
			PDFRenderer renderer = new PDFRenderer(doc);
			bufferedImage = renderer.renderImage(0);
			image = SwingFXUtils.toFXImage(bufferedImage, null);
		}

		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setX(10);
		imageView.setY(10);
		imageView.setFitWidth(595);
		imageView.setPreserveRatio(true);

		Group root = new Group(imageView);
		Scene scene = new Scene(root, 595, 370);
		primaryStage.setTitle("pdf cover");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
