import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Item {
	private int itemNum;
	private int x;
	private int y;
	private int width;
	private int height;
	private int tracking_i = 0;
	private File[] listOfFiles;
	private BufferedImage image;
	private String name;
	private File folder;

	public Item(String name, int itemNum, int x, int y, int width, int height,
			String path) {
		this.name = name;
		this.itemNum = itemNum;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		folder = new File(path);
		listOfFiles = folder.listFiles();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		if (name.equals("bottom")) {
			if (listOfFiles[tracking_i].getName().endsWith("short.jpg")) {
				this.height = 133;
			} else {
				this.height = 255;
			}
		}
		return height;
	}

	public BufferedImage getImage() {
		try {
			image = ImageIO.read(listOfFiles[tracking_i]);

		} catch (Exception ex) {
			System.out.print(name + " error");
		}
		return image;
	}

	public int getListLength() {
		return listOfFiles.length;
	}

	public int getTracking_i() {
		return tracking_i;
	}

	public void setTracking_i(int tracking_i) {
		this.tracking_i = tracking_i;
	}

	public String getItemName() {
		return listOfFiles[tracking_i].getName();
	}

	public String getName() {
		return name;
	}

	public int getItemNum() {
		return itemNum;
	}
}
