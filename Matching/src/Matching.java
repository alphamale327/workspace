import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.LayoutManager2;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Matching extends JPanel implements ActionListener {

	private int nameLine = 10;
	private int topNum;

	private static JPanel[] controlPanel;
	private JButton[] buttons;
	private JCheckBox[] checkBoxes;

	private Item[] items;

	private final static int totalItemNum = 5;

	// String debug = "";

	public static void main(String[] args) {
		JFrame frame = new JFrame("matching_ver0.2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 750);

		Matching matching = new Matching(new BorderLayout());
		matching.setPreferredSize(new Dimension(500, 730));

		JPanel HQPanel = new JPanel(new BorderLayout());
		HQPanel.setPreferredSize(new Dimension(140, 730));
		HQPanel.setLayout(new GridLayout(20,1));
		for (int i = 0; i < totalItemNum; i++) {
			HQPanel.add(controlPanel[i]);
		}

		JPanel gui = new JPanel(new BorderLayout());
		gui.setBackground(Color.WHITE);
		gui.add(matching, BorderLayout.CENTER);
		gui.add(HQPanel, BorderLayout.EAST);

		frame.getContentPane().add(gui);
		frame.pack();
		frame.setVisible(true);
	}

	public Matching(LayoutManager2 layout) {
		super(layout);

		items = new Item[totalItemNum];
		buttons = new JButton[totalItemNum];
		checkBoxes = new JCheckBox[totalItemNum];
		controlPanel = new JPanel[totalItemNum];

		int itemNum = 0;

		// public Item(String name,
		// int itemNum,
		// int x,
		// int y,
		// int width,
		// int height,
		// String path)

		items[itemNum] = new Item("cap", itemNum, 214, 44, 73, 58, "src/caps");
		checkBoxes[itemNum] = new JCheckBox("cap", true);
		buttons[itemNum] = new JButton(">");
		itemNum++;
		//

		items[itemNum] = new Item("top", itemNum, 158, 114, 186, 194,
				"src/tops");
		checkBoxes[itemNum] = new JCheckBox("top", true);
		buttons[itemNum] = new JButton(">");
		itemNum++;

		items[itemNum] = new Item("jacket", itemNum, 158, 112, 186, 194,
				"src/jackets");
		checkBoxes[itemNum] = new JCheckBox("jacket", true);
		buttons[itemNum] = new JButton(">");
		itemNum++;

		items[itemNum] = new Item("bottom", itemNum, 198, 320, 108, 3,
				"src/bottoms");
		checkBoxes[itemNum] = new JCheckBox("bottom", true);
		buttons[itemNum] = new JButton(">");
		itemNum++;
		
		items[itemNum] = new Item("shoes", itemNum, 195, 590, 115, 100,
				"src/shoes");
		checkBoxes[itemNum] = new JCheckBox("shoes", true);
		buttons[itemNum] = new JButton(">");
		itemNum++;

		for (int i = 0; i < totalItemNum; i++) {
			controlPanel[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
			controlPanel[i].add(checkBoxes[i], BorderLayout.WEST);
			controlPanel[i].add(buttons[i], BorderLayout.EAST);
			checkBoxes[i].addActionListener(this);
			buttons[i].addActionListener(this);

			if (items[i].getListLength() == 0) {
				checkBoxes[i].setSelected(false);
				checkBoxes[i].setVisible(false);
				buttons[i].setVisible(false);
			}

			if (items[i].getName().equals("top")) {
				topNum = items[i].getItemNum();
			}
		}
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.clearRect(0, 0, 500, 750);

		for (int i = 0; i < totalItemNum; i++) {
			if (items[i].getListLength() != 0) {
				if (items[i].getImage() != null && checkBoxes[i].isSelected()) {
					g2d.drawImage(items[i].getImage(), items[i].getX(),
							items[i].getY(), items[i].getWidth(),
							items[i].getHeight(), null);

					nameLine = nameLine + 10;
					g2d.drawString(
							items[i].getName() + ": " + items[i].getItemName(),
							10, nameLine);
				} else {
					g2d.clearRect(items[i].getX(), items[i].getY(),
							items[i].getWidth(), items[i].getHeight());
					if (items[i].getName().equals("jacket")
							&& checkBoxes[topNum].isSelected()) {
						g2d.drawImage(items[topNum].getImage(),
								items[topNum].getX(), items[topNum].getY(),
								items[topNum].getWidth(),
								items[topNum].getHeight(), null);
					}
				}
			}
		}
		nameLine = 10;

	}

	public void actionPerformed(ActionEvent arg0) {
		for (int i = 0; i < totalItemNum; i++) {
			if (arg0.getSource() == buttons[i]) {
				if (items[i].getTracking_i() <= items[i].getListLength() - 2) {
					items[i].setTracking_i(items[i].getTracking_i() + 1);
				} else {
					items[i].setTracking_i(0);
				}
			}

			if (!checkBoxes[i].isSelected()) {
				buttons[i].setVisible(false);
			} else {
				buttons[i].setVisible(true);
			}

			// debug = arg0.getActionCommand();
		}
		repaint();
	}
}