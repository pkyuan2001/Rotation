import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.lang.Math;

public class Animation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Animation();
    }

    public Animation() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Graphics");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new Panel());
                frame.pack();
                frame.setVisible(true);
                frame.setSize(400,400);
                frame.setLocation(800,150);
            }
        });
    }

    public class Panel extends JPanel {

        private final double[] topLeftFront = {100,400,0};
        private final double[] topRightFront = {400,400,0};
        private final double[] bottomLeftFront = {100,100,0};
        private final double[] bottomRightFront = {400,100,0};
        private final double[] topLeftBack = {100,400,300};
        private final double[] topRightBack = {400,400,300};
        private final double[] bottomLeftBack = {100,100,300};
        private final double[] bottomRightBack = {400,100,300};
        private final Vectors[] cubePoints = {new Vectors(topLeftFront),new Vectors(topRightFront),
                new Vectors(bottomLeftFront), new Vectors(bottomRightFront), new Vectors(topLeftBack),
                new Vectors(topRightBack), new Vectors(bottomLeftBack), new Vectors(bottomRightBack)};
        private void rotate() throws Exception {
            int i = 0;
            for (Vectors point: cubePoints) {
                cubePoints[i] = XRotation(point, 0.01);
                i++;
            }
        }

        public Panel() {
            Timer timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        rotate();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    repaint();
                }
            });
            timer.start();
        }



        private Vectors XRotation(Vectors point, Double degree) throws Exception {
            double[][] entries = {{Math.cos(degree), 0, Math.sin(degree)}, {0,1,0},
                    {Math.sin(degree), 0, Math.cos(degree)}};
            Matrix rotationMatrix = new Matrix(entries);
            return rotationMatrix.mult(point.toColumnMatrix()).toVector();
        }
        private double[] Projection(Vectors point) throws Exception {
            double[][] entries = {{1,0,0}, {0,1,0}};
            Matrix projectionMatrix = new Matrix(entries);
            return projectionMatrix.mult(point.toColumnMatrix()).toPos();
        }
        private int[][] Project() throws Exception{
            int[][] position = new int[8][2];
            int i = 0;
            for(Vectors vec: cubePoints){
                double[] projected = Projection(vec);
                int[] pos = {(int)Math.round(projected[0]), (int)Math.round(projected[1])};
                position[i] = pos;
                i++;
            }
            return position;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(Color.BLACK);
            try {
                int [][] position = Project();
                for(int[] pos: position){
                    g2d.fillOval(pos[0], pos[1], 10, 10);

                }
                g2d.drawLine(position[0][0], position[0][1], position[1][0], position[1][1]);
                g2d.drawLine(position[0][0], position[0][1], position[2][0], position[2][1]);
                g2d.drawLine(position[0][0], position[0][1], position[4][0], position[4][1]);
                g2d.drawLine(position[3][0], position[3][1], position[1][0], position[1][1]);
                g2d.drawLine(position[3][0], position[3][1], position[2][0], position[2][1]);
                g2d.drawLine(position[3][0], position[3][1], position[7][0], position[7][1]);
                g2d.drawLine(position[5][0], position[5][1], position[1][0], position[1][1]);
                g2d.drawLine(position[5][0], position[5][1], position[4][0], position[4][1]);
                g2d.drawLine(position[5][0], position[5][1], position[7][0], position[7][1]);
                g2d.drawLine(position[6][0], position[6][1], position[3][0], position[3][1]);
                g2d.drawLine(position[6][0], position[6][1], position[4][0], position[4][1]);
                g2d.drawLine(position[6][0], position[6][1], position[7][0], position[7][1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}