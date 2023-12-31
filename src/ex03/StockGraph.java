package ex03;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class StockGraph extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int MARGIN = 50;

    private Map<String, Double> stockData;

    public StockGraph(Map<String, Double> stockData) {
        this.stockData = stockData;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int graphWidth = getWidth() - 2 * MARGIN;
        int graphHeight = getHeight() - 2 * MARGIN;

        g.drawLine(MARGIN, MARGIN + graphHeight, MARGIN, MARGIN);
        g.drawLine(MARGIN, MARGIN + graphHeight, MARGIN + graphWidth, MARGIN + graphHeight);

        double minValue = Double.MAX_VALUE;
        double maxValue = Double.MIN_VALUE;
        for (double value : stockData.values()) {
            if (value < minValue) {
                minValue = value;
            }
            if (value > maxValue) {
                maxValue = value;
            }
        }

        double xScale = (double) graphWidth / (stockData.size() - 1);
        double yScale = (double) graphHeight / (maxValue - minValue);

        // Draw data points
        int x1 = MARGIN;
        int y1 = MARGIN + graphHeight - (int) ((stockData.get(getFirstDate()) - minValue) * yScale);
        for (Map.Entry<String, Double> entry : stockData.entrySet()) {
            int x2 = x1 + (int) xScale;
            int y2 = MARGIN + graphHeight - (int) ((entry.getValue() - minValue) * yScale);
            g.drawLine(x1, y1, x2, y2);
            x1 = x2;
            y1 = y2;
        }
    }

    private String getFirstDate() {
        return stockData.keySet().iterator().next();
    }

    public static void main(String[] args) {
        Map<String, Double> stockData = new HashMap<>();
        stockData.put("2022-01-03", 182.01);
        stockData.put("2022-01-04", 179.7);
        stockData.put("2022-01-05", 174.92);
        stockData.put("2022-01-06", 172.0);
        stockData.put("2022-01-07", 172.17);
        stockData.put("2022-01-10", 172.19);
        stockData.put("2022-01-11", 175.08);
        stockData.put("2022-01-12", 175.53);
        stockData.put("2022-01-13", 172.19);
        stockData.put("2022-01-14", 173.07);
        stockData.put("2022-01-18", 169.8);
        stockData.put("2022-01-19", 166.23);
        stockData.put("2022-01-20", 164.51);
        stockData.put("2022-01-21", 162.41);
        stockData.put("2022-01-24", 161.62);
        stockData.put("2022-01-25", 159.78);
        stockData.put("2022-01-26", 159.69);
        stockData.put("2022-01-27", 159.22);
        stockData.put("2022-01-28", 170.33);
        stockData.put("2022-01-31", 174.78);
        stockData.put("2022-02-01", 174.61);
        stockData.put("2022-02-02", 175.84);
        stockData.put("2022-02-03", 172.9);
        stockData.put("2022-02-04", 172.39);
        stockData.put("2022-02-07", 171.66);
        stockData.put("2022-02-08", 174.83);
        stockData.put("2022-02-09", 176.28);
        stockData.put("2022-02-10", 172.12);
        stockData.put("2022-02-11", 168.64);
        stockData.put("2022-02-14", 168.88);
        stockData.put("2022-02-15", 172.79);
        stockData.put("2022-02-16", 172.55);
        stockData.put("2022-02-17", 168.88);
        stockData.put("2022-02-18", 167.3);
        stockData.put("2022-02-22", 164.32);
        stockData.put("2022-02-23", 160.07);
        stockData.put("2022-02-24", 162.74);
        stockData.put("2022-02-25", 164.85);
        stockData.put("2022-02-28", 165.12);
        stockData.put("2022-03-01", 163.2);
        stockData.put("2022-03-02", 166.56);
        stockData.put("2022-03-03", 166.23);
        stockData.put("2022-03-04", 163.17);
        stockData.put("2022-03-07", 159.3);
        stockData.put("2022-03-08", 157.44);
        stockData.put("2022-03-09", 162.95);
        stockData.put("2022-03-10", 158.52);
        stockData.put("2022-03-11", 154.73);
        stockData.put("2022-03-14", 150.62);
        stockData.put("2022-03-15", 155.09);
        stockData.put("2022-03-16", 159.59);
        stockData.put("2022-03-17", 160.62);
        stockData.put("2022-03-18", 163.98);
        stockData.put("2022-03-21", 165.38);
        stockData.put("2022-03-22", 168.82);
        stockData.put("2022-03-23", 170.21);
        stockData.put("2022-03-24", 174.07);
        stockData.put("2022-03-25", 174.72);
        stockData.put("2022-03-28", 175.6);
        stockData.put("2022-03-29", 178.96);
        stockData.put("2022-03-30", 177.77);
        stockData.put("2022-03-31", 174.61);
        stockData.put("2022-04-01", 174.31);
        stockData.put("2022-04-04", 178.44);
        stockData.put("2022-04-05", 175.06);
        stockData.put("2022-04-06", 171.83);
        stockData.put("2022-04-07", 172.14);
        stockData.put("2022-04-08", 170.09);
        stockData.put("2022-04-11", 165.75);
        stockData.put("2022-04-12", 167.66);
        stockData.put("2022-04-13", 170.4);
        stockData.put("2022-04-14", 165.29);
        stockData.put("2022-04-18", 165.07);
        stockData.put("2022-04-19", 167.4);
        stockData.put("2022-04-20", 167.23);
        stockData.put("2022-04-21", 166.42);
        stockData.put("2022-04-22", 161.79);
        stockData.put("2022-04-25", 162.88);
        stockData.put("2022-04-26", 156.8);
        stockData.put("2022-04-27", 156.57);
        stockData.put("2022-04-28", 163.64);
        stockData.put("2022-04-29", 157.65);
        stockData.put("2022-05-02", 157.96);
        stockData.put("2022-05-03", 159.48);
        stockData.put("2022-05-04", 166.02);
        stockData.put("2022-05-05", 156.77);
        stockData.put("2022-05-06", 157.28);
        stockData.put("2022-05-09", 152.06);
        stockData.put("2022-05-10", 154.51);
        stockData.put("2022-05-11", 146.5);
        stockData.put("2022-05-12", 142.56);
        stockData.put("2022-05-13", 147.11);
        stockData.put("2022-05-16", 145.54);
        stockData.put("2022-05-17", 149.24);
        stockData.put("2022-05-18", 140.82);
        stockData.put("2022-05-19", 137.35);
        stockData.put("2022-05-20", 137.59);
        stockData.put("2022-05-23", 143.11);
        stockData.put("2022-05-24", 140.36);
        stockData.put("2022-05-25", 140.52);
        stockData.put("2022-05-26", 143.78);
        stockData.put("2022-05-27", 149.64);
        stockData.put("2022-05-31", 148.84);
        stockData.put("2022-06-01", 148.71);
        stockData.put("2022-06-02", 151.21);
        stockData.put("2022-06-03", 145.38);
        stockData.put("2022-06-06", 146.14);
        stockData.put("2022-06-07", 148.71);
        stockData.put("2022-06-08", 147.96);
        stockData.put("2022-06-09", 142.64);
        stockData.put("2022-06-10", 137.13);
        stockData.put("2022-06-13", 131.88);
        stockData.put("2022-06-14", 132.76);
        stockData.put("2022-06-15", 135.43);
        stockData.put("2022-06-16", 130.06);
        stockData.put("2022-06-17", 131.56);
        stockData.put("2022-06-21", 135.87);
        stockData.put("2022-06-22", 135.35);
        stockData.put("2022-06-23", 138.27);
        stockData.put("2022-06-24", 141.66);
        stockData.put("2022-06-27", 141.66);
        stockData.put("2022-06-28", 137.44);
        stockData.put("2022-06-29", 139.23);
        stockData.put("2022-06-30", 136.72);
        stockData.put("2022-07-01", 138.93);
        stockData.put("2022-07-05", 141.56);
        stockData.put("2022-07-06", 142.92);
        stockData.put("2022-07-07", 146.35);
        stockData.put("2022-07-08", 147.04);
        stockData.put("2022-07-11", 144.87);
        stockData.put("2022-07-12", 145.86);
        stockData.put("2022-07-13", 145.49);
        stockData.put("2022-07-14", 148.47);
        stockData.put("2022-07-15", 150.17);
        stockData.put("2022-07-18", 147.07);
        stockData.put("2022-07-19", 151.0);
        stockData.put("2022-07-20", 153.04);
        stockData.put("2022-07-21", 155.35);
        stockData.put("2022-07-22", 154.09);
        stockData.put("2022-07-25", 152.95);
        stockData.put("2022-07-26", 151.6);
        stockData.put("2022-07-27", 156.79);
        stockData.put("2022-07-28", 157.35);
        stockData.put("2022-07-29", 162.51);
        stockData.put("2022-08-01", 161.51);
        stockData.put("2022-08-02", 160.01);
        stockData.put("2022-08-03", 166.13);
        stockData.put("2022-08-04", 165.81);
        stockData.put("2022-08-05", 165.35);
        stockData.put("2022-08-08", 164.87);
        stockData.put("2022-08-09", 164.92);
        stockData.put("2022-08-10", 169.24);
        stockData.put("2022-08-11", 168.49);
        stockData.put("2022-08-12", 172.1);
        stockData.put("2022-08-15", 173.19);
        stockData.put("2022-08-16", 173.03);
        stockData.put("2022-08-17", 174.55);
        stockData.put("2022-08-18", 174.15);
        stockData.put("2022-08-19", 171.52);
        stockData.put("2022-08-22", 167.57);
        stockData.put("2022-08-23", 167.23);
        stockData.put("2022-08-24", 167.53);
        stockData.put("2022-08-25", 170.03);
        stockData.put("2022-08-26", 163.62);
        stockData.put("2022-08-29", 161.38);
        stockData.put("2022-08-30", 158.91);
        stockData.put("2022-08-31", 157.22);
        stockData.put("2022-09-01", 157.96);
        stockData.put("2022-09-02", 155.81);
        stockData.put("2022-09-06", 154.53);
        stockData.put("2022-09-07", 155.96);
        stockData.put("2022-09-08", 154.46);
        stockData.put("2022-09-09", 157.37);
        stockData.put("2022-09-12", 163.43);
        stockData.put("2022-09-13", 153.84);
        stockData.put("2022-09-14", 155.31);
        stockData.put("2022-09-15", 152.37);
        stockData.put("2022-09-16", 150.7);
        stockData.put("2022-09-19", 154.48);
        stockData.put("2022-09-20", 156.9);
        stockData.put("2022-09-21", 153.72);
        stockData.put("2022-09-22", 152.74);
        stockData.put("2022-09-23", 150.43);
        stockData.put("2022-09-26", 150.77);
        stockData.put("2022-09-27", 151.76);
        stockData.put("2022-09-28", 149.84);
        stockData.put("2022-09-29", 142.48);
        stockData.put("2022-09-30", 138.2);
        stockData.put("2022-10-03", 142.45);
        stockData.put("2022-10-04", 146.1);
        stockData.put("2022-10-05", 146.4);
        stockData.put("2022-10-06", 145.43);
        stockData.put("2022-10-07", 140.09);
        stockData.put("2022-10-10", 140.42);
        stockData.put("2022-10-11", 138.98);
        stockData.put("2022-10-12", 138.34);
        stockData.put("2022-10-13", 142.99);
        stockData.put("2022-10-14", 138.38);
        stockData.put("2022-10-17", 142.41);
        stockData.put("2022-10-18", 143.75);
        stockData.put("2022-10-19", 143.86);
        stockData.put("2022-10-20", 143.39);
        stockData.put("2022-10-21", 147.27);
        stockData.put("2022-10-24", 149.45);
        stockData.put("2022-10-25", 152.34);
        stockData.put("2022-10-26", 149.35);
        stockData.put("2022-10-27", 144.8);
        stockData.put("2022-10-28", 155.74);
        stockData.put("2022-10-31", 153.34);
        stockData.put("2022-11-01", 150.65);
        stockData.put("2022-11-02", 145.03);
        stockData.put("2022-11-03", 138.88);
        stockData.put("2022-11-04", 138.38);
        stockData.put("2022-11-07", 138.92);
        stockData.put("2022-11-08", 139.5);
        stockData.put("2022-11-09", 134.87);
        stockData.put("2022-11-10", 146.87);
        stockData.put("2022-11-11", 149.7);
        stockData.put("2022-11-14", 148.28);
        stockData.put("2022-11-15", 150.04);
        stockData.put("2022-11-16", 148.79);
        stockData.put("2022-11-17", 150.72);
        stockData.put("2022-11-18", 151.29);
        stockData.put("2022-11-21", 148.01);
        stockData.put("2022-11-22", 150.18);
        stockData.put("2022-11-23", 151.07);
        stockData.put("2022-11-25", 148.11);
        stockData.put("2022-11-28", 144.22);
        stockData.put("2022-11-29", 141.17);
        stockData.put("2022-11-30", 148.03);
        stockData.put("2022-12-01", 148.31);
        stockData.put("2022-12-02", 147.81);
        stockData.put("2022-12-05", 146.63);
        stockData.put("2022-12-06", 142.91);
        stockData.put("2022-12-07", 140.94);
        stockData.put("2022-12-08", 142.65);
        stockData.put("2022-12-09", 142.16);
        stockData.put("2022-12-12", 144.49);
        stockData.put("2022-12-13", 145.47);
        stockData.put("2022-12-14", 143.21);
        stockData.put("2022-12-15", 136.5);
        stockData.put("2022-12-16", 134.04);
        stockData.put("2022-12-19", 132.28);
        stockData.put("2022-12-20", 136.34);
        stockData.put("2022-12-21", 137.3);
        stockData.put("2022-12-22", 134.02);
        stockData.put("2022-12-23", 134.69);
        stockData.put("2022-12-27", 135.52);
        stockData.put("2022-12-28", 133.32);
        stockData.put("2022-12-29", 134.52);
        stockData.put("2022-12-30", 136.48);
        stockData.put("2023-01-03", 135.56);
        stockData.put("2023-01-04", 137.37);
        stockData.put("2023-01-05", 138.14);
        stockData.put("2023-01-06", 138.22);
        stockData.put("2023-01-09", 136.06);
        stockData.put("2023-01-10", 133.57);
        stockData.put("2023-01-11", 138.4);
        stockData.put("2023-01-12", 138.31);
        stockData.put("2023-01-13", 139.03);
        stockData.put("2023-01-16", 139.78);
        stockData.put("2023-01-17", 139.44);
        stockData.put("2023-01-18", 137.82);
        stockData.put("2023-01-19", 135.02);
        stockData.put("2023-01-20", 137.18);
        stockData.put("2023-01-23", 139.45);
        stockData.put("2023-01-24", 139.5);
        stockData.put("2023-01-25", 138.93);
        stockData.put("2023-01-26", 141.02);
        stockData.put("2023-01-27", 141.73);
        stockData.put("2023-01-30", 144.79);
        stockData.put("2023-01-31", 145.23);
        stockData.put("2023-02-01", 144.74);
        stockData.put("2023-02-02", 142.62);
        stockData.put("2023-02-03", 141.22);
        stockData.put("2023-02-06", 139.68);
        stockData.put("2023-02-07", 141.41);
        stockData.put("2023-02-08", 141.23);
        stockData.put("2023-02-09", 142.24);
        stockData.put("2023-02-10", 139.49);
        stockData.put("2023-02-13", 137.45);
        stockData.put("2023-02-14", 140.34);
        stockData.put("2023-02-15", 140.86);
        stockData.put("2023-02-16", 140.79);
        stockData.put("2023-02-17", 142.06);
        stockData.put("2023-02-20", 140.38);
        stockData.put("2023-02-21", 138.58);
        stockData.put("2023-02-22", 139.77);
        stockData.put("2023-02-23", 141.45);
        stockData.put("2023-02-24", 142.92);
        stockData.put("2023-02-27", 143.25);
        stockData.put("2023-02-28", 142.6);
        stockData.put("2023-03-01", 141.49);
        stockData.put("2023-03-02", 143.37);
        stockData.put("2023-03-03", 144.52);
        stockData.put("2023-03-06", 144.1);
        stockData.put("2023-03-07", 143.23);
        stockData.put("2023-03-08", 140.93);
        stockData.put("2023-03-09", 142.24);
        stockData.put("2023-03-10", 139.25);
        stockData.put("2023-03-13", 138.01);
        stockData.put("2023-03-14", 135.49);
        stockData.put("2023-03-15", 132.87);
        stockData.put("2023-03-16", 134.61);
        stockData.put("2023-03-17", 136.44);
        stockData.put("2023-03-20", 135.72);
        stockData.put("2023-03-21", 134.43);
        stockData.put("2023-03-22", 133.95);
        stockData.put("2023-03-23", 136.71);
        stockData.put("2023-03-24", 137.18);
        stockData.put("2023-03-27", 139.92);
        stockData.put("2023-03-28", 140.12);
        stockData.put("2023-03-29", 141.98);
        stockData.put("2023-03-30", 141.91);
        stockData.put("2023-03-31", 141.16);
        stockData.put("2023-04-03", 140.88);
        stockData.put("2023-04-04", 141.57);
        stockData.put("2023-04-05", 140.35);
        stockData.put("2023-04-06", 138.43);
        stockData.put("2023-04-07", 139.22);
        stockData.put("2023-04-10", 139.71);
        stockData.put("2023-04-11", 141.55);
        stockData.put("2023-04-12", 140.99);
        stockData.put("2023-04-13", 142.35);
        stockData.put("2023-04-14", 144.23);
        stockData.put("2023-04-17", 142.77);
        stockData.put("2023-04-18", 141.32);
        stockData.put("2023-04-19", 141.58);
        stockData.put("2023-04-20", 143.25);
        stockData.put("2023-04-21", 142.72);
        stockData.put("2023-04-24", 140.75);
        stockData.put("2023-04-25", 141.31);
        stockData.put("2023-04-26", 143.88);
        stockData.put("2023-04-27", 144.95);
        stockData.put("2023-04-28", 145.68);
        stockData.put("2023-05-01", 144.93);
        stockData.put("2023-05-02", 142.98);
        stockData.put("2023-05-03", 141.33);
        stockData.put("2023-05-04", 142.07);
        stockData.put("2023-05-05", 141.49);
        stockData.put("2023-05-08", 142.78);
        stockData.put("2023-05-09", 143.53);
        stockData.put("2023-05-10", 144.11);
        stockData.put("2023-05-11", 144.82);
        stockData.put("2023-05-12", 144.13);
        stockData.put("2023-05-15", 142.55);
        stockData.put("2023-05-16", 143.42);
        stockData.put("2023-05-17", 142.79);
        stockData.put("2023-05-18", 140.91);
        stockData.put("2023-05-19", 142.03);
        stockData.put("2023-05-22", 143.38);
        stockData.put("2023-05-23", 142.63);
        stockData.put("2023-05-24", 142.51);
        stockData.put("2023-05-25", 144.09);
        stockData.put("2023-05-26", 144.52);
        stockData.put("2023-05-29", 145.22);
        stockData.put("2023-05-30", 146.11);
        stockData.put("2023-05-31", 146.77);
        stockData.put("2023-06-01", 146.32);
        stockData.put("2023-06-02", 146.61);
        stockData.put("2023-06-05", 147.43);
        stockData.put("2023-06-06", 147.02);
        stockData.put("2023-06-07", 147.84);
        stockData.put("2023-06-08", 147.48);
        stockData.put("2023-06-09", 146.23);

        StockGraph graph = new StockGraph(stockData);

        JFrame frame = new JFrame("Stock Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.getContentPane().add(graph);
        frame.setVisible(true);
    }
}