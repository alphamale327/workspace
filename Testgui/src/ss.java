import java.awt.image.*;
import java.awt.*;
import javax.swing.*;

class PaintPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5234408578614716833L;
	BufferedImage bg;

    PaintPanel(LayoutManager2 layout) {
        super(layout);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (bg==null) {
            bg = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = bg.createGraphics();
            GradientPaint gp = new GradientPaint(
                0,0,Color.RED,500,500,Color.BLUE);
            g2.setPaint(gp);
            g2.fillRect(0,0,500,500);
            g2.dispose();
        }

        g.drawImage(bg,0,0,getWidth(),getHeight(),this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JPanel buttons = new JPanel(
                    new FlowLayout(FlowLayout.CENTER));
                buttons.setOpaque(false);
                buttons.add(new JButton("Start"));
                buttons.add(new JButton("Stop"));

                PaintPanel pp = new PaintPanel(new BorderLayout());
                pp.setPreferredSize(new Dimension(200,100));

                pp.add(buttons, BorderLayout.SOUTH);

                JOptionPane.showMessageDialog(null,pp);

                JPanel gui = new JPanel(new BorderLayout());
                gui.setBackground(Color.ORANGE);
                gui.add(pp, BorderLayout.CENTER);
                gui.add(buttons, BorderLayout.SOUTH);

                JOptionPane.showMessageDialog(null,gui);
            }
        });
    }
}